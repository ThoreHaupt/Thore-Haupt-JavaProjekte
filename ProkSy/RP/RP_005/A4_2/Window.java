package ProkSy.RP.RP_005.A4_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    Container contentPain;

    public static void main(String[] args) {
        new Window();
    }

    public Window() {
        contentPain = this.getContentPane();
        ColoredButton b = new ColoredButton();
        contentPain.add(b);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

            }
        });
        setBasics("Knopf");
    }

    private void setBasics(String windowTitle) {
        this.setTitle(windowTitle);
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class test extends WindowAdapter {

}