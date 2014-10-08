/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.pomocne;

import db.DBBroker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domen.Autor;
import domen.Kategorija;
import domen.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class VratiAutoreIKategorije extends OpstaSO{

    private Map<String, List<OpstiDomenskiObjekat>> autoriIKategorije = new HashMap<>();

    public Map<String, List<OpstiDomenskiObjekat>> getAutoriIKategorije() {
        return autoriIKategorije;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        List<OpstiDomenskiObjekat> odos = DBBroker.getInstance().vratiSve(new Autor());
        autoriIKategorije.put("autori", odos);
        odos = DBBroker.getInstance().vratiSve(new Kategorija());
        autoriIKategorije.put("kategorije", odos);
    }
    
}
