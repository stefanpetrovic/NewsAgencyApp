/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.beans;

import njt.domen.Urednik;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author stefan
 */
@ManagedBean
@SessionScoped
public class UrednikBean implements Serializable{

    private static final long serialVersionUID = 8022232151742389165L;
    
    private String korisnickoIme;
    private String korisnickaSifra;
    private Urednik urednikDB;
    
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme.trim();
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra.trim();
    }
    
    public Urednik getUrednik() {
        return urednikDB;
    }

    public String ulogujSe() {
        if (urednikDB != null) {
            return "broj?faces-redirect=true";
        }
        System.out.println(korisnickaSifra  + korisnickoIme);
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (!korisnickoIme.equals("") && !korisnickaSifra.equals("")) {
                urednikDB = njt.konekcija.Konekcija.getInstance().ulogujUrednika(new Urednik(0, null, null, korisnickoIme, korisnickaSifra));
            }
        } catch (Exception ex) {
            Logger.getLogger(UrednikBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (urednikDB != null) {
            return "broj?faces-redirect=true";
        }
        context.addMessage(null, new FacesMessage("Sistem ne moze da nadje urednika po zadatim vrednostima."));
        return null;
    }
}
