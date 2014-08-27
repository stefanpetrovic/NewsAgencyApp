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
import njt.domen.Novine;
import njt.konekcija.Konekcija;

/**
 *
 * @author stefan
 */
@FacesConverter("converterNovina")
public class ConverterNovina implements Converter{

    private List<Novine> novine;

    public ConverterNovina() {
        novine = Konekcija.getInstance().vratiNovine();
    }
    
    public Novine vratiNovineZaID(String id) {
        try {
            int nid = Integer.parseInt(id);
            for (Novine n : novine) {
                if (n.getNovineID() == nid) {
                    return n;
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
        return vratiNovineZaID(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Novine)value).getNovineID() + "";
    }
    
}
