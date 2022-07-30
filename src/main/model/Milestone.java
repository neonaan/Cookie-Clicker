package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a milestone (a certain amount of cookies to be reached)
// having a status ("reached" or "unreached")
public class Milestone implements Writable {
    private int cookieMilestone;
    private boolean status;


    // REQUIRES: target >= 0
    // EFFECTS: Constructs a milestone with a set amount of cookies to be reached.
    //          The milestone is given an unreached status when it is set.
    public Milestone(int target) {
        cookieMilestone = target;
        status = false;
    }

    // EFFECTS: returns the amount of cookies wished to be acquired by the milestone
    public int getMilestoneAmount() {
        return cookieMilestone;
    }

    // EFFECTS: returns the status of the milestone as a boolean.
    public boolean getStatus() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: Changes the status of the milestone to reached.
    public void setReached() {
        status = true;
    }

    // EFFECTS: Returns status as a string, either "reached" or "unreached"
    public String getStringFormStatus() {
        if (status) {
            return "reached";
        } else {
            return "unreached";
        }
    }

    // EFFECTS: returns milestone in format: "acquire cookieMilestone: status"
    public String getMilestoneAndStatus() {
        return ("acquire " + Integer.toString(cookieMilestone) + " cookies: " + getStringFormStatus());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goal", cookieMilestone);
        json.put("status", status);
        return json;
    }
}
