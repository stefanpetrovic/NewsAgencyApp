/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.urednik;

import db.DBBroker;
import javax.persistence.EntityManager;
import domen.OpstiDomenskiObjekat;
import domen.Urednik;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class NadjiUrednikaSO extends OpstaSO{

    private Urednik urednik;
    
    public Urednik getUrednik() {
        return urednik;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        urednik = (Urednik) DBBroker.getInstance().vratiJedan((OpstiDomenskiObjekat) object);
    }
    
}
