package ProkSy.RP.RP_003.A3_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

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

        BorderLayout S_LMR = new BorderLayout();
        panel_S.setLayout(S_LMR);

        // South unterscheiden in East and West

        JPanel panel_SW = new JPanel();
        {
            BorderLayout SW_LMR = new BorderLayout(0, 0);
            panel_SW.setLayout(SW_LMR);

            {
                // SOUTH WEST NORTH ( die kleine graue Box, aber ich mache da einen Knopf rein,
                // weil ich nicht weiß was das für ein Object sein soll)
                JPanel panel_SWN = new JPanel();
                panel_SWN.setMinimumSize(new Dimension(10, 35));
                panel_SWN.setBackground(new ColorUIResource(200, 200, 200));
                GridLayout SWN_LMR = new GridLayout(1, 1);
                panel_SWN.setLayout(SWN_LMR);

                Component component = Box.createRigidArea(new Dimension(30, 30));
                panel_SWN.add(component, 0);

                // JButton button_SWN = new JButton("");
                // panel_SWN.add(button_SWN, 0);

                panel_SW.add(panel_SWN, BorderLayout.NORTH);
            }
            {
                JPanel panel_SWC = new JPanel();

                GridLayout SWC_LMR = new GridLayout(4, 1);
                panel_SWC.setLayout(SWC_LMR);

                JButton[] SWS_Buttons = new JButton[4];
                for (int i = 0; i < 4; i++) {
                    SWS_Buttons[i] = new JButton("" + i);
                    panel_SWC.add(SWS_Buttons[i], i);
                }

                panel_SW.add(panel_SWC, BorderLayout.WEST);
            }
        }
        JPanel panel_SC = new JPanel();
        {
            BorderLayout SC_LMR = new BorderLayout(0, 0);
            panel_SC.setLayout(SC_LMR);
            {
                JPanel panel_SCN = new JPanel();
                GridLayout SCN_LMR = new GridLayout(1, 3);
                panel_SCN.setLayout(SCN_LMR);
                JButton[] SCN_Buttons = new JButton[3];
                for (int i = 0; i < 3; i++) {
                    SCN_Buttons[i] = new JButton("" + i);
                    panel_SCN.add(SCN_Buttons[i], i);
                }
                panel_SC.add(panel_SCN, BorderLayout.NORTH);
            }

            {
                JPanel panel_SCC = new JPanel();
                GridLayout SCN_SCC = new GridLayout(4, 5);
                panel_SCC.setLayout(SCN_SCC);
                JButton[] SCC_Buttons = new JButton[20];
                for (int i = 0; i < 20; i++) {
                    SCC_Buttons[i] = new JButton("" + i);
                    panel_SCC.add(SCC_Buttons[i], i);
                }
                panel_SC.add(panel_SCC, BorderLayout.CENTER);
            }

        }

        panel_S.add(panel_SW, BorderLayout.WEST);
        panel_S.add(panel_SC, BorderLayout.CENTER);
        contentPain.add(panel_S, BorderLayout.CENTER);

        //

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
