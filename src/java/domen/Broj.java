/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "broj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Broj.findAll", query = "SELECT b FROM Broj b"),
    @NamedQuery(name = "Broj.findByBrojID", query = "SELECT b FROM Broj b WHERE b.brojID = :brojID"),
    @NamedQuery(name = "Broj.findByDatumIzdavanja", query = "SELECT b FROM Broj b WHERE b.datumIzdavanja = :datumIzdavanja"),
    @NamedQuery(name = "Broj.findByTiraz", query = "SELECT b FROM Broj b WHERE b.tiraz = :tiraz"),
    @NamedQuery(name = "Broj.findByCena", query = "SELECT b FROM Broj b WHERE b.cena = :cena")})
public class Broj implements Serializable, OpstiDomenskiObjekat {
    private static final long serialVersionUID = 946295678327857916L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BrojID")
    private Long brojID;
    @Basic(optional = false)
    @Column(name = "DatumIzdavanja")
    @Temporal(TemporalType.DATE)
    private Date datumIzdavanja;
    @Basic(optional = false)
    @Column(name = "Tiraz")
    private int tiraz;
    @Basic(optional = false)
    @Column(name = "Cena")
    private double cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "broj")
    private List<Clanak> clanakList;
    @JoinColumn(name = "Urednik", referencedColumnName = "UrednikID")
    @ManyToOne(optional = false)
    private Urednik urednik;
    @JoinColumn(name = "Novine", referencedColumnName = "NovineID")
    @ManyToOne(optional = false)
    private Novine novine;

    public Broj() {
    }

    public Broj(Long brojID) {
        this.brojID = brojID;
    }

    public Broj(Long brojID, Date datumIzdavanja, int tiraz, double cena) {
        this.brojID = brojID;
        this.datumIzdavanja = datumIzdavanja;
        this.tiraz = tiraz;
        this.cena = cena;
    }

    public Long getBrojID() {
        return brojID;
    }

    public void setBrojID(Long brojID) {
        this.brojID = brojID;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public int getTiraz() {
        return tiraz;
    }

    public void setTiraz(int tiraz) {
        this.tiraz = tiraz;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @XmlTransient
    public List<Clanak> getClanakList() {
        return clanakList;
    }

    public void setClanakList(List<Clanak> clanakList) {
        this.clanakList = clanakList;
    }

    public Urednik getUrednik() {
        return urednik;
    }

    public void setUrednik(Urednik urednik) {
        this.urednik = urednik;
    }

    public Novine getNovine() {
        return novine;
    }

    public void setNovine(Novine novine) {
        this.novine = novine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojID != null ? brojID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Broj)) {
            return false;
        }
        Broj other = (Broj) object;
        if ((this.brojID == null && other.brojID != null) || (this.brojID != null && !this.brojID.equals(other.brojID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Broj[ brojID=" + brojID + " ]";
    }

    @Override
    public String vratiNazivKljuca() {
        return "brojID";
    }

    @Override
    public String vratiNazivTabele() {
        return "Broj";
    }

    @Override
    public String vratiNazivObjekta() {
        return "b";
    }

    @Override
    public String vratiKriterijumPretrage() {
        return "b.novine=:novine";
    }

    @Override
    public Map<String, Object> vratiVrednostKriterijumPretrage() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("novine", novine);
        
        return map;
    }

    @Override
    public Object vratiID() {
        return brojID;
    }
    
    
}
