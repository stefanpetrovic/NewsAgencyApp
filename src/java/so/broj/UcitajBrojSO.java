/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.broj;

import db.DBBroker;
import domen.Broj;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class UcitajBrojSO extends OpstaSO{

    private Broj broj;

    public Broj getBroj() {
        return broj;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        broj = (Broj) DBBroker.getInstance().vratiPoID((Broj) object);
    }
    
}
