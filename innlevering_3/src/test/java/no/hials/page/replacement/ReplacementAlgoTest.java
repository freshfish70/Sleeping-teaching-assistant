package no.hials.page.replacement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for abstract replacement algorithm
 * @author Girts Strazdins, 2016-03-11
 */
public class ReplacementAlgoTest {
    private final static String E = String.valueOf(FIFOReplacement.EMPTY);
    
    /**
     * Test of setup method, of class ReplacementAlgorithm.
     */
    @Test
    public void testSetup() {
        System.out.println("ReplacementAlgorithm setup");
        // We create a dummy algo with no real methods. But that should not
        // impact our test of setup() method
        ReplacementAlgorithm algo = new ReplacementAlgorithm() {
            @Override
            public int process(String referenceString) { return 0; }
            @Override
            protected void reset() {}
        };
        assertEquals(E, algo.getFrameStatus());
        algo.setup(1);
        assertEquals(E, algo.getFrameStatus());
        algo.setup(3);
        assertEquals(E + ", " + E + ", " + E, algo.getFrameStatus());
        algo.setup(-1);
        assertEquals(E, algo.getFrameStatus());
    }
}
