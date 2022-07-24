package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MilestonesSetTest {
    MilestonesSet ms;
    Milestone m1;
    Milestone m2;

    @BeforeEach
    public void setup() {
        ms = new MilestonesSet();
        m1 = new Milestone(1);
        m2 = new Milestone(10);
    }

    @Test
    public void testMilestonesSet() {
        assertEquals(0, ms.getLength());
    }

    @Test
    public void testAddMilestone() {
        ms.addMilestone(m1);
        assertTrue(ms.contains(m1));
        assertFalse(ms.contains(m2));

        ms.addMilestone(m2);
        assertTrue(ms.contains(m1));
        assertTrue(ms.contains(m2));
    }

    @Test
    public void testGetLength() {
        assertEquals(0, ms.getLength());

        ms.addMilestone(m1);
        assertEquals(1, ms.getLength());

        ms.addMilestone(m2);
        assertEquals(2, ms.getLength());
    }

    @Test
    public void testContains() {
        assertFalse(ms.contains(m1));
        assertFalse(ms.contains(m2));

        ms.addMilestone(m1);
        assertTrue(ms.contains(m1));
        assertFalse(ms.contains(m2));

        ms.addMilestone(m2);
        assertTrue(ms.contains(m1));
        assertTrue(ms.contains(m2));
    }

    @Test
    public void testMilestonesSetDisplay() {
        ms.addMilestone(m1);
        LinkedList<String> testList = new LinkedList<>();
        testList.add("acquire 1 cookies: unreached");
        assertEquals(testList, ms.milestonesSetDisplay());

        ms.addMilestone(m2);
        testList.add("acquire 10 cookies: unreached");
        assertEquals(testList, ms.milestonesSetDisplay());

        m2.setReached();
        testList.removeLast();
        testList.add("acquire 10 cookies: reached");
        assertEquals(testList, ms.milestonesSetDisplay());
    }

    @Test
    public void testUpdateMilestonesStatuses() {
        int counter = 0;
        LinkedList<String> testList = new LinkedList<>();
        ms.addMilestone(m1);
        ms.addMilestone(m2);
        testList.add("acquire 1 cookies: unreached");
        testList.add("acquire 10 cookies: unreached");
        ms.updateMilestonesStatuses(counter);
        assertEquals(testList, ms.milestonesSetDisplay());

        counter = 2;
        testList.removeFirst();
        testList.addFirst("acquire 1 cookies: reached");
        ms.updateMilestonesStatuses(counter);
        assertEquals(testList, ms.milestonesSetDisplay());

        counter = 10;
        testList.removeLast();
        testList.removeLast();
        testList.add("acquire 1 cookies: reached");
        testList.add("acquire 10 cookies: reached");
        ms.updateMilestonesStatuses(counter);
        assertEquals(testList, ms.milestonesSetDisplay());
    }
}
