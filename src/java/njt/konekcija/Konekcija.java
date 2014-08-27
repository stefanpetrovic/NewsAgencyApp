/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njt.konekcija;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import njt.domen.Autor;
import njt.domen.Broj;
import njt.domen.Kategorija;
import njt.domen.Novine;
import njt.domen.Urednik;

/**
 *
 * @author stefan
 */
public class Konekcija {

    private EntityManagerFactory emf;
    private static Konekcija instance;

    private Konekcija() {
        emf = Persistence.createEntityManagerFactory("NovinskaAgencijaPU");
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Urednik ulogujUrednika(Urednik urednik) throws Exception {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query q = em.createQuery("Select u from Urednik u WHERE u.korisnickoIme=:korisnickoIme AND u.korisnickaSifra=:korisnickaSifra");
        q = q.setParameter("korisnickoIme", urednik.getKorisnickoIme()).setParameter("korisnickaSifra", urednik.getKorisnickaSifra());
        System.out.println(q.getSingleResult());
        System.out.println();
        Urednik urednikDB = null;
        try {
            urednikDB = (Urednik) q.getSingleResult();
            em.getTransaction().commit();
            return urednikDB;
        } catch (NoResultException nre) {
            nre.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Sistem ne moze da nadje urednika po zadatim vrednostima.");
        } finally {
            em.close();
        }

    }

    public List<Novine> vratiNovine() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Novine> novine = em.createQuery("Select n From Novine n").getResultList();
            em.getTransaction().commit();
            return novine;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Autor> vratiAutore() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Autor> autori = em.createQuery("Select a From Autor a").getResultList();
            em.getTransaction().commit();
            return autori;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Kategorija> vratiKategorije() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Kategorija> kategorije = em.createQuery("Select k From Kategorija k").getResultList();
            em.getTransaction().commit();
            return kategorije;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public Broj kreirajNoviBroj() {
        EntityManager em = emf.createEntityManager();
        Broj b = null;
        try {
            em.getTransaction().begin();
            long noviRb = (Long) em.createQuery("SELECT MAX(b.brojID) FROM Broj b").getSingleResult();
            em.getTransaction().commit();
            b = new Broj(noviRb + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return b;
    }

    public void sacuvajBroj(Broj broj) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(broj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();

        } finally {
            em.close();
        }
    }

    public List<Broj> pretraziBrojeve(Novine novine) {
        EntityManager em = emf.createEntityManager();
        List<Broj> brojevi = new ArrayList<>();
        try {
            em.getTransaction().begin();
            brojevi = em.createQuery("Select b from Broj b WHERE b.novine=:novine").setParameter("novine", novine).getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return brojevi;
    }

    public void izmeniBroj(Broj broj) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(broj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void obrisiBroj(Broj broj) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Broj b = em.find(Broj.class, broj.getBrojID());
            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
        }catch(Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        System.out.println(Konekcija.getInstance().kreirajNoviBroj());
    }
}
