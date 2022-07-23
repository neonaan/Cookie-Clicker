package model;

import java.util.LinkedList;

// Represents a list of milestones set
public class MilestonesSet {
    private LinkedList<Milestone> milestones;

    // EFFECTS: Initializes an empty list of milestones that have been set.
    public MilestonesSet() {
        milestones = new LinkedList<>();
    }

    // REQUIRES: Milestone cannot already be in the list
    // MODIFIES: this
    // EFFECTS: Adds a milestone to the end of the list
    public void addMilestone(Milestone milestone) {
        milestones.add(milestone);
    }

}
