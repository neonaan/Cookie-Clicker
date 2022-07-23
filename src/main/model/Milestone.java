package model;

// Represents a milestone (a certain amount of cookies to be reached)
// having a status ("reached" or "unreached")
public class Milestone {
    private int cookieMilestone;
    private boolean status;


    // EFFECTS: Constructs a milestone with a set amount of cookies to be reached.
    //          The milestone is given an unreached status when it is set.
    public Milestone(int amount) {
        cookieMilestone = amount;
        status = false;
    }

    // EFFECTS: returns the amount of cookies wished to be acquired by the milestone
    public int getMilestoneAmount() {
        return cookieMilestone;
    }

    // EFFECTS: returns the status of the milestone.
    public boolean isReached() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: Changes the status of the milestone to reached.
    public void setReached() {
        status = true;
    }


}
