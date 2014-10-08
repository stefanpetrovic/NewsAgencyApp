/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.broj;

import db.DBBroker;
import java.util.ArrayList;
import java.util.List;
import domen.Broj;
import domen.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class PretraziBrojeveSO extends OpstaSO{

    private List<Broj> brojevi = new ArrayList<>();

    public List<Broj> getBrojevi() {
        return brojevi;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        List<OpstiDomenskiObjekat> odos = DBBroker.getInstance().vratiNeke((Broj) object);
        for (OpstiDomenskiObjekat odo : odos) {
            brojevi.add((Broj) odo);
        }
    }
    
}
