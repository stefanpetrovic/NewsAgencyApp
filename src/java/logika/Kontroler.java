/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import domen.Autor;
import domen.Broj;
import domen.Clanak;
import domen.ClanakPK;
import domen.Novine;
import domen.OpstiDomenskiObjekat;
import domen.Urednik;
import so.broj.IzmeniBrojSO;
import so.broj.KreirajNoviBrojSO;
import so.broj.ObrisiBrojSO;
import so.broj.PretraziBrojeveSO;
import so.broj.ZapamtiBrojSO;
import so.broj.UcitajBrojSO;
import so.clanak.ZapamtiClanakSO;
import so.clanak.ObrisiClanakSO;
import so.clanak.PretraziClankeSO;
import so.clanak.UcitajClanakSO;
import so.pomocne.VratiAutoreIKategorije;
import so.pomocne.VratiNovineSO;
import so.urednik.NadjiUrednikaSO;

/**
 *
 * @author stefan
 */
public class Kontroler {

    private EntityManagerFactory emf;
    private static Kontroler instance;

    private Kontroler() {
        emf = Persistence.createEntityManagerFactory("NovinskaAgencijaPU");
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Urednik ulogujUrednika(Urednik urednik) throws Exception {
        NadjiUrednikaSO nuso = new NadjiUrednikaSO();
        nuso.izvrsiOperaciju(urednik);
        return nuso.getUrednik();
    }

    public List<Novine> vratiNovine() throws Exception {
        VratiNovineSO vnso = new VratiNovineSO();
        vnso.izvrsiOperaciju(new Novine());
        return vnso.getNovine();
    }

    public Map<String, List<OpstiDomenskiObjekat>> vratiAutoreIKategorije() throws Exception {
        VratiAutoreIKategorije vaik = new VratiAutoreIKategorije();
        vaik.izvrsiOperaciju(null);
        return vaik.getAutoriIKategorije();
    }

    public Broj kreirajNoviBroj() throws Exception {
        KreirajNoviBrojSO knbso = new KreirajNoviBrojSO();
        knbso.izvrsiOperaciju(null);
        return knbso.getBroj();
    }

    public void sacuvajBroj(Broj broj) throws Exception {
        ZapamtiBrojSO sbso = new ZapamtiBrojSO();
        sbso.izvrsiOperaciju(broj);
    }

    public List<Broj> pretraziBrojeve(Novine novine) throws Exception {
        PretraziBrojeveSO pbso = new PretraziBrojeveSO();
        Broj b = new Broj();
        b.setNovine(novine);
        pbso.izvrsiOperaciju(b);
        return pbso.getBrojevi();
    }
    
    public List<Clanak> pretraziClanke(Autor izabraniAutor) throws Exception {
        PretraziClankeSO pcso = new PretraziClankeSO();
        Clanak c = new Clanak();
        c.setAutor(izabraniAutor);
        pcso.izvrsiOperaciju(c);
        return pcso.getClanci();
    }

    public void izmeniBroj(Broj broj) throws Exception {
        IzmeniBrojSO ibso = new IzmeniBrojSO();
        ibso.izvrsiOperaciju(broj);
    }
    
    public void izmeniClanak(Clanak clanak) throws Exception {
        ZapamtiClanakSO icso = new ZapamtiClanakSO();
        icso.izvrsiOperaciju(clanak);
    }

    public void obrisiBroj(Broj broj) throws Exception {
        ObrisiBrojSO obso = new ObrisiBrojSO();
        obso.izvrsiOperaciju(broj);
    }
    
    public void obrisiClanak(Clanak clanak) throws Exception {
        ObrisiClanakSO ocso = new ObrisiClanakSO();
        ocso.izvrsiOperaciju(clanak);
    }
    
    public Broj vratiBroj(long brojID) throws Exception {
        UcitajBrojSO ubso = new UcitajBrojSO();
        Broj b = new Broj(brojID);
        ubso.izvrsiOperaciju(b);
        return ubso.getBroj();
    }
    
    public Clanak vratiClanak(long clanakID) throws Exception {
        UcitajClanakSO ucso = new UcitajClanakSO();
        Clanak c = new Clanak(new ClanakPK(0, clanakID));
        ucso.izvrsiOperaciju(c);
        return ucso.getClanak();
    }
    
    
}
