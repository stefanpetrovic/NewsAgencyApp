/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.clanak;

import db.DBBroker;
import java.util.ArrayList;
import java.util.List;
import domen.Clanak;
import domen.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class PretraziClankeSO extends OpstaSO{

    private List<Clanak> clanci = new ArrayList<Clanak>();

    public List<Clanak> getClanci() {
        return clanci;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        List<OpstiDomenskiObjekat> odos = DBBroker.getInstance().vratiNeke((Clanak) object);
        for (OpstiDomenskiObjekat odo : odos) {
            clanci.add((Clanak) odo);
        }
    }
    
}
