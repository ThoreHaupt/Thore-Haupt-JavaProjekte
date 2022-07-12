package ProkSy.RP.RP_005.A4_2;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.ChangedCharSetException;

import java.awt.*;
import java.awt.event.*;

public class ColoredButton extends JButton {

    public ColoredButton() {
        changeColor();
        this.addActionListener(new on_Press());
    }

    private Color generateRandomColor() {
        int r = (int) (Math.random() * 250);
        int g = (int) (Math.random() * 250);
        int b = (int) (Math.random() * 250);
        return new Color(r, g, b);
    }

    private void changeColor() {
        Color c = generateRandomColor();
        this.setBackground(c);
        setToolTipText("(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ")");
        this.repaint();
    }

    private class on_Press extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeColor();
        }

    }

    private class onPress2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }
}
