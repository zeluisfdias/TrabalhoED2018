/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Zé Luís
 */
public class IComidaNGTest {
    
    public IComidaNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getId method, of class IComida.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        IComida instance = new IComida();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class IComida.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int newId = 0;
        IComida instance = new IComida();
        instance.setId(newId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTamanho method, of class IComida.
     */
    @Test
    public void testGetTamanho() {
        System.out.println("getTamanho");
        IComida instance = new IComida();
        int expResult = 0;
        int result = instance.getTamanho();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTamanho method, of class IComida.
     */
    @Test
    public void testSetTamanho() {
        System.out.println("setTamanho");
        int newTamanho = 0;
        IComida instance = new IComida();
        instance.setTamanho(newTamanho);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
