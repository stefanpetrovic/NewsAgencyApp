/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.util.List;
import java.util.Map;

/**
 *
 * @author stefan
 */
public interface OpstiDomenskiObjekat {
    
    public Object vratiID();
    
    public String vratiNazivKljuca();//vraca brojID
    
    public String vratiNazivTabele();//vraca Broj
    
    public String vratiNazivObjekta();//vraca b
    
    public String vratiKriterijumPretrage();//vraca u.korisnickoIme=:korisnickoIme AND u.korisnickaSifra=:korisnickaSifra
    
    public Map<String, Object> vratiVrednostKriterijumPretrage();//vraca "korisnickoIme", urednik.getKorisnickoIme()
}
