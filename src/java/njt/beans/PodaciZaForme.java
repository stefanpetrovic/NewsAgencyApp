/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import njt.domen.Autor;
import njt.domen.Kategorija;
import njt.domen.Novine;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import njt.konekcija.Konekcija;

/**
 *
 * @author stefan
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class PodaciZaForme{
    
    private List<Novine> novine;
    private List<Autor> autori;
    private List<Kategorija> kategorije;

    public PodaciZaForme() {
        novine = Konekcija.getInstance().vratiNovine();
        autori = Konekcija.getInstance().vratiAutore();
        kategorije = Konekcija.getInstance().vratiKategorije();
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
