package ui;

import model.Milestone;
import model.MilestonesSet;

import java.util.Scanner;

// Cookie Clicker game application
public class CookieApp {
    private Scanner input;
    private int cookieCount;
    private MilestonesSet milestones;

    // EFFECTS: runs the Cookie Clicker game application. User starts with no cookies and no milestones.
    public CookieApp() {
        cookieCount = 0;
        milestones = new MilestonesSet();
        runCookie();
    }

    // inspired by tellerApp
    // MODIFIES: this
    // EFFECTS: Processes user input
    public void runCookie() {
        boolean endApp = false;
        String command = null;

        startGame();
        while (!endApp) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                endApp = true;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for playing! \nYou ended the game with "
                + Integer.toString(cookieCount) + " cookies.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("c")) {
            increaseCookieCount();
        } else if (command.equals("m")) {
            setupMilestone();
        } else if (command.equals("v")) {
            showMilestonesSet();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> acquire cookie");
        System.out.println("\tm -> set milestone");
        System.out.println("\tv -> view milestones");
        System.out.println("\te -> end game");
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    public void startGame() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: increases cookie count by 1
    public void increaseCookieCount() {
        cookieCount++;
        System.out.println("You now have " + Integer.toString(cookieCount) + " cookies!");
    }

    // REQUIRES: input must be an integer >=0
    // MODIFIES: this, MilestonesSet
    // EFFECTS: Prompts user to create a milestone and adds it to the MilestoneSet
    public void setupMilestone() {
        String amount = null;
        System.out.println("Set the amount of cookies you wish to receive: ");
        amount = input.next();
        int integerAmount = Integer.valueOf(amount);
        Milestone milestone = new Milestone(integerAmount);
        milestones.addMilestone(milestone);
        System.out.println("Your new milestone has been set!");
    }

    // EFFECTS: displays a list of all milestones set by the user
    public void showMilestonesSet() {
        if (milestones.getLength() == 0) {
            System.out.println("You have no milestones set.");
        } else {
            milestones.updateMilestonesStatuses(cookieCount);
            System.out.println(milestones.milestonesSetDisplay());
        }
    }
}
