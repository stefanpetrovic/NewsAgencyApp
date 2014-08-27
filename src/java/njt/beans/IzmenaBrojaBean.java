/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import njt.domen.Broj;
import njt.domen.Novine;
import njt.konekcija.Konekcija;
import org.primefaces.context.RequestContext;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class IzmenaBrojaBean implements Serializable{
    private static final long serialVersionUID = -7253591140354203688L;
    
    private List<Broj> brojevi;
    private Novine izabraneNovine;
    //private Broj izabraniBroj = new Broj();
    private BrojBean brojBean = new BrojBean();
    private Broj brojZaBrisanje;

//    public Broj getIzabraniBroj() {
//        return izabraniBroj;
//    }
//
//    public void setIzabraniBroj(Broj izabraniBroj) {
//        this.izabraniBroj = izabraniBroj;
//    }
//    
    public Broj getBrojZaBrisanje() {
        return brojZaBrisanje;
    }

    public void setBrojZaBrisanje(Broj brojZaBrisanje) {
        this.brojZaBrisanje = brojZaBrisanje;
    }
    
    public List<Broj> getBrojevi() {
        return brojevi;
    }

    public void setBrojevi(List<Broj> brojevi) {
        this.brojevi = brojevi;
    }

    public Novine getIzabraneNovine() {
        return izabraneNovine;
    }

    public void setIzabraneNovine(Novine izabraneNovine) {
        this.izabraneNovine = izabraneNovine;
    }
    
    public BrojBean getBrojBean() {
        return brojBean;
    }

    public void setBrojBean(BrojBean brojBean) {
        this.brojBean = brojBean;
    }
    
    public void pretraziBrojeve() {
        brojevi = Konekcija.getInstance().pretraziBrojeve(izabraneNovine);
    }
    
    public void sacuvajIzmene() {
        Konekcija.getInstance().izmeniBroj(brojBean.getBroj());
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void obrisiBroj() {
        Konekcija.getInstance().obrisiBroj(brojZaBrisanje);
        brojevi.remove(brojZaBrisanje);
    }
    
}
