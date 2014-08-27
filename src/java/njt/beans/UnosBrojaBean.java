/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import njt.domen.Clanak;
import njt.domen.ClanakPK;
import njt.konekcija.Konekcija;

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
    
    public void kreirajNoviBroj() {
        brojBean.setBroj(Konekcija.getInstance().kreirajNoviBroj());
        brojBean.getBroj().setUrednik(urednikBean.getUrednik());
        System.out.println("klik");
    }
    
    public String sacuvajBroj() {
        
        long rb = 1L;
        for(Clanak c : brojBean.getClanci()) {
            c.setClanakPK(new ClanakPK(brojBean.getBroj().getBrojID(), rb));
            rb++;
        }
        brojBean.getBroj().setClanakList(brojBean.getClanci());
        Konekcija.getInstance().sacuvajBroj(brojBean.getBroj());
        return "broj";
    }
    
}
