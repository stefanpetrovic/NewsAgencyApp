/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import domen.Broj;
import logika.Kontroler;

/**
 *
 * @author stefan
 */
@FacesConverter("converterBroja")
public class ConverterBroja implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long id = Long.parseLong(value);
        try {
            return Kontroler.getInstance().vratiBroj(id);
        } catch (Exception ex) {
            Logger.getLogger(ConverterBroja.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Broj) value).getBrojID().toString();
    }
    
}
