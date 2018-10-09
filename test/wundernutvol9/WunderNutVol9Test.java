/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wundernutvol9;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tainalep
 */
public class WunderNutVol9Test {

    @Test(timeout = 10)
    public void testBlinking() {
        System.out.println("testBlinking");
        String input = ".#.##..";
        String result = WunderNutVol9.searchForPattern(input);
        assertTrue("Method searchForPattern not working, should be blinking but got " + result, result == "blinking");

    }
    
    @Test(timeout = 10)
    public void testGlinding() {
        System.out.println("testGlinding");
        String input = ".#.###..";
        String result = WunderNutVol9.searchForPattern(input);
        assertTrue("Method searchForPattern not working, should be gliding but got " + result, result == "gliding");

    }
    
    @Test(timeout = 10)
    public void testVanishing() {
        System.out.println("testVanishing");
        String input = ".#.#.";
        String result = WunderNutVol9.searchForPattern(input);
        assertTrue("Method searchForPattern not working, should be vanishing but got " + result, result == "vanishing");

    }
    
    // is this even possible? I can't come with a pattern that would end in other.. 
    //@Test(timeout = 10)
    public void testOther() {
        System.out.println("testOther");
        String input = "..#..#..#";
        String result = WunderNutVol9.searchForPattern(input);
        assertTrue("Method searchForPattern not working, should be other but got " + result, result == "other");

    }
    
    

}
