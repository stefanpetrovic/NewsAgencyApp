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
import javax.faces.bean.ViewScoped;
import njt.domen.Clanak;


/**
 *
 * @author stefan
 */
@ManagedBean
@ViewScoped
public class AjaxProba implements Serializable{
    
    private static final long serialVersionUID = -1804721808132968734L;
    
    private String poruka;
    private Clanak clanak = new Clanak();
    private List<Clanak> clanci = new ArrayList<>();

    public List<Clanak> getClanci() {
        return clanci;
    }
    
    public void setClanci(List<Clanak> clanci) {
        this.clanci = clanci;
    }
    
    public Clanak getClanak() {
        return clanak;
    }

    public void setClanak(Clanak clanak) {
        this.clanak = clanak;
    }
    
    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    public void dodajClanak() {
        clanci.add(clanak);
        clanak = new Clanak();
    }
    
    
}
