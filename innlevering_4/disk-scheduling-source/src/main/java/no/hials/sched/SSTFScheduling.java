package no.hials.sched;

import java.util.List;

/**
 * SSTF Disk scheduling algorithm (Shortest Seek Time First)
 * 
 * Fill in your code in this class!
 * 
 * 2016-04-04 
 */
public class SSTFScheduling extends DiskSchedulingAlgo {
    @Override
    public int process(List<Integer> refList) {
        if (refList == null) {
            // No references means "empty sequence" == no movement
            return 0;
        }
        
        int movement = 0; // Total head movement
        
        // TODO - fill in your algorithm here
        // Detect which position to move to in each step and call
        // moveHead()
        // See FCFSScheduling as an example
       
        return movement;
    }

}
