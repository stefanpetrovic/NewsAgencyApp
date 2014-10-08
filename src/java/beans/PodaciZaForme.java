/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import domen.Autor;
import domen.Kategorija;
import domen.Novine;
import domen.OpstiDomenskiObjekat;
import logika.Kontroler;

/**
 *
 * @author stefan
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class PodaciZaForme{
    
    private List<Novine> novine;
    private List<Autor> autori = new ArrayList<>();
    private List<Kategorija> kategorije = new ArrayList<>();

    public PodaciZaForme() {
        try {
            novine = Kontroler.getInstance().vratiNovine();
        } catch (Exception ex) {
            novine = new ArrayList<>();
            Logger.getLogger(PodaciZaForme.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Map<String, List<OpstiDomenskiObjekat>> mapa = Kontroler.getInstance().vratiAutoreIKategorije();
            List<OpstiDomenskiObjekat> odos = mapa.get("autori");
            for (OpstiDomenskiObjekat odo : odos) {
                autori.add((Autor) odo);
            }
            odos = mapa.get("kategorije");
            for (OpstiDomenskiObjekat odo : odos) {
                kategorije.add((Kategorija) odo);
            }
        } catch (Exception ex) {
            autori = new ArrayList<>();
            kategorije = new ArrayList<>();
            Logger.getLogger(PodaciZaForme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Novine> getNovine() {
        return novine;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }
    
    
    
}
