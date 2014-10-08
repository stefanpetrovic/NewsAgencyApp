/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import domen.Broj;
import domen.Clanak;
import domen.ClanakPK;
import logika.Kontroler;

/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class UnosBrojaBean implements Serializable{
    
    private static final long serialVersionUID = 6776433176866677104L;
    
    private BrojBean brojBean = new BrojBean();

    @ManagedProperty(value="#{urednikBean}")
    private UrednikBean urednikBean;

    public UrednikBean getUrednikBean() {
        return urednikBean;
    }

    public void setUrednikBean(UrednikBean urednikBean) {
        this.urednikBean = urednikBean;
    }
    
    public BrojBean getBrojBean() {
        return brojBean;
    }

    public void setBrojBean(BrojBean brojBean) {
        this.brojBean = brojBean;
    }
    
    public void dodajClanak() {
        brojBean.getClanci().add(brojBean.getTrenutniClanak());
        brojBean.setTrenutniClanak(new Clanak());
    }
    
    public boolean validacija() {
        Broj b = brojBean.getBroj();
        if (b.getCena() == 0) return false;
        if (b.getClanakList().isEmpty()) return false;
        if (b.getDatumIzdavanja() == null && b.getDatumIzdavanja().after(new Date())) return false;
        if (b.getDatumIzdavanja().before(new Date())) return false;
        if (b.getTiraz() == 0) return false;
        return true;
    }
    
    public void kreirajNoviBroj() {
        try {
            brojBean.setBroj(Kontroler.getInstance().kreirajNoviBroj());
            brojBean.getBroj().setUrednik(urednikBean.getUrednik());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sistem je kreirao novi broj."));
        } catch (Exception ex) {
            Logger.getLogger(UnosBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greška", "Greška prilikom kreiranja broja."));
        }
    }
    
    public String sacuvajBroj() {
        
        long rb = 1L;
        for(Clanak c : brojBean.getClanci()) {
            c.setClanakPK(new ClanakPK(brojBean.getBroj().getBrojID(), rb));
            rb++;
        }
        brojBean.getBroj().setClanakList(brojBean.getClanci());
        try {
            Kontroler.getInstance().sacuvajBroj(brojBean.getBroj());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Broj uspešno sačuvan."));
            brojBean = new BrojBean();
        } catch (Exception ex) {
            Logger.getLogger(UnosBrojaBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greška", "Greška prilikom čuvanja broja."));
        }
        
        return null;
    }
    
}
