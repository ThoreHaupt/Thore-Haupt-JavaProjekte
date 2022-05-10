package ProkSy.Vorlesung;

import java.awt.*;
import javax.swing.*;

public class Fenster extends JFrame {
    java.awt.Container c;

    public Fenster() {
        setBasics();
        c = getContentPane();
        JButton b = new JButton();
        b.setSize(100, 100);
        c.add(b);
        Container c2 = new Container();
        c.add(c2);
        c2.add(b);

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
