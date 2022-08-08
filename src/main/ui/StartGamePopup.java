package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Startup window that appear when the main application is first run
public class StartGamePopup implements ActionListener {
    private JButton noButton;
    private JButton yesButton;
    private CookieApp application;
    private JFrame popup;

    // EFFECTS: runs the popup application
    public StartGamePopup(CookieApp application) {
        this.application = application;
        openPopup();
    }

    // EFFECTS: Creates and displays a new popup window
    private void openPopup() {
        popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.getContentPane().setBackground(Color.white);
        popup.setSize(400, 200);
        popup.setLayout(null);
        popup.setLocationRelativeTo(null);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(70, 0, 300, 50);
        textPanel.setBackground(Color.white);
        textPanel.setLayout(new GridLayout(1,1));
        popup.add(textPanel);

        Font font = new Font("Verdana", Font.PLAIN, 14);
        JLabel text = new JLabel("Do you want to load the saved file?");
        text.setForeground(Color.black);
        text.setFont(font);
        textPanel.add(text);

        makeButtons(popup);
        popup.setVisible(true);

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
            application.loadGame();
            popup.dispose();
        } else if (action == noButton) {
            popup.dispose();
        }
    }

}
