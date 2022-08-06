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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NewCookieApp implements ActionListener {
    private static final String JSON_STORE_MILESTONES = "./data/milestones.json";
    private static final String JSON_STORE_COOKIES = "./data/cookies.json";
    protected CookieCount cookieCount;
    private MilestonesSet milestones;
    private JsonWriter jsonMilestoneWriter;
    private JsonReader jsonMilestoneReader;
    private JsonWriter jsonCookieWriter;
    private JsonReader jsonCookieReader;
    private JLabel counterLabel;
    private JButton setMilestoneButton;
    private JButton viewMilestoneButton;
    private JButton cookieButton;
    private JTextField setMilestoneTextField;
    private JFrame setMilestoneFrame;
    private JFrame application;

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
        application = new JFrame();
        application.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        application.getContentPane().setBackground(Color.pink);
        application.setLayout(null);
        application.setSize(800,700);
        application.addWindowListener(new CloseWindowPopup(this));
        new StartGamePopup(this);
        return application;
    }

    public void makeCookie(JFrame application) {
        cookieButton = new JButton();
        Color brown = new Color(150, 75, 0);
        cookieButton.setBackground(brown);
        cookieButton.setBounds(100, 200, 200, 200);
        cookieButton.addActionListener(this);
        application.add(cookieButton);

    }

    public void makeCookieCounter(JFrame application) {
        JPanel counter = new JPanel();
        counter.setBounds(120, 100, 200, 100);
        counter.setBackground(Color.pink);
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
        setMilestoneButton.addActionListener(this);
        milestoneButtons.add(setMilestoneButton);

        viewMilestoneButton = new JButton("View milestones");
        viewMilestoneButton.setFont(font);
        viewMilestoneButton.addActionListener(this);
        milestoneButtons.add(viewMilestoneButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object action = e.getSource();

        if (action == cookieButton) {
            cookieCount.incrementCookies(1);
            counterLabel.setText(cookieCount.getCookieCount() + " cookies");
        } else if (action == setMilestoneButton) {
            setupMilestone();
        } else if (action == viewMilestoneButton) {
            showMilestones();
        } else if (action == setMilestoneTextField) {
            String amount = setMilestoneTextField.getText();
            addNewMilestone(amount);
        }
    }


    // REQUIRES: input must be an integer >=0
    // MODIFIES: this, MilestonesSet
    // EFFECTS: Prompts user to create a milestone and adds it to the MilestoneSet
    public void setupMilestone() {

        setMilestoneFrame = new JFrame();
        setMilestoneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMilestoneFrame.getContentPane().setBackground(Color.black);
        setMilestoneFrame.setLayout(null);
        setMilestoneFrame.setSize(400,200);
        setMilestoneFrame.setLayout(new FlowLayout());
        setMilestoneFrame.setLocationRelativeTo(null);
        setMilestoneFrame.setVisible(true);

        JLabel entryText = new JLabel("Set the amount of cookies you wish to receive:");
        entryText.setBounds(250, 10, 100, 20);
        entryText.setBackground(Color.black);
        entryText.setForeground(Color.white);
        setMilestoneFrame.add(entryText);

        setMilestoneTextField = new JTextField("", 5);
        setMilestoneFrame.add(setMilestoneTextField);
        setMilestoneTextField.addActionListener(this);

    }

    public void addNewMilestone(String amount) {
        try {
            int integerAmount = Integer.valueOf(amount);
            Milestone milestone = new Milestone(integerAmount);
            milestones.addMilestone(milestone);
            setMilestoneFrame.dispose();

        } catch (NumberFormatException e) {
            JFrame errorFrame = new JFrame();
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            errorFrame.getContentPane().setBackground(Color.yellow);
            errorFrame.setSize(400, 200);
            errorFrame.setLocationRelativeTo(null);
            errorFrame.setVisible(true);

            JLabel entryText = new JLabel("oh no! Your milestone needs to be an integer.");
            entryText.setHorizontalAlignment(JLabel.CENTER);
            entryText.setBackground(Color.yellow);
            entryText.setForeground(Color.black);
            errorFrame.add(entryText);
        }
    }

    // EFFECTS: displays a list of all milestones set by the user
    public void showMilestones() {
        JFrame milestonesDisplay = new JFrame("Milestones:");
        milestonesDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        milestonesDisplay.getContentPane().setBackground(Color.black);
        milestonesDisplay.setLayout(null);
        milestonesDisplay.setSize(400,300);
        milestonesDisplay.setLocationRelativeTo(null);
        milestonesDisplay.setVisible(true);

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        area.setEditable(false);
        scroll.setBounds(0, 0, 400, 300);
        milestonesDisplay.add(scroll);

        if (milestones.getLength() == 0) {
            area.setText("You have no milestones set.");
        } else {
            milestones.updateMilestonesStatuses(cookieCount.getCookieCount());
            for (int i = 0; i < milestones.getLength(); i++) {
                area.append(milestones.milestonesSetDisplay().get(i) + "\n");
            }
        }
    }

    public void saveGame() {
        try {
            jsonMilestoneWriter.open();
            jsonMilestoneWriter.writeMilestonesSet(milestones);
            jsonMilestoneWriter.close();
            jsonCookieWriter.open();
            jsonCookieWriter.writeCookieCount(cookieCount);
            jsonCookieWriter.close();
            System.out.println("Saved milestones and total cookies");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_MILESTONES);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads MilestonesSet and cookieCount from file
    public void loadGame() {
        try {
            milestones = jsonMilestoneReader.readMilestonesSet();
            cookieCount = jsonCookieReader.readCookies();
            counterLabel.setText(cookieCount.getCookieCount() + " cookies");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_MILESTONES);
        }
    }
}
