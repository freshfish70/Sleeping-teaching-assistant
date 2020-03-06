package no.hials.page.replacement;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Girts Strazdins, 2016-03-11
 */
public class ToolsTest {
    
    /**
     * Test of stringToArray method, of class Tools.
     */
    @Test
    public void testStringToArray() {
        System.out.println("stringToArray");
        ArrayList<Integer> a = new ArrayList<>();
        // Test different variations of empty lists
        assertEquals(a, Tools.stringToArray(null));
        assertEquals(a, Tools.stringToArray(""));
        assertEquals(a, Tools.stringToArray("     "));
        assertEquals(a, Tools.stringToArray(" ,  ,,,"));
        // Test a simple list with one value
        a.add(7);
        assertEquals(a, Tools.stringToArray("7"));
        // Test non-numeric values
        assertEquals(a, Tools.stringToArray("7,nice,try"));
        assertEquals(a, Tools.stringToArray("7,8nice,9try"));
        // Test a multi-value list with some empty elements and negative numbers
        a.add(78);
        a.add(5);
        a.add(6);
        a.add(-82);
        a.add(7);
        a.add(7);
        a.add(7);
        assertEquals(a, 
                Tools.stringToArray("7, 78,5,6,-82,,7,7,7"));
    }
    
}
