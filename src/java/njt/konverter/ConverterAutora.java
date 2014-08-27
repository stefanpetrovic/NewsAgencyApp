/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.konverter;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import njt.domen.Autor;
import njt.konekcija.Konekcija;

/**
 *
 * @author stefan
 */
@FacesConverter("converterAutora")
public class ConverterAutora implements Converter{

    private List<Autor> autori;

    public ConverterAutora() {
        autori = Konekcija.getInstance().vratiAutore();
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
