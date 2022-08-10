package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// A new window that appears when the main application is closed
public class CloseWindowPopup extends WindowAdapter implements ActionListener {
    private JButton noButton;
    private JButton yesButton;
    private CookieApp application;

    // EFFECTS: runs the popup application
    public CloseWindowPopup(CookieApp application) {
        this.application = application;
    }

    // EFFECTS: Creates and displays a new popup window
    public void windowClosing(WindowEvent e) {
        JFrame popUp = new JFrame();
        popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUp.getContentPane().setBackground(Color.white);
        popUp.setSize(400, 200);
        popUp.setLayout(null);
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(85, 0, 300, 50);
        textPanel.setBackground(Color.white);
        textPanel.setLayout(new GridLayout(1,1));
        popUp.add(textPanel);

        Font font = new Font("Verdana", Font.PLAIN, 14);
        JLabel text = new JLabel("Do you want to save the game?");
        text.setForeground(Color.black);
        text.setFont(font);
        textPanel.add(text);

        makeButtons(popUp);

    }

    // MODIFIES: this
    // EFFECTS: Makes yes/no buttons for the new window
    private void makeButtons(JFrame popUp) {
        JPanel yesNoButtons = new JPanel();
        yesNoButtons.setBounds(140, 50, 100,50);
        yesNoButtons.setBackground(Color.blue);
        yesNoButtons.setLayout(new GridLayout(2, 1));
        popUp.add(yesNoButtons);

        Font font = new Font("Verdana", Font.PLAIN, 16);

        yesButton = new JButton("Yes");
        yesButton.setFont(font);
        yesButton.addActionListener(this);
        yesButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        yesNoButtons.add(yesButton);

        noButton = new JButton("No");
        noButton.setFont(font);
        noButton.addActionListener(this);
        yesNoButtons.add(noButton);
    }

    // EFFECTS: selects a new action based on which button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();

        if (action == yesButton) {
            application.saveGame();
            printLog(EventLog.getInstance());
            System.exit(0);
        } else if (action == noButton) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    private void printLog(EventLog el) {
        for (Event event : el) {
            System.out.println(event.getDescription());
        }
    }

}
