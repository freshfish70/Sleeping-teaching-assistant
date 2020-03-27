package no.hials.sched;

import java.util.ArrayList;
import java.util.List;
import no.hials.tools.Tools;

/**
 * Common interface for Disk Scheduling algorithm
 * @author Girts Strazdins 
 * girts.strazdins@gmail.com
 * 2016-04-04 
 */
public abstract class DiskSchedulingAlgo {

    /**
     * List containing sequence of cylinder numbers, as detected by the last
     * process() method. 
     */
    private List<Integer> schedule;

    /** Total number of cylinders on the disk */
    private int cylinders = 0;    
    
    /** Current head position */
    private int head = 0;
    
    /**
     * Process reference list containing cylinder numbers
     * The algorithm should return total number of disk head movement (number of cylinders)
     * It should also update the schedule list - call moveHead() where necessary
     * 
     * Each sub-class (specific algorithm) must implement this method!
     * 
     * @param cylinders - sequence of cylinder access requests 
     * @return total number of disk head movement (number of cylinders) 
     *  or -1 on error
     */
    protected abstract int process(List<Integer> cylinders);
    
    /**
     * Process reference string containing cylinder numbers
     * This is a public function that wraps around an internal algorithm-specific 
     * process() function
     *
     * @param referenceString 
     * @return total number of disk head movement (number of cylinders) 
     *     or -1 on error
     */
    public int processString(String referenceString) {
        // Get the reference sequence as a list
        List<Integer> refList = Tools.stringToArray(referenceString);
        if (refList == null) return -1;

        return process(refList);
    }
    
    
    /**
     * Initialize the disk, clear the schedule if it is not empty
     * Set total number of cylinders on the disk and head position
     * Cylinder numbering starts from 0 and goes up to cylinders-1 !
     * 
     * This function should be called before running the algorithm
     * @param cylinders
     * @param head current head position
     */
    public void setup(int cylinders, int head) {
        if (cylinders > 0) {
            this.cylinders = cylinders;
        } else {
            // Invalid number of cylinders, should have at least one
            this.cylinders = 1;
        }
        
        if (isValidHeadPosition(head)) {
            this.head = head;
        } else {
            // Invalid head position
            this.head = 0;
        }
        
        // Initialize a fresh schedule
        schedule = new ArrayList<>();
    }
    
    /**
     * Get total number of cylinders on the disk
     * @return 
     */
    protected int getCylinderCount() {
        return cylinders;
    }

    /**
     * Get current head position
     * @return 
     */
    protected int getHeadPosition() {
        return head;
    }
    
    /**
     * Check if a particular head position is valid
     * @param pos
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidHeadPosition(int pos) {
        return pos >= 0 && pos < cylinders;
    }
    
    /**
     * Move the head to new position, add the position to the schedule     
     * @param newPosition desired next head position
     * @return number of cylinders moved from the last position, or -1 on error
     */
    protected int moveHead(int newPosition) {
        if (!isValidHeadPosition(newPosition)) {
            return -1;
        }
        
        if (newPosition == head) {
            // No motion
            return 0;
        }
        
        // Calculate the absolute movement distance
        int movement = Math.abs(head - newPosition);
        
        // Move the head, register the movement in the schedule
        this.head = newPosition;
        schedule.add(newPosition);
        
        return movement;
    }

    /**
     * 
     * @return Return the current schedule as a comma-separated string
     *  or empty string if the schedule is empty or invalid
     */
    String getSchedule() {
        if (schedule == null || schedule.isEmpty()) {
            return "";
        }
        // Convert the schedule to an array of strings
        String[] ss = new String[schedule.size()];
        int i = 0;
        for (int c : schedule) {
            ss[i++] = String.valueOf(c);
        }
        return String.join(",", ss);
    }
    
}
