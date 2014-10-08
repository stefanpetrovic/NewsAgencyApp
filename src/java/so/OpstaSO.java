/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import javax.persistence.EntityManager;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author stefan
 */
public abstract class OpstaSO {

    public final void izvrsiOperaciju(Object object) throws Exception {
        try {
            DBBroker.getInstance().pokreniEntityManager();
            DBBroker.getInstance().pokreniTransakciju();
            
            proveriPreduslov(object);
            izvrsiKonkretnuSO(object);
            
            DBBroker.getInstance().potvrdiTransakciju();
        }catch (Exception ex) {
            DBBroker.getInstance().ponistiTransakciju();
            ex.printStackTrace();
        }finally {
            DBBroker.getInstance().zaustaviEntityManager();
        }

    }

    protected abstract void proveriPreduslov(Object object) throws Exception;

    protected abstract void izvrsiKonkretnuSO(Object object) throws Exception;
}
