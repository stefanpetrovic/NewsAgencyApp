/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import njt.domen.Broj;
import njt.domen.Clanak;
import njt.domen.ClanakPK;
import njt.konekcija.Konekcija;

/**
 *
 * @author stefan
 */
@ManagedBean
public class BrojBean implements Serializable{
    private static final long serialVersionUID = -4447133297392254893L;
    
    private Broj broj = new Broj();
    private List<Clanak> clanci = new ArrayList<>();
    private Clanak trenutniClanak = new Clanak(null, null, null);
    

    public BrojBean() {
        
    }
    
    public BrojBean(Broj broj) {
        this.broj = broj;
        clanci = broj.getClanakList();
    }

    public Broj getBroj() {
        return broj;
    }

    public void setBroj(Broj broj) {
        this.broj = broj;
    }

    public List<Clanak> getClanci() {
        return clanci;
    }

    public void setClanci(List<Clanak> clanci) {
        this.clanci = clanci;
    }

    public Clanak getTrenutniClanak() {
        return trenutniClanak;
    }

    public void setTrenutniClanak(Clanak trenutniClanak) {
        this.trenutniClanak = trenutniClanak;
    }
    
    
   
}
