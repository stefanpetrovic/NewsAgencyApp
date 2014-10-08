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
public class ZapamtiClanakSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        Clanak c = (Clanak) object;
        if (c.getAutor() == null || c.getBroj() == null || c.getKategorija() == null || c.getNaslov() == null || c.getSadrzaj() == null) {
            throw new Exception("Clanak nezadovoljava ogranicenja");
        }
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        DBBroker.getInstance().izmeni((Clanak) object);
    }
    
}
