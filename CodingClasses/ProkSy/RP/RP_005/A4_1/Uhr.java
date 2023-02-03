package CodingClasses.ProkSy.RP.RP_005.A4_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Uhr extends JFrame {
    Container contentPain;
    SimpleDateFormat df = new SimpleDateFormat("YYYY:MM:dd:HH:mm:ss");
    JLabel timeLabel;

    public static void main(String[] args) {
        new Uhr();
    }

    public Uhr() {
        createWindowGUI();
        setBasics("Uhr");
    }

    public void createWindowGUI() {
        contentPain = this.getContentPane();
        GridLayout cP_Layout = new GridLayout(2, 1);

        contentPain.setLayout(cP_Layout);

        JPanel Panel_N = new JPanel();

        Date time = new Date();

        timeLabel = new JLabel(df.format(time), JLabel.CENTER);
        Panel_N.setLayout(new BorderLayout());
        Panel_N.add(timeLabel, BorderLayout.CENTER);
        contentPain.add(Panel_N);

        JButton refresh = new JButton(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
                if (topFrame instanceof Uhr) {
                    ((Uhr) topFrame).refreshDate();
                } else {
                    System.out.println("Internal Error");
                }

            }

        });
        // JButton refresh = new JButton(new on_Refresh());
        refresh.setText("refresh");

        contentPain.add(refresh);
    }

    private void setBasics(String windowTitle) {
        this.setTitle(windowTitle);
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean refreshDate() {
        timeLabel.setText(df.format(new Date()));
        return true;
    }

    private class on_Refresh extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
            if (topFrame instanceof Uhr) {
                ((Uhr) topFrame).refreshDate();
            } else {
                System.out.println("Internal Error");
            }

        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */

        @Override
        public String toString() {
            return "on_Refresh";
        }

    }
}
