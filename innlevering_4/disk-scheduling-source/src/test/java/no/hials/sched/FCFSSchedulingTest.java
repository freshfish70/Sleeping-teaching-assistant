package no.hials.sched;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for FCFS scheduling algorithm
 * @author Girts Strazdins, 2016-04-04
 */
public class FCFSSchedulingTest {
    /**
     * Test how the class behaves with invalid input
     */
    @Test
    public void errorHandlingTest() {
        System.out.println("FCFSScheduling error handling test");
        FCFSScheduling algo = new FCFSScheduling();
        
        algo.setup(100, 10);
        
        int movement;
        movement = algo.processString(null);
        assertEquals(movement, -1);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());
        
        movement = algo.processString("");
        assertEquals(movement, 0);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());

        // Test wrong head positions
        movement = algo.processString("-3");
        assertEquals(movement, -1);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());
        
        // Test wrong head positions
        movement = algo.processString("100");
        assertEquals(movement, -1);
        assertEquals(100, algo.getCylinderCount());
        assertEquals(10, algo.getHeadPosition());
        assertEquals("", algo.getSchedule());
    }
    
    /**
     * Some simple tests
     */
    @Test
    public void simpleTest() {
        System.out.println("FCFSScheduling simple test");
        FCFSScheduling algo = new FCFSScheduling();
        
        algo.setup(50, 10);
        
        int movement;
        movement = algo.processString("0");
        assertEquals(10, movement);
        assertEquals(50, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("0", algo.getSchedule());
        
        movement = algo.processString("-1");
        assertEquals(-1, movement);
        assertEquals(50, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("0", algo.getSchedule());

        movement = algo.processString("99");
        assertEquals(-1, movement);
        assertEquals(50, algo.getCylinderCount());
        assertEquals(0, algo.getHeadPosition());
        assertEquals("0", algo.getSchedule());

        movement = algo.processString("49");
        assertEquals(49, movement);
        assertEquals(50, algo.getCylinderCount());
        assertEquals(49, algo.getHeadPosition());
        assertEquals("0,49", algo.getSchedule());
        
        algo.setup(60, 0);

        movement = algo.processString("49,12,-2,8");
        assertEquals(-1, movement);
        assertEquals(60, algo.getCylinderCount());
        assertEquals(12, algo.getHeadPosition());
        assertEquals("49,12", algo.getSchedule());

        // Reset the schedule
        algo.setup(60, 10);

        movement = algo.processString("40,20,50,30,10,45");
        assertEquals(155, movement);
        assertEquals(60, algo.getCylinderCount());
        assertEquals(45, algo.getHeadPosition());
        assertEquals("40,20,50,30,10,45", algo.getSchedule());
    }
    
    /**
     * Test the book example
     */
    @Test
    public void bookTest() {
        FCFSScheduling algo = new FCFSScheduling();
        
        algo.setup(200, 53);
        
        int movement;
        movement = algo.processString("98,183,37,122,14,124,65,67");
        assertEquals(640, movement);
        assertEquals(200, algo.getCylinderCount());
        assertEquals(67, algo.getHeadPosition());
        assertEquals("98,183,37,122,14,124,65,67", algo.getSchedule());
    }
}
