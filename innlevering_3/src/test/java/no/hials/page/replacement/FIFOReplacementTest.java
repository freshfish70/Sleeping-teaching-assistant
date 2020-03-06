package no.hials.page.replacement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for FIFO page replacement algorithm
 * @author Girts Strazdins, 2016-03-11
 */
public class FIFOReplacementTest {
    private final static String E = String.valueOf(FIFOReplacement.EMPTY);
    
    /**
     * Test of process method, of class FIFOReplacement.
     */
    @Test
    public void testProcess() {
        System.out.println("FIFO process");
        FIFOReplacement algo = new FIFOReplacement();
        // Use FIFO with 3 pages
        algo.setup(3);
        
        // Test on empty reference strings
        assertEquals(0, algo.process(null));
        
        // Test on empty reference strings
        assertEquals(0, algo.process(""));
        assertEquals(0, algo.process(",,,"));
        
        // Test on simple reference strings
        int replacements = algo.process("5");
        String frameStatus = algo.getFrameStatus();
        assertEquals(0, replacements);
        assertEquals("5, " + E + ", " + E, frameStatus);

        replacements = algo.process("72");
        frameStatus = algo.getFrameStatus();
        assertEquals("5, 72, " + E, frameStatus);
        assertEquals(0, replacements);

        replacements = algo.process("13");
        frameStatus = algo.getFrameStatus();
        assertEquals("5, 72, 13", frameStatus);
        assertEquals(0, replacements);
        
        // Test some simple replacements
        replacements = algo.process("15");
        frameStatus = algo.getFrameStatus();
        assertEquals("15, 72, 13", frameStatus);
        assertEquals(1, replacements);

        replacements = algo.process("272");
        frameStatus = algo.getFrameStatus();
        assertEquals("15, 272, 13", frameStatus);
        assertEquals(1, replacements);

        replacements = algo.process("383");
        frameStatus = algo.getFrameStatus();
        assertEquals("15, 272, 383", frameStatus);
        assertEquals(1, replacements);
        
        // Test longer sequences, less than frame count
        replacements = algo.process("2, 4");
        frameStatus = algo.getFrameStatus();
        assertEquals("2, 4, 383", frameStatus);
        assertEquals(2, replacements);

        replacements = algo.process("8, 6");
        frameStatus = algo.getFrameStatus();
        assertEquals("6, 4, 8", frameStatus);
        assertEquals(2, replacements);
        
        // Test sequences longer than frame count
        replacements = algo.process("1, 2, 3, 4, 5, 6");
        frameStatus = algo.getFrameStatus();
        assertEquals("6, 4, 5", frameStatus);
        assertEquals(6, replacements);
        
        // Test sequence where some pages are already loaded and 
        // don't result in replacement
        replacements = algo.process("4, 4, 4, 6, 5, 4, 2, 2, 2");
        frameStatus = algo.getFrameStatus();
        assertEquals("6, 2, 5", frameStatus);
        assertEquals(1, replacements);

        // Test sequence which replaces a page and then loads the replaced
        // page again - should get into replacements always
        replacements = algo.process("8, 5, 6, 2");
        frameStatus = algo.getFrameStatus();
        assertEquals("5, 6, 2", frameStatus);
        assertEquals(4, replacements);
    }
   
}
