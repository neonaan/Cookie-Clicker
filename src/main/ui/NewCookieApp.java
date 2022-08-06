package ui;

import model.CookieCount;
import model.Milestone;
import model.MilestonesSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class NewCookieApp implements ActionListener {
    private static final String JSON_STORE_MILESTONES = "./data/milestones.json";
    private static final String JSON_STORE_COOKIES = "./data/cookies.json";
    private Scanner input;
    protected CookieCount cookieCount;
    private MilestonesSet milestones;
    private JsonWriter jsonMilestoneWriter;
    private JsonReader jsonMilestoneReader;
    private JsonWriter jsonCookieWriter;
    private JsonReader jsonCookieReader;
    private JLabel counterLabel;
    private JButton setMilestoneButton;
    private JButton viewMilestoneButton;

    public NewCookieApp() {
        cookieCount = new CookieCount();
        milestones = new MilestonesSet();
        jsonMilestoneWriter = new JsonWriter(JSON_STORE_MILESTONES);
        jsonMilestoneReader = new JsonReader(JSON_STORE_MILESTONES);
        jsonCookieWriter = new JsonWriter(JSON_STORE_COOKIES);
        jsonCookieReader = new JsonReader(JSON_STORE_COOKIES);
        counterLabel = new JLabel(cookieCount.getCookieCount() + " cookies");
        runCookie();
    }

    public void runCookie() {
        JFrame application = makeWindow();
        makeCookie(application);
        makeCookieCounter(application);
        makeMilestoneButtons(application);
        application.setVisible(true);

    }

    private JFrame makeWindow() {
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.getContentPane().setBackground(Color.black);
        application.setLayout(null);
        application.setSize(800,700);
        return application;
    }

    public void makeCookie(JFrame application) {
        JButton cookieButton = new JButton();
        Color brown = new Color(150, 75, 0);
        cookieButton.setBackground(brown);
        cookieButton.setBounds(100, 200, 200, 200);
        cookieButton.setActionCommand("click cookie");
        cookieButton.addActionListener(this);
        application.add(cookieButton);

    }

    public void makeCookieCounter(JFrame application) {
        JPanel counter = new JPanel();
        counter.setBounds(120, 100, 200, 100);
        counter.setBackground(Color.black);
        counter.setLayout(new GridLayout(1,1));
        application.add(counter);

        Font font = new Font("Verdana", Font.PLAIN, 30);
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font);
        counter.add(counterLabel);

    }

    public void makeMilestoneButtons(JFrame application) {
        JPanel milestoneButtons = new JPanel();
        milestoneButtons.setBounds(450, 250, 200,100);
        milestoneButtons.setBackground(Color.blue);
        milestoneButtons.setLayout(new GridLayout(2, 1));
        application.add(milestoneButtons);

        Font font = new Font("Verdana", Font.PLAIN, 16);

        setMilestoneButton = new JButton("Set new milestone");
        setMilestoneButton.setFont(font);
        setMilestoneButton.setActionCommand("set milestone");
        setMilestoneButton.addActionListener(this);
        milestoneButtons.add(setMilestoneButton);

        viewMilestoneButton = new JButton("View milestones");
        viewMilestoneButton.setFont(font);
        viewMilestoneButton.setActionCommand("view milestones");
        viewMilestoneButton.addActionListener(this);
        milestoneButtons.add(viewMilestoneButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        switch (action) {
            case "click cookie":
                cookieCount.incrementCookies(1);
                counterLabel.setText(cookieCount.getCookieCount() + " cookies");
                break;
            case "set milestone":
                setupMilestone();

            case "view milestones":

        }
    }

    // REQUIRES: input must be an integer >=0
    // MODIFIES: this, MilestonesSet
    // EFFECTS: Prompts user to create a milestone and adds it to the MilestoneSet
    public void setupMilestone() {

        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        application.getContentPane().setBackground(Color.black);
        application.setLayout(null);
        application.setSize(500,500);
        application.setLayout(new FlowLayout());

        JTextField field = new JTextField("Set the amount of cookies you wish to receive: ");
        application.add(field);


        String amount = null;
        System.out.println("Set the amount of cookies you wish to receive: ");
        amount = input.next();
        int integerAmount = Integer.valueOf(amount);
        Milestone milestone = new Milestone(integerAmount);
        milestones.addMilestone(milestone);
        System.out.println("Your new milestone has been set!");
    }
}
