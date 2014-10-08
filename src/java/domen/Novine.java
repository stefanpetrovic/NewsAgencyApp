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
@Table(name = "novine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novine.findAll", query = "SELECT n FROM Novine n"),
    @NamedQuery(name = "Novine.findByNovineID", query = "SELECT n FROM Novine n WHERE n.novineID = :novineID"),
    @NamedQuery(name = "Novine.findByNaziv", query = "SELECT n FROM Novine n WHERE n.naziv = :naziv")})
public class Novine implements Serializable, OpstiDomenskiObjekat {
    
    private static final long serialVersionUID = -1887674140672136582L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NovineID")
    private Integer novineID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "novine")
    private List<Broj> brojList;

    public Novine() {
    }

    public Novine(Integer novineID) {
        this.novineID = novineID;
    }

    public Novine(Integer novineID, String naziv) {
        this.novineID = novineID;
        this.naziv = naziv;
    }

    public Integer getNovineID() {
        return novineID;
    }

    public void setNovineID(Integer novineID) {
        this.novineID = novineID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Broj> getBrojList() {
        return brojList;
    }

    public void setBrojList(List<Broj> brojList) {
        this.brojList = brojList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (novineID != null ? novineID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novine)) {
            return false;
        }
        Novine other = (Novine) object;
        if ((this.novineID == null && other.novineID != null) || (this.novineID != null && !this.novineID.equals(other.novineID))) {
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
        return "novineID";
    }

    @Override
    public String vratiNazivTabele() {
        return "Novine";
    }

    @Override
    public String vratiNazivObjekta() {
        return "n";
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
        return novineID;
    }
    
    
}
