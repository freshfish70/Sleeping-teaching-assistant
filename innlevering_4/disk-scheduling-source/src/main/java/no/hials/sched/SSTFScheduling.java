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
        int headPos = this.getHeadPosition();
        
        // TODO - fill in your algorithm here
        // Detect which position to move to in each step and call
        // moveHead()
        // See FCFSScheduling as an example

        List<Integer> posList = refList;
        int s = refList.size();

        for (int i = 0; i < s; i++) {
            int nextPosIndex = this.getNextPos(this.getHeadPosition(), posList);
            int nextPos = posList.get(nextPosIndex);
            int mv = this.moveHead(nextPos);

            if (mv < 0) {
                // Error
                return -1;
            }

            movement += mv;
            posList.remove(nextPosIndex);

        }

        return movement;
    }


    private int getDelta(int oldPos, int newPos){
        return Math.abs(oldPos - newPos);
    }

    private int getNextPos(int currPos,List<Integer> remainingRefs){
        int candIndex = 0;
        int candDelta = this.getDelta(currPos, remainingRefs.get(0));

        for (int i = 0; i < remainingRefs.size(); i++) {
            int d = this.getDelta(currPos, remainingRefs.get(i));
            if (d < candDelta) {
                candIndex = i;
                candDelta = d;
            }
        }
        return candIndex;
    }

}
