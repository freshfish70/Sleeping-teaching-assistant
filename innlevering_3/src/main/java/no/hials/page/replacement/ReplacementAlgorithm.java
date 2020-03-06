package no.hials.page.replacement;

/**
 * Common interface for Page Replacement algorithm
 * @author Girts Strazdins 
 * girts.strazdins@gmail.com
 * 2016-03-11 
 */
public abstract class ReplacementAlgorithm {
    // Constant meaning "Empty page"
    public static final int EMPTY = -1;

    /**
     * Array containing physical frames: if an element holds 
     * a non-negative integer, it represents number of virtual page currently 
     * loaded in this physical frame. EMPTY means there is no page loaded in
     * this physical frame.
     */
    protected int[] frames;

    
    /**
     * Process reference string containing virtual page references
     * The algorithm should return total number of page replacements made
     * (not including the initial page faults which just load page into RAM
     * without replacing anything)
     * 
     * Each sub-class (specific algorithm) must implement this method!
     * @param referenceString 
     * @return the number of pages replaced during handling of the reference string
     */
    public abstract int process(String referenceString);
    
    /**
     * Reset parsing - initialize the algorithm. 
     * Each sub-class (specific algorithm) must implement this method!
     */
    protected abstract void reset();
    
    
    /**
     * Set number of physical frames to be used by the algorithm.
     * This function should be called before running the algorithm
     * @param numberOfPhysicalFrames
     */
    public void setup(int numberOfPhysicalFrames) {
        if (numberOfPhysicalFrames > 0) {
            // Initialize frame array, marke all as empty
            this.frames = new int[numberOfPhysicalFrames];
            for (int i = 0; i < numberOfPhysicalFrames; ++i) {
                this.frames[i] = EMPTY;
            }
        } else {
            // Invalid number of frames
            this.frames = null;
        }
        // Do algorithm-specific initialization
        this.reset();
    }

    /**
     * Return true if the page is already loaded into physical RAM frame
     * @param page virtual page number
     * @return 
     */
    protected boolean isLoaded(int page) {
        for (int frame: frames) {
            if (frame == page) return true;
        }
        return false;
    }
    
    /**
     * Load a virtual page into a physical RAM frame
     * @param frameNumber the page will be loaded to this frame
     * @param page the page to be loaded
     * @return true if page replacement was made, false otherwise (if there
     * was a free frame to use, or if frames are not set up properly, or frame
     * index is wrong)
     * Does not check if the frame is already in RAM!
     */
    protected boolean pageIn(int frameNumber, int page) {
        if (frames == null || frameNumber < 0 || frameNumber >= frames.length) {
            return false;
        }
        boolean replaced = false;
        if (frames[frameNumber] != EMPTY) {
            // There was an old victim frame already loaded, we replace it
            replaced = true;
        }

        // Load the new page
        frames[frameNumber] = page;

        return replaced;
    }
    
   
    /**
     * Get current frame status as a list of page numbers
     * 
     * @return loaded page numbers separated by ", " (comma and space) 
     * For empty frames EMPTY constant will represent the frame
     * If the frame list is not set up properly, will return EMPTY constant
     */
    public String getFrameStatus() {
        if (frames != null) {
            // Converts frames to string array, then join them together
            // Just because Jave does not have join() functionfor integer array
            String s[] = new String[frames.length];
            for (int i = 0; i < frames.length; ++i) {
                s[i] = String.valueOf(frames[i]);
            }
            return String.join(", ", s);
        } else {
            return String.valueOf(EMPTY);
        }
    }
    
}
