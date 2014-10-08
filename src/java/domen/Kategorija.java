/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "kategorija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k"),
    @NamedQuery(name = "Kategorija.findByKategorijaID", query = "SELECT k FROM Kategorija k WHERE k.kategorijaID = :kategorijaID"),
    @NamedQuery(name = "Kategorija.findByNaziv", query = "SELECT k FROM Kategorija k WHERE k.naziv = :naziv")})
public class Kategorija implements Serializable, OpstiDomenskiObjekat {
    
    private static final long serialVersionUID = 2689053575526749796L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KategorijaID")
    private Integer kategorijaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategorija")
    private List<Clanak> clanakList;

    public Kategorija() {
    }

    public Kategorija(Integer kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public Kategorija(Integer kategorijaID, String naziv) {
        this.kategorijaID = kategorijaID;
        this.naziv = naziv;
    }

    public Integer getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Integer kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Clanak> getClanakList() {
        return clanakList;
    }

    public void setClanakList(List<Clanak> clanakList) {
        this.clanakList = clanakList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kategorijaID != null ? kategorijaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.kategorijaID == null && other.kategorijaID != null) || (this.kategorijaID != null && !this.kategorijaID.equals(other.kategorijaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivKljuca() {
        return "kategorijaID";
    }

    @Override
    public String vratiNazivTabele() {
        return "Kategorija";
    }

    @Override
    public String vratiNazivObjekta() {
        return "k";
    }

    @Override
    public String vratiKriterijumPretrage() {
        return "";
    }

    @Override
    public Map<String, Object> vratiVrednostKriterijumPretrage() {
        return new HashMap<>();
    }

    @Override
    public Object vratiID() {
        return kategorijaID;
    }
    
    
}
