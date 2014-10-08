/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so.pomocne;

import db.DBBroker;
import java.util.ArrayList;
import java.util.List;
import domen.Novine;
import domen.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author stefan
 */
public class VratiNovineSO extends OpstaSO{

    private List<Novine> novine = new ArrayList<Novine>();

    public List<Novine> getNovine() {
        return novine;
    }
    
    @Override
    protected void proveriPreduslov(Object object) throws Exception {
        System.out.println("Nema preduslova");
    }

    @Override
    protected void izvrsiKonkretnuSO(Object object) throws Exception {
        List<OpstiDomenskiObjekat> odos = DBBroker.getInstance().vratiSve((Novine) object);
        for (OpstiDomenskiObjekat o : odos) {
            novine.add((Novine) o);
        }
    }
    
}
