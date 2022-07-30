package persistence;

import model.Milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMilestone(int amount, boolean status, Milestone milestone) {
        assertEquals(amount, milestone.getMilestoneAmount());
        assertEquals(status, milestone.getStatus());
    }
}