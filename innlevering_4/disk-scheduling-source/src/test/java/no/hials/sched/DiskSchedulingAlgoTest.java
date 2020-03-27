package no.hials.sched;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for abstract disk scheduling algorithm
 * @author Girts Strazdins, 2016-04-04
 */
public class DiskSchedulingAlgoTest {
    @Test
    public void testCommon() {
        System.out.println("DiskSchedulingAlgo common test");
        // We create a dummy algo with no real methods. But that should not
        // impact our test of setup() method
        DiskSchedulingAlgo algo = new DiskSchedulingAlgo() {
            @Override
            protected int process(List<Integer> cylinders) { return -1; }
        };
        
        // Check some wrong setup values
        algo.setup(0, 10);
        assertEquals(1, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());

        algo.setup(100, 100);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());

        algo.setup(100, -1);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());
        
        // Test if setup works correctly
        algo.setup(100, 10);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());

        // Test moveHead()
        int movement = algo.moveHead(-1);
        assertEquals(-1, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());

        movement = algo.moveHead(15);
        assertEquals(5, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(15, algo.getHeadPosition());
        assertEquals("15", algo.getSchedule());

        movement = algo.moveHead(7);
        assertEquals(8, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(7, algo.getHeadPosition());
        assertEquals("15,7", algo.getSchedule());

        movement = algo.moveHead(70);
        assertEquals(63, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(70, algo.getHeadPosition());
        assertEquals("15,7,70", algo.getSchedule());

        movement = algo.moveHead(100); // This should be out of bounds
        assertEquals(-1, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(70, algo.getHeadPosition());
        assertEquals("15,7,70", algo.getSchedule());

        movement = algo.moveHead(99); // This should be out of bounds
        assertEquals(29, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(99, algo.getHeadPosition());
        assertEquals("15,7,70,99", algo.getSchedule());

        movement = algo.moveHead(0); // This should be ok
        assertEquals(99, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("15,7,70,99,0", algo.getSchedule());

        movement = algo.moveHead(10); // This should be ok
        assertEquals(10, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("15,7,70,99,0,10", algo.getSchedule());

        // Check if the schedule entry is ignored if we are not moving
        movement = algo.moveHead(10); 
        assertEquals(0, movement);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("15,7,70,99,0,10", algo.getSchedule());
    }
}
