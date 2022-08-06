package ui;

import javax.swing.*;
import java.awt.*;

//class that allows us to create a circular JButton
public class RoundButton extends JButton {

    public RoundButton() {
        Color brown = new Color(150, 75, 0);
        setBackground(brown);
        setFocusable(false);

        //makes circular frame
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {

    }



}
