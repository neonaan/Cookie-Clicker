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

    // EFFECTS: returns length of milestones
    public int getLength() {
        return milestones.size();
    }

    //EFFECTS: returns true if the milestone is in the set
    public boolean contains(Milestone m) {
        if (milestones.contains(m)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: displays all the milestones set so far
    public LinkedList<String> milestonesSetDisplay() {
        LinkedList<String> milestoneLog = new LinkedList<>();
        for (Milestone m: milestones) {
            milestoneLog.add(m.getMilestoneAndStatus());
        }
        return milestoneLog;
    }

    // MODIFIES: MilestonesSet
    // EFFECTS: updates the status of each milestone according to the amount of cookies acquired
    public void updateMilestonesStatuses(int cookieCount) {
        for (Milestone m: this.milestones) {
            if (cookieCount >= m.getMilestoneAmount()) {
                m.setReached();
            }
        }
    }

}
