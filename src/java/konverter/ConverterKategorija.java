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
import beans.PodaciZaForme;
import domen.Kategorija;
import domen.OpstiDomenskiObjekat;
import logika.Kontroler;

/**
 *
 * @author stefan
 */
@FacesConverter("converterKategorija")
public class ConverterKategorija implements Converter{

    private List<Kategorija> kategorije = new LinkedList<>();
    
    public ConverterKategorija() {
        try {
            Map<String, List<OpstiDomenskiObjekat>> mapa = Kontroler.getInstance().vratiAutoreIKategorije();
            List<OpstiDomenskiObjekat> odos = mapa.get("kategorije");
            for (OpstiDomenskiObjekat odo: odos) {
                kategorije.add((Kategorija) odo);
            }
        } catch (Exception ex) {
            Logger.getLogger(ConverterKategorija.class.getName()).log(Level.SEVERE, null, ex);
            kategorije = new ArrayList<>();
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return vratiKategorijuZaID(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Kategorija) value).getKategorijaID() + "";
    }
    
    public Kategorija vratiKategorijuZaID(String id) {
        try {
            int kid = Integer.parseInt(id);
            for (Kategorija k : kategorije) {
                if (k.getKategorijaID() == kid) {
                    return k;
                }
            }
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
