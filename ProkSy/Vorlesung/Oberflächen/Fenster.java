package ProkSy.Vorlesung.Oberflächen;

import java.awt.*;
import javax.swing.*;

public class Fenster extends JFrame {
    java.awt.Container c;

    public Fenster() {
        setBasics();
        c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel c2 = new JPanel(new GridLayout());
        c2.setMinimumSize(new Dimension(200, 300));

        JButton b = new JButton();
        c2.add(b, BorderLayout.CENTER);
        b.setMinimumSize(new Dimension(200, 300));
        b.setAction(new MyAction());
        b.setVisible(true);
        b.setOpaque(true);

        c.add(c2, BorderLayout.NORTH);

        /*
         * 
         * c2.setVisible(true);
         * c2.add(b);
         * c.add(c2);
         */

    }

    private void setBasics() {
        this.setTitle("Hallo");
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void doSomething() {
        System.out.println("hallo");
    }

    public static void main(String[] args) {
        Fenster f = new Fenster();
    }
}
