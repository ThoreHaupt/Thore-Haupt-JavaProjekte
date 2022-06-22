package ProkSy.RP.RP_005.A4_4;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Umrechner extends JFrame {
    Container contentPain;
    JTextField leftField;
    JTextField rightField;

    UnitSelector selectorLeft;
    UnitSelector selectorRight;
    ConversionLogic CL;

    boolean ignoreWrongInput;
    boolean tausendertrennzeichen;

    public static void main(String[] args) {
        ConversionLogic CL = new ConversionLogic();
        new Umrechner(CL);
    }

    public Umrechner(ConversionLogic CL) {
        this.CL = CL;
        CL.setFrame(this);
        createWindowGUI();
        setBasics("Umrechner");
    }

    public void createWindowGUI() {
        contentPain = this.getContentPane();
        GridLayout cP_Layout = new GridLayout(4, 1);

        contentPain.setLayout(cP_Layout);

        JPanel Panel_00 = new JPanel();
        Panel_00.setLayout(new GridLayout(1, 3));

        Font font1 = new Font("SansSerif", Font.PLAIN, 20);
        leftField = new JTextField("");
        rightField = new JTextField("");
        rightField.setEditable(false);
        leftField.setFont(font1);
        rightField.setFont(font1);

        Panel_00.add(leftField);
        Panel_00.add(rightField);

        JPanel Panel_01 = new JPanel();
        Panel_01.setBorder(new TitledBorder(null, "Convert", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        Panel_01.setLayout(new GridLayout());

        selectorLeft = new UnitSelector(0, CL.getImplementedUnits());
        selectorRight = new UnitSelector(1, CL.getImplementedUnits());

        JButton switchDirection = new JButton(">");
        switchDirection.setFont(font1);
        switchDirection.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchEditable();
                if (switchDirection.getText().equals(">")) {
                    switchDirection.setText("<");
                } else {
                    switchDirection.setText(">");
                }
            }

        });

        Panel_01.add(selectorLeft);
        Panel_01.add(switchDirection);
        Panel_01.add(selectorRight);

        JPanel Panel_02 = new JPanel();
        Panel_02.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        Panel_02.setLayout(new GridLayout(2, 2));

        JCheckBox checkbox1 = new JCheckBox("ignore wrong Input");
        checkbox1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ignoreWrongInput = !ignoreWrongInput;
            }

        });
        Panel_02.add(checkbox1);
        JCheckBox checkbox2 = new JCheckBox("Tausendertrennzeichen");
        checkbox2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tausendertrennzeichen = !tausendertrennzeichen;
            }

        });
        Panel_02.add(checkbox2);

        JPanel Panel_03 = new JPanel();
        Panel_03.setLayout(new GridLayout(1, 1));
        JButton doIT = new JButton("Berechnen");
        doIT.addActionListener(new on_Calculate());

        Panel_03.add(doIT);

        contentPain.add(Panel_00);
        contentPain.add(Panel_01);
        contentPain.add(Panel_02);
        contentPain.add(Panel_03);

    }

    public void setLeftField(double i) {
        leftField.setText("" + i);
    }

    public void setRightField(double i) {
        rightField.setText("" + i);
    }

    private void setBasics(String windowTitle) {
        this.setTitle(windowTitle);
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void switchEditable() {
        if (leftField.isEditable()) {
            leftField.setEditable(false);
            rightField.setEditable(true);
        } else {
            leftField.setEditable(true);
            rightField.setEditable(false);
        }
    }

    public void changeUnit(int num, int newUnitIndex) {

    }

    private class on_Calculate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
            try {

                /*
                 * NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
                 * Number leftValue = format.parse(leftField.getText());
                 * Number rightValue = format.parse(rightField.getText());
                 */

                /*
                 * CL.setValue(leftField.isEditable() ? leftValue.doubleValue()
                 * : rightValue.doubleValue());
                 */
                CL.setValue(leftField.isEditable() ? Double.parseDouble(leftField.getText())
                        : Double.parseDouble(rightField.getText()));

            } catch (NumberFormatException error) {
                System.out.println(error.getLocalizedMessage());
                if (!ignoreWrongInput)
                    JOptionPane.showMessageDialog(topFrame, "Dieser Wert ist ungültig", "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                return;
            }

            CL.setConversionIndeces(selectorLeft.getSelectedIndex(), selectorRight.getSelectedIndex());
            CL.calculate(leftField.isEditable());
        }

    }
}
