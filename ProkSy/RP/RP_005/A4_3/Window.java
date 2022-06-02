package ProkSy.RP.RP_005.A4_3;

import javax.swing.*;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Window extends JFrame {
    Container contentPain;
    JComboBox<String> cBox;
    JLabel mainLabel;
    HashMap<Integer, String> lookupMap = new HashMap<>();

    public static void main(String[] args) {
        new Window();
    }

    public Window() {
        contentPain = this.getContentPane();
        contentPain.setLayout(new BorderLayout());

        JLabel topLabel = new JLabel("heutigesDatum");
        contentPain.add(topLabel, BorderLayout.NORTH);

        String[] comboboxLabels = { "Tag, Monat, Jahr", "Stunde, Minute", "Jahr, Monat, Wochentag, Monatstag" };
        lookupMap.put(0, "dd:MM:YYYY");
        lookupMap.put(1, "hh:mm");
        lookupMap.put(2, "YYYY:MM:EEEE:dd");

        cBox = new JComboBox<>(comboboxLabels);
        cBox.addActionListener(new comboBoxListender());
        cBox.addKeyListener(new MyKeyListener());
        contentPain.add(cBox, BorderLayout.SOUTH);

        mainLabel = new JLabel("", JLabel.CENTER);
        setLable(0);
        contentPain.add(mainLabel, BorderLayout.CENTER);

        setBasics("DatumDislay");
    }

    private void setBasics(String windowTitle) {
        this.setTitle(windowTitle);
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setLable(int selectedOption) {
        SimpleDateFormat df = new SimpleDateFormat(lookupMap.get(selectedOption));
        mainLabel.setText(df.format(new Date()));
    }

    private class comboBoxListender extends AbstractAction {

        @Override
        @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent e) {
            setLable(((JComboBox<String>) e.getSource()).getSelectedIndex());
        }

    }

}
