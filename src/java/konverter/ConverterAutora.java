/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import domen.Autor;
import domen.OpstiDomenskiObjekat;
import logika.Kontroler;

/**
 *
 * @author stefan
 */
@FacesConverter("converterAutora")
public class ConverterAutora implements Converter{

    private List<Autor> autori = new LinkedList<>();

    public ConverterAutora() {
        try {
            Map<String, List<OpstiDomenskiObjekat>> mapa = Kontroler.getInstance().vratiAutoreIKategorije();
            List<OpstiDomenskiObjekat> odos = mapa.get("autori");
            for (OpstiDomenskiObjekat odo : odos) {
                autori.add((Autor) odo);
            }
        } catch (Exception ex) {
            Logger.getLogger(ConverterAutora.class.getName()).log(Level.SEVERE, null, ex);
            autori = new ArrayList<>();
        }
    }
    
    public Autor vratiAutoraZaID(String id) {
        try {
            int aid = Integer.parseInt(id);
            for (Autor a : autori) {
                if (a.getAutorID() == aid) {
                    return a;
                }
            }
            return null;
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return vratiAutoraZaID(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Autor) value).getAutorID() + "";
    }
    
}
