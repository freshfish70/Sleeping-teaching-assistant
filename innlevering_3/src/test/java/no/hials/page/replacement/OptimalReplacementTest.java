package no.hials.page.replacement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Optimal page replacement algorithm
 * @author Girts Strazdins, 2016-03-11 (only a template)
 */
public class OptimalReplacementTest {
    /**
     * Test of process method, of class OptimalReplacement.
     */
    @Test
    public void testProcess() {
        System.out.println("Optimal Replacement process test");
        OptimalReplacement algo = new OptimalReplacement();

        // Use the book example with 3 frames and the given reference string
        algo.setup(3);
        String ref = "7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1";
        int replacements = algo.process(ref);
        String frameStatus = algo.getFrameStatus();
        assertEquals(6, replacements);
        assertEquals("7, 0, 1", frameStatus);

        // TODO - add additional Unit tests here

        OptimalReplacement best = new OptimalReplacement();

       /** This unit thest checks that the pageReplacement
        * algorithm does the corrent number of replacement pages.
        * We calculated the result on paper, first, and then
        * assert that the alogrithm will do eqaul replacements
        */

        best.setup(3);
        String pages = "3,7,2,0,1,5,9,3,4,2,4,9,7,3";
        int pageReplacements = best.process(pages);
        String pageFrameStatus = best.getFrameStatus();
        assertEquals(7,pageReplacements);
    }


}
