package ProkSy.Vorlesung;

import javax.swing.*;

public class GraphischeOberflächen {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Hallo");
        window.setSize(400, 280);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button1 = new JButton(new MyAction());
        button1.setOpaque(true);
        button1.setVisible(true);
        button1.setSize(20, 10);
        window.add(button1);

    }
}
