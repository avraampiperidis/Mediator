/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author piper
 */
public class SkeletonBuilderTest {
    
    public SkeletonBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of build method, of class SkeletonBuilder.
     */
    @Test
    public void testBuild() {
        System.out.println("build");
        SkeletonBuilder instance = null;
        String expResult = "";
        String result = instance.build();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
