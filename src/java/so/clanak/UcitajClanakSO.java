/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.clanak;

import db.DBBroker;
import domen.Clanak;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class UcitajClanakSO extends OpstaSO{

    private Clanak clanak;

    public Clanak getClanak() {
        return clanak;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        clanak = (Clanak) DBBroker.getInstance().vratiPoID((Clanak) object);
    }
    
}
