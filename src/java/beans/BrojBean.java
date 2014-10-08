/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import domen.Broj;
import domen.Clanak;

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
    
    public String parseHTML(String html) {
        String s = "";
        boolean escape = false;
        for (int i = 0; i < html.length(); i++) {
            char c = html.charAt(i);
            if (c == '<') {
                escape = true;
                if (!s.endsWith(" ")) s += " ";
            }
            if (!escape) {
                s += c + "";
            }
            if (c == '>') {
                escape = false;
            }
            if (s.length() > 20) {
                if (s.endsWith(" ")) {
                    s = s.substring(0, s.length() - 1);
                }
                s += "...";
                break;
            }
        }
        return s;
    }
}
