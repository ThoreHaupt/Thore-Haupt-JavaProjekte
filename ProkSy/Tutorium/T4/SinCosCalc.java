package ProkSy.Tutorium.T4;

import java.awt.*;
import java.util.jar.JarEntry;

import javax.swing.*;
import javax.swing.text.DocumentFilter;

public class SinCosCalc extends JFrame {

    Container c = getContentPane();

    public static void main(String[] args) {
        new SinCosCalc();
    }

    public SinCosCalc() {
        buildUI();

        setSize(200, 400);
        setTitle("sinCosCalc");
        setVisible(true);
    }

    void buildUI() {
        c.setLayout(new GridLayout(2, 3));

        JLabel label = new JLabel("Argument");

        JTextField input = new JTextField();
        input.setEditable(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        JCheckBox sinBox = new JCheckBox("Sinus");
        buttonGroup.add(sinBox);

        JCheckBox cosBox = new JCheckBox("Sosinus");
        buttonGroup.add(cosBox);

        JButton calc = new JButton("Calculate");

        JLabel outputLabel = new JLabel();

        calc.addActionListener(e -> {
            double d = Double.parseDouble(input.getText());
            outputLabel.setText(sinBox.isSelected() ? Math.sin(d) + "" : Math.cos(d) + "");
        });

        c.add(label, 0);
        c.add(sinBox, 1);
        c.add(calc);
        c.add(input, 3);
        c.add(cosBox, 4);
        c.add(outputLabel);

    }

}
