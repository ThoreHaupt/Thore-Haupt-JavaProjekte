package ProkSy.RP.RP_007;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class A5 {

    static class buttonPanel extends JPanel {
        public static void main(String[] args) {
            new buttonPanel();
        }

        JFrame frame;

        Object monitor = new Object();

        JButton button1;
        JButton button2;
        JButton button3;

        JButton[] buttons = { button1, button2, button3 };

        Runnable runner;
        Thread thread;

        boolean running = true;

        public buttonPanel() {
            setLayout(new GridLayout(1, 3));
            initButtons();
            intiThread();
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("KNOPF");
            frame.add(this);
            frame.setSize(300, 100);
            frame.setVisible(true);
        }

        private void initButtons() {
            button1 = new JButton(0 + "");
            button1.addActionListener(e -> {
                synchronized (monitor) {
                    runner.notify();
                }

            });
            add(button1);

            button2 = new JButton(0 + "");
            button2.addActionListener(e -> {

                thread.notify();

            });
            add(button2);

            button3 = new JButton(0 + "");
            button3.addActionListener(e -> {
                thread.notify();
            });
            add(button3);
        }

        private void intiThread() {
            runner = () -> {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("yeah");
                maxRandomButton();

            };
            thread = new Thread(runner);
            thread.start();
        }

        private void maxRandomButton() {
            int i = (int) (Math.random() * 3);
            while (Integer.parseInt(buttons[i].getText()) < 1000) {
                buttons[i].setText(Integer.parseInt(buttons[i].getText()) + 1 + "");
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
