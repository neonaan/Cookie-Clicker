package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MilestoneTest {

    Milestone milestone1;
    Milestone milestone2;

    @BeforeEach
    public void setup() {
        milestone1 = new Milestone(1);
        milestone2 = new Milestone(100);
    }

    @Test
    public void testMilestone() {
        assertEquals(1, milestone1.getMilestoneAmount());
        assertFalse(milestone1.getStatus());

        assertEquals(100, milestone2.getMilestoneAmount());
        assertFalse(milestone2.getStatus());
    }

    @Test
    public void testGetStringFormStatus() {
        assertEquals("unreached", milestone1.getStringFormStatus());
        milestone1.setReached();
        assertEquals("reached", milestone1.getStringFormStatus());

        milestone2.setReached();
        assertEquals("reached", milestone2.getStringFormStatus());
    }

    @Test
    public void testGetMilestoneAndStatus() {
        assertEquals("acquire 1 cookies: unreached", milestone1.getMilestoneAndStatus());
        milestone1.setReached();
        assertEquals("acquire 1 cookies: reached", milestone1.getMilestoneAndStatus());

        assertEquals("acquire 100 cookies: unreached", milestone2.getMilestoneAndStatus());
        milestone2.setReached();
        assertEquals("acquire 100 cookies: reached", milestone2.getMilestoneAndStatus());
    }

}