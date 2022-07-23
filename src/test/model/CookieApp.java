package model;

import java.util.Scanner;

// Cookie Clicker game application
public class CookieApp {
    private Scanner input;
    private int cookieCount;

    // EFFECTS: runs the Cookie Clicker game application
    public CookieApp() {
        runCookie();
    }

    // MODIFIES: this
    // EFFECTS: Processes user input
    private void runCookie() {
        boolean endApp = false;
        String command = null;
        cookieCount = 0;

        startGame();

    }

    private void startGame() {
    }
}
