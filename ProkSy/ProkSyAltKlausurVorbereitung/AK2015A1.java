package ProkSy.ProkSyAltKlausurVorbereitung;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class AK2015A1 extends JFrame {

    JLabel statusLabel;
    JComboBox<String> klausur;
    private JTextField punkte;
    private JLabel ergebnisLabel;

    public AK2015A1() {

        setBounds(100, 100, 300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu m1 = new JMenu("Datei");
        JMenu m2 = new JMenu("Berechnung");
        menubar.add(m1);
        menubar.add(m2);

        statusLabel = new JLabel("Bereut zum Rechenen");
        //statusLabel.setHorizontalAlignment(JLabel.CENTER);
        c.add(statusLabel, BorderLayout.SOUTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setBorder(new TitledBorder("Einstellungen"));
        c.add(settingsPanel, BorderLayout.NORTH);

        settingsPanel.setLayout(new GridLayout(2, 0, 5, 5));

        klausur = new JComboBox<String>(new String[] { "ProkSy" });
        String s = (String) klausur.getSelectedItem();
        settingsPanel.add(klausur);

        JPanel hilfspanel = new JPanel(new GridLayout(0, 2, 5, 5));
        punkte = new JTextField("(Punkte)");
        hilfspanel.add(punkte);

        JButton b = new JButton("Berechne Note");
        hilfspanel.add(b);
        settingsPanel.add(hilfspanel);

        JPanel ergebnisPanel = new JPanel(new BorderLayout());
        ergebnisPanel.setBorder(new TitledBorder("Ergebnis"));
        c.add(ergebnisPanel, BorderLayout.CENTER);
        ergebnisLabel = new JLabel("(Ergebnis)");
        ergebnisLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        ergebnisLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ergebnisLabel.setVerticalAlignment(SwingConstants.CENTER);
        ergebnisPanel.add(ergebnisLabel);
    }

    public static void main(String[] args) {
        AK2015A1 frame = new AK2015A1();
        frame.setVisible(true);
    }
}
