package no.hials.page.replacement;

import java.util.List;

/**
 * Optimal Replacement algorithm
 * Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {
    
    // TODO - add some state variables here, if you need any

    @Override
    protected void reset() {
        // TODO - do preparation/initilization here, if needed
    }
    
    @Override
    public int process(String referenceString) {
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;
        
        int replacements = 0; // How many page replacements made
        
        // TODO - process the reference string here. You can see FIFOReplacement
        // as an example. But remember, that FIFO uses a different algorithm.
        // This class should use Optimal Replacement algorithm, described
        // in Section 9.4.
        // Get the reference list as an array
       
        return replacements;
    }

    // TODO - create any helper methods here if you need any
}
