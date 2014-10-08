/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import domen.Autor;
import domen.Broj;
import domen.Clanak;
import logika.Kontroler;
import org.primefaces.context.RequestContext;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class IzmenaClankaBean implements Serializable{
    private static final long serialVersionUID = 5928741855906287424L;
    
    private BrojBean brojBean = new BrojBean();
    private Autor izabraniAutor;
    private Clanak clanakZaBrisanje;

    public BrojBean getBrojBean() {
        return brojBean;
    }

    public void setBrojBean(BrojBean brojBean) {
        this.brojBean = brojBean;
    }

    public Autor getIzabraniAutor() {
        return izabraniAutor;
    }

    public void setIzabraniAutor(Autor izabraniAutor) {
        this.izabraniAutor = izabraniAutor;
    }

    public Clanak getClanakZaBrisanje() {
        return clanakZaBrisanje;
    }

    public void setClanakZaBrisanje(Clanak clanakZaBrisanje) {
        this.clanakZaBrisanje = clanakZaBrisanje;
    }
    
    public void pretraziClanke() {
        try {
            brojBean.setClanci(Kontroler.getInstance().pretraziClanke(izabraniAutor));
            if (brojBean.getClanci().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem ne moze da pronadje clanke."));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem je nasao clanke."));
            }
        } catch (Exception ex) {
            Logger.getLogger(IzmenaClankaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom pretrage clanaka."));
        }
    }
    
    public void obrisiClanak() {
        try {
            Kontroler.getInstance().obrisiClanak(clanakZaBrisanje);
            brojBean.getClanci().remove(clanakZaBrisanje);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Sistem je obrisao clanak."));
        } catch (Exception ex) {
            Logger.getLogger(IzmenaClankaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Greska prilikom brisanja clanka."));
        }
    }
    
    public void sacuvajIzmene() {
        try {
            Kontroler.getInstance().izmeniClanak(brojBean.getTrenutniClanak());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Članak uspešno izmenjen."));
            RequestContext.getCurrentInstance().execute("clanakDialog.hide();");
        } catch (Exception ex) {
            Logger.getLogger(IzmenaClankaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Greška prilikom izmene članka."));
        }
        
    }
}
