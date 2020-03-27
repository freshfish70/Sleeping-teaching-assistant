package no.hials.sched;

import java.util.List;

/**
 * FCFS Disk scheduling algorithm (First Come - First Served)
 * @author Girts Strazdins 
 * girts.strazdins@gmail.com
 * 2016-04-04 
 */
public class FCFSScheduling extends DiskSchedulingAlgo {
    @Override
    public int process(List<Integer> refList) {
        if (refList == null) {
            // No references means "empty sequence" == no movement
            return 0;
        }
        
        int movement = 0; // Total head movement
        
        // Simply move to each next cylinder number
        for (int cylinderNr: refList) {
            int m = moveHead(cylinderNr);
            if (m < 0) {
                // Error
                return -1;
            }
            // Add the current movement to the total number
            movement += m; 
        }
       
        return movement;
    }

}
