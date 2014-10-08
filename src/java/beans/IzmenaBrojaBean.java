/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import domen.Broj;
import domen.Novine;
import logika.Kontroler;
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
        try {
            brojevi = Kontroler.getInstance().pretraziBrojeve(izabraneNovine);
            if (brojevi.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem ne moze da pronadje brojeve po zadatom kriterijumu"));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem je pronasao brojeve"));
            }
        } catch (Exception ex) {
            Logger.getLogger(IzmenaBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom pretrage brojeva"));
        }
    }
    
    public void sacuvajIzmene() {
        try {
            Kontroler.getInstance().izmeniBroj(brojBean.getBroj());
            RequestContext.getCurrentInstance().execute("brojDialog.hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno izmenjen broj"));
        } catch (Exception ex) {
            Logger.getLogger(IzmenaBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom izmene broja"));
        }
    }
    
    public void obrisiBroj() {
        try {
            Kontroler.getInstance().obrisiBroj(brojZaBrisanje);
            brojevi.remove(brojZaBrisanje);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno obrisan broj"));
        } catch (Exception ex) {
            Logger.getLogger(IzmenaBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska pilikom brisanja broja"));
        }
    }
    
    public void izmeniClanak() {
        try {
            Kontroler.getInstance().izmeniClanak(brojBean.getTrenutniClanak());
            RequestContext.getCurrentInstance().execute("clanakDialog.hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno izmenjen clanak"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom izmene clanka"));
            Logger.getLogger(IzmenaBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
