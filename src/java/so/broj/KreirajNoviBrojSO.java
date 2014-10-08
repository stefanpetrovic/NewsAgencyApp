/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.broj;

import db.DBBroker;
import java.util.List;
import domen.Broj;
import domen.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class KreirajNoviBrojSO extends OpstaSO{

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
        List<OpstiDomenskiObjekat> odos = DBBroker.getInstance().vratiSve(new Broj());
        long najveciBroj = 0;
        for (OpstiDomenskiObjekat odo : odos) {
            long id = ((Broj) odo).getBrojID();
            if (id > najveciBroj) {
                najveciBroj = id;
            }
        }
        najveciBroj += 1;
        Broj b = new Broj(najveciBroj);
        DBBroker.getInstance().sacuvaj(b);
        broj = b;
    }
    
}
