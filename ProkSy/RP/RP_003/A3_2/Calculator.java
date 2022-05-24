package ProkSy.RP.RP_003.A3_2;

import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {

    Container contentPain;

    public static void main(String[] args) {
        new Calculator();
    }

    public Calculator() {

        contentPain = this.getContentPane();

        createMenuBar();

        BorderLayout b = new BorderLayout(1, 1);
        // Textfeld
        JTextField OutputField = new JTextField();
        contentPain.setLayout(b);
        contentPain.add(OutputField, BorderLayout.NORTH);

        // Knöpfe

        // Pannel für SOUTH
        JPanel panel_S = new JPanel();

        BorderLayout S_LMR = new BorderLayout(1, 1);
        panel_S.setLayout(S_LMR);

        // South unterscheiden in East and West

        JPanel panel_SW = new JPanel();
        BorderLayout SW_LMR = new BorderLayout(1, 1);

        JPanel panel_SWN = new JPanel();
        JPanel panel_SWS = new JPanel();

        // panel_SWS.addLayoutComponent();
        // GridLayout SWN_LMR = new GridLayout();

        GridLayout SWS_LMR = new GridLayout(4, 1);
        panel_SWS.setLayout(SWS_LMR);

        JButton[] SWS_Buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            SWS_Buttons[i] = new JButton();
            panel_SWS.add(SWS_Buttons[i], i);
        }

        panel_SW.setLayout(SW_LMR);
        panel_SW.add(panel_SWS, BorderLayout.WEST);

        panel_S.add(panel_SW, BorderLayout.WEST);
        contentPain.add(panel_S, BorderLayout.SOUTH);
        //
        JPanel panel_SE = new JPanel();

        // panel_SE.setLayout(SE_LMR);

        setBasics();

    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuBearbeiten = new JMenu("Bearbeiten");
        JMenu menuAnsicht = new JMenu("Ansicht");
        JMenu menuF = new JMenu("?");

        // menu.addSeparator();

        menuBar.add(menuBearbeiten);
        menuBar.add(menuAnsicht);
        menuBar.add(menuF);
        this.setJMenuBar(menuBar);
    }

    private void setBasics() {
        this.setTitle("Hallo");
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
