/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stefan
 */
@Embeddable
public class ClanakPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "BrojID")
    private long brojID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ClanakID")
    private long clanakID;

    public ClanakPK() {
    }

    public ClanakPK(long brojID, long clanakID) {
        this.brojID = brojID;
        this.clanakID = clanakID;
    }

    public long getBrojID() {
        return brojID;
    }

    public void setBrojID(long brojID) {
        this.brojID = brojID;
    }

    public long getClanakID() {
        return clanakID;
    }

    public void setClanakID(long clanakID) {
        this.clanakID = clanakID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) brojID;
        hash += (int) clanakID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClanakPK)) {
            return false;
        }
        ClanakPK other = (ClanakPK) object;
        if (this.brojID != other.brojID) {
            return false;
        }
        if (this.clanakID != other.clanakID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.ClanakPK[ brojID=" + brojID + ", clanakID=" + clanakID + " ]";
    }
    
}
