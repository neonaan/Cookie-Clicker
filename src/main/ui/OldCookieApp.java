//package ui;
//
//import model.CookieCount;
//import model.Milestone;
//import model.MilestonesSet;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
//// Cookie Clicker game application
//public class CookieApp {
//    private static final String JSON_STORE_MILESTONES = "./data/milestones.json";
//    private static final String JSON_STORE_COOKIES = "./data/cookies.json";
//    private Scanner input;
//    private CookieCount cookieCount;
//    private MilestonesSet milestones;
//    private JsonWriter jsonMilestoneWriter;
//    private JsonReader jsonMilestoneReader;
//    private JsonWriter jsonCookieWriter;
//    private JsonReader jsonCookieReader;
//
//    // EFFECTS: runs the Cookie Clicker game application. User starts with no cookies and no milestones.
//    public CookieApp() {
//        cookieCount = new CookieCount();
//        milestones = new MilestonesSet();
//        jsonMilestoneWriter = new JsonWriter(JSON_STORE_MILESTONES);
//        jsonMilestoneReader = new JsonReader(JSON_STORE_MILESTONES);
//        jsonCookieWriter = new JsonWriter(JSON_STORE_COOKIES);
//        jsonCookieReader = new JsonReader(JSON_STORE_COOKIES);
//        runCookie();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Processes user input
//    public void runCookie() {
//        boolean endApp = false;
//        String command = null;
//
//        startGame();
//        while (!endApp) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("e")) {
//                endApp = true;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nThanks for playing! \nYou ended the game with "
//                + Integer.toString(cookieCount.getCookieCount()) + " cookies.");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    public void processCommand(String command) {
//        if (command.equals("c")) {
//            increaseCookieCount();
//        } else if (command.equals("m")) {
//            setupMilestone();
//        } else if (command.equals("v")) {
//            showMilestonesSet();
//        } else if (command.equals("s")) {
//            saveGameState();
//        } else if (command.equals("l")) {
//            loadGameState();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//
//    // EFFECTS: displays menu of options to user
//    public void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tc -> acquire cookie");
//        System.out.println("\tm -> set milestone");
//        System.out.println("\tv -> view milestones");
//        System.out.println("\ts -> save game");
//        System.out.println("\tl -> load game");
//        System.out.println("\te -> end game");
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: initializes accounts
//    public void startGame() {
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: increases cookie count by 1
//    public void increaseCookieCount() {
//        cookieCount.incrementCookies(1);
//        System.out.println("You now have " + Integer.toString(cookieCount.getCookieCount()) + " cookies!");
//    }
//
//    // REQUIRES: input must be an integer >=0
//    // MODIFIES: this, MilestonesSet
//    // EFFECTS: Prompts user to create a milestone and adds it to the MilestoneSet
//    public void setupMilestone() {
//        String amount = null;
//        System.out.println("Set the amount of cookies you wish to receive: ");
//        amount = input.next();
//        int integerAmount = Integer.valueOf(amount);
//        Milestone milestone = new Milestone(integerAmount);
//        milestones.addMilestone(milestone);
//        System.out.println("Your new milestone has been set!");
//    }
//
//    // EFFECTS: displays a list of all milestones set by the user
//    public void showMilestonesSet() {
//        if (milestones.getLength() == 0) {
//            System.out.println("You have no milestones set.");
//        } else {
//            milestones.updateMilestonesStatuses(cookieCount.getCookieCount());
//            for (int i = 0; i < milestones.getLength(); i++) {
//                System.out.println(milestones.milestonesSetDisplay().get(i));
//            }
//        }
//    }
//
//    // EFFECTS: saves MilestonesSet and cookieCount to file
//    private void saveGameState() {
//        try {
//            jsonMilestoneWriter.open();
//            jsonMilestoneWriter.writeMilestonesSet(milestones);
//            jsonMilestoneWriter.close();
//            jsonCookieWriter.open();
//            jsonCookieWriter.writeCookieCount(cookieCount);
//            jsonCookieWriter.close();
//            System.out.println("Saved milestones and total cookies");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE_MILESTONES);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads MilestonesSet and cookieCount from file
//    private void loadGameState() {
//        try {
//            milestones = jsonMilestoneReader.readMilestonesSet();
//            cookieCount = jsonCookieReader.readCookies();
//            System.out.println("Loaded previous game state");
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE_MILESTONES);
//        }
//    }
//}
