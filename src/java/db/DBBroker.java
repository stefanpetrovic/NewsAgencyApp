/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author stefan
 */
public class DBBroker {
    
    private static DBBroker instance;
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    private DBBroker() {
        emf = Persistence.createEntityManagerFactory("NovinskaAgencijaPU");
    }
    
    public static DBBroker getInstance() {
        if (instance == null) instance = new DBBroker();
        return instance;
    }
    
    public void pokreniEntityManager() {
        em = emf.createEntityManager();
    }
    
    public void zaustaviEntityManager() {
        if (em != null) {
            em.close();
        }
    }
    
    public void pokreniTransakciju() {
        if (em != null) {
            em.getTransaction().begin();
        }
    }
    
    public void potvrdiTransakciju() {
        if (em != null) {
            em.getTransaction().commit();
        }
    }
    
    public void ponistiTransakciju() {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
    
    public OpstiDomenskiObjekat vratiPoID(OpstiDomenskiObjekat odo) {
        return (OpstiDomenskiObjekat) em.find(odo.getClass(), odo.vratiID());
    }
    
    public OpstiDomenskiObjekat vratiJedan(OpstiDomenskiObjekat odo){
        Query q = em.createQuery("Select " + odo.vratiNazivObjekta() +" from " + odo.vratiNazivTabele() + " " + odo.vratiNazivObjekta() + " WHERE " + odo.vratiKriterijumPretrage());
        Map<String, Object> mapa = odo.vratiVrednostKriterijumPretrage();
        for (Entry<String, Object> e : mapa.entrySet()) {
            q = q.setParameter(e.getKey(), e.getValue());
        }
        return (OpstiDomenskiObjekat) q.getSingleResult();
    }
    
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) {
        return (List<OpstiDomenskiObjekat>) em.createQuery("Select " + odo.vratiNazivObjekta() + " From " + odo.vratiNazivTabele() + " " + odo.vratiNazivObjekta()).getResultList();
    }
    
    public List<OpstiDomenskiObjekat> vratiNeke(OpstiDomenskiObjekat odo) {
        Query q = em.createQuery("Select " + odo.vratiNazivObjekta() + " from " + odo.vratiNazivTabele() + " " + odo.vratiNazivObjekta() + " WHERE " + odo.vratiKriterijumPretrage());
        Map<String, Object> mapa = odo.vratiVrednostKriterijumPretrage();
        for (Entry<String, Object> e : mapa.entrySet()) {
            q.setParameter(e.getKey(), e.getValue());
        }
        return q.getResultList();
    }
    
    public void sacuvaj(OpstiDomenskiObjekat odo) {
        em.persist(odo);
    }
    
    public void izmeni(OpstiDomenskiObjekat odo) {
        em.merge(odo);
    }
    
    public void obrisi(OpstiDomenskiObjekat odo) {
        OpstiDomenskiObjekat obj = em.find(odo.getClass(), odo.vratiID());
        em.remove(obj);
    }
}
