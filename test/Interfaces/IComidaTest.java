/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zé Luís
 */
public class IComidaTest {
    
    public IComidaTest() {
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
     * Test of getId method, of class IComida.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        IComida instance = new IComida();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
