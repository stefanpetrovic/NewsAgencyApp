/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.broj;

import db.DBBroker;
import domen.Broj;
import domen.Clanak;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class ZapamtiBrojSO extends OpstaSO{
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        Broj b = (Broj) object;
        if (b.getDatumIzdavanja() == null || b.getNovine() == null || b.getUrednik() == null) {
            throw new Exception("Broj nezadovoljava prosto vrednosno ogranicenje.");
        }
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        DBBroker.getInstance().izmeni((Broj) object);
        for (Clanak c : ((Broj) object).getClanakList()) {
            DBBroker.getInstance().sacuvaj(c);
        }
    }
    
}
