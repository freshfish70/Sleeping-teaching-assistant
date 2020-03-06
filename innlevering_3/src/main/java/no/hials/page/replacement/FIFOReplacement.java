package no.hials.page.replacement;

import java.util.List;

/**
 * FIFO Page Replacement algorithm
 * @author Girts Strazdins 
 * girts.strazdins@gmail.com
 * 2016-03-11 
 */
public class FIFOReplacement extends ReplacementAlgorithm {
    
    // Which frame to use for page replacement next time
    private int currentFrame;

    @Override
    protected void reset() {
        currentFrame = 0;
    }
    
    @Override
    public int process(String referenceString) {
        // Get the reference list as an array
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;
        
        int replacements = 0; // How many page replacements made
        
        // Check all virtual page accesses
        for (int page: pageReferences) {
            // If the page is not loaded into physical frame
            if (!isLoaded(page)) {
                // Page it in (load it)
                if (pageIn(currentFrame, page)) {
                    // If there was a page replacement, register it
                    replacements++;
                }
                moveCurrentPointer();
                System.out.println(getFrameStatus());
            }
        }
       
        return replacements;
    }

    /**
     * Move the current circular FIFO pointer - next page to be replaced
     */
    private void moveCurrentPointer() {
        currentFrame++;
        // If it overflows, go to first frame again
        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
    }
}
