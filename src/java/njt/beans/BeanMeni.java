/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author stefan
 */
@ManagedBean
public class BeanMeni {
    
    public String unosNovogBroja() {
        return "broj";
    }
    
    public String izmenaBroja() {
        return "izmena-broja";
    }
    
    public String pretragaBroja() {
        return "pretraga-broja";
    }
}
