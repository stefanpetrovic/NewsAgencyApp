/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package njt.beans;

import beans.BrojBean;
import beans.UnosBrojaBean;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import domen.Broj;
import domen.Clanak;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author stefan
 */
public class UnosBrojaBeanTest {
    
    public UnosBrojaBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validacija method, of class UnosBrojaBean.
     */
    @Test
    public void testValidacija() {
        System.out.println("Testiranje u toku ......");
        UnosBrojaBean bean = new UnosBrojaBean();
        boolean expResult = true;
        BrojBean brojBean = new BrojBean();
        Broj broj = new Broj();
        broj.setCena(125.00);
        Date d = new Date();
        broj.setDatumIzdavanja(new Date(d.getTime() - 20000));
        List<Clanak> clanci = new LinkedList<>();
        clanci.add(new Clanak());
        broj.setClanakList(clanci);
        broj.setTiraz(1000);
        brojBean.setBroj(broj);
        bean.setBrojBean(brojBean);
        boolean result = bean.validacija();
        assertEquals("Uneseni podaci nisu validni", expResult, result);
    }
    
}
