/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.domen;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "urednik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Urednik.findAll", query = "SELECT u FROM Urednik u"),
    @NamedQuery(name = "Urednik.findByUrednikID", query = "SELECT u FROM Urednik u WHERE u.urednikID = :urednikID"),
    @NamedQuery(name = "Urednik.findByIme", query = "SELECT u FROM Urednik u WHERE u.ime = :ime"),
    @NamedQuery(name = "Urednik.findByPrezime", query = "SELECT u FROM Urednik u WHERE u.prezime = :prezime"),
    @NamedQuery(name = "Urednik.findByKorisnickoIme", query = "SELECT u FROM Urednik u WHERE u.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Urednik.findByKorisnickaSifra", query = "SELECT u FROM Urednik u WHERE u.korisnickaSifra = :korisnickaSifra")})
public class Urednik implements Serializable {
    private static final long serialVersionUID = -3759733175304697045L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UrednikID")
    private Integer urednikID;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "KorisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "KorisnickaSifra")
    private String korisnickaSifra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urednik")
    private List<Broj> brojList;

    public Urednik() {
    }

    public Urednik(Integer urednikID) {
        this.urednikID = urednikID;
    }

    public Urednik(Integer urednikID, String ime, String prezime, String korisnickoIme, String korisnickaSifra) {
        this.urednikID = urednikID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
    }

    public Integer getUrednikID() {
        return urednikID;
    }

    public void setUrednikID(Integer urednikID) {
        this.urednikID = urednikID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
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
        hash += (urednikID != null ? urednikID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Urednik)) {
            return false;
        }
        Urednik other = (Urednik) object;
        if ((this.urednikID == null && other.urednikID != null) || (this.urednikID != null && !this.urednikID.equals(other.urednikID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Urednik[ urednikID=" + urednikID + " ]";
    }
    
}
