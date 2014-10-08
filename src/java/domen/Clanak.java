/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "clanak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clanak.findAll", query = "SELECT c FROM Clanak c"),
    @NamedQuery(name = "Clanak.findByBrojID", query = "SELECT c FROM Clanak c WHERE c.clanakPK.brojID = :brojID"),
    @NamedQuery(name = "Clanak.findByClanakID", query = "SELECT c FROM Clanak c WHERE c.clanakPK.clanakID = :clanakID"),
    @NamedQuery(name = "Clanak.findByNaslov", query = "SELECT c FROM Clanak c WHERE c.naslov = :naslov")})
public class Clanak implements Serializable, OpstiDomenskiObjekat {
    private static final long serialVersionUID = 8268795427777740920L;
    @EmbeddedId
    protected ClanakPK clanakPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Naslov")
    private String naslov;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Sadrzaj")
    private String sadrzaj;
    @JoinColumn(name = "Kategorija", referencedColumnName = "KategorijaID")
    @ManyToOne(optional = false)
    private Kategorija kategorija;
    @JoinColumn(name = "BrojID", referencedColumnName = "BrojID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Broj broj;
    @JoinColumn(name = "Autor", referencedColumnName = "AutorID")
    @ManyToOne(optional = false)
    private Autor autor;

    public Clanak() {
    }

    public Clanak(ClanakPK clanakPK) {
        this.clanakPK = clanakPK;
    }

    public Clanak(ClanakPK clanakPK, String naslov, String sadrzaj) {
        this.clanakPK = clanakPK;
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
    }

    public Clanak(long brojID, long clanakID) {
        this.clanakPK = new ClanakPK(brojID, clanakID);
    }

    public ClanakPK getClanakPK() {
        return clanakPK;
    }

    public void setClanakPK(ClanakPK clanakPK) {
        this.clanakPK = clanakPK;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Broj getBroj() {
        return broj;
    }

    public void setBroj(Broj broj) {
        this.broj = broj;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clanakPK != null ? clanakPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clanak)) {
            return false;
        }
        Clanak other = (Clanak) object;
        if ((this.clanakPK == null && other.clanakPK != null) || (this.clanakPK != null && !this.clanakPK.equals(other.clanakPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Clanak[ clanakPK=" + clanakPK + ", " + naslov + " ]";
    }

    @Override
    public String vratiNazivKljuca() {
        return "clanakID";
    }

    @Override
    public String vratiNazivTabele() {
        return "Clanak";
    }

    @Override
    public String vratiNazivObjekta() {
        return "c";
    }

    @Override
    public String vratiKriterijumPretrage() {
        return "c.autor=:autor";
    }

    @Override
    public Map<String, Object> vratiVrednostKriterijumPretrage() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("autor", autor);
        
        return map;
    }

    @Override
    public Object vratiID() {
        return clanakPK;
    }
    
}
