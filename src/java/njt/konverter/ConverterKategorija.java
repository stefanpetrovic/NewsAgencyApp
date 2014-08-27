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
import njt.beans.PodaciZaForme;
import njt.domen.Kategorija;
import njt.konekcija.Konekcija;

/**
 *
 * @author stefan
 */
@FacesConverter("converterKategorija")
public class ConverterKategorija implements Converter{

    private List<Kategorija> kategorije;
    
    public ConverterKategorija() {
        kategorije = Konekcija.getInstance().vratiKategorije();
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
