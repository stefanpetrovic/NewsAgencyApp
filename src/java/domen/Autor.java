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
@Table(name = "autor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a"),
    @NamedQuery(name = "Autor.findByAutorID", query = "SELECT a FROM Autor a WHERE a.autorID = :autorID"),
    @NamedQuery(name = "Autor.findByIme", query = "SELECT a FROM Autor a WHERE a.ime = :ime"),
    @NamedQuery(name = "Autor.findByPrezime", query = "SELECT a FROM Autor a WHERE a.prezime = :prezime")})
public class Autor implements Serializable, OpstiDomenskiObjekat {
    
    private static final long serialVersionUID = 6282369404419114974L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AutorID")
    private Integer autorID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autor")
    private List<Clanak> clanakList;

    public Autor() {
    }

    public Autor(Integer autorID) {
        this.autorID = autorID;
    }

    public Autor(Integer autorID, String ime, String prezime) {
        this.autorID = autorID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getAutorID() {
        return autorID;
    }

    public void setAutorID(Integer autorID) {
        this.autorID = autorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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
        hash += (autorID != null ? autorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.autorID == null && other.autorID != null) || (this.autorID != null && !this.autorID.equals(other.autorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivKljuca() {
        return "autorID";
    }

    @Override
    public String vratiNazivTabele() {
        return "Autor";
    }

    @Override
    public String vratiNazivObjekta() {
        return "a";
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
        return autorID;
    }
    
    
}
