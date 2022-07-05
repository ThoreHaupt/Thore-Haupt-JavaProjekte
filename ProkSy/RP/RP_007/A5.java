package ProkSy.RP.RP_007;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class A5 {

    static class ButtonPanel extends JPanel {
        public static void main(String[] args) {
            new ButtonPanel();
        }

        JFrame frame;

        Object monitor = new Object();

        JButton button1;
        JButton button2;
        JButton button3;

        JButton[] buttons = new JButton[3];

        Runnable runner;
        Thread thread;

        boolean running = true;

        public ButtonPanel() {
            setLayout(new GridLayout(1, 3));
            initButtons(this);
            initThread(this);
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("KNOPF");
            frame.add(this);
            frame.setSize(300, 100);
            frame.setVisible(true);
        }

        private void initButtons(ButtonPanel b) {
            button1 = new JButton(0 + "");
            button1.addActionListener(e -> {
                synchronized (thread) {
                    thread.notify();
                }

            });
            add(button1);
            buttons[0] = button1;

            button2 = new JButton(0 + "");
            button2.addActionListener(e -> {
                synchronized (thread) {
                    thread.notify();
                }
            });
            add(button2);
            buttons[1] = button2;

            button3 = new JButton(0 + "");
            button3.addActionListener(e -> {
                synchronized (thread) {
                    thread.notify();
                }
            });
            add(button3);
            buttons[2] = button3;
        }

        private void initThread(ButtonPanel b) {

            runner = () -> {
                System.out.println("Thread startet");
                System.out.println(this.getClass().getSimpleName());

                while (true) {
                    try {
                        synchronized (thread) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("yeah");
                    b.maxRandomButton();
                }
            };

            /* thread = new ThreadThing(b);
            thread.start(); */

            thread = new Thread();
            thread.start();

        }

        private void maxRandomButton() {
            int i = (int) (Math.random() * 3);
            System.out.println(i);
            while (Integer.parseInt(buttons[i].getText()) < 1000) {
                buttons[i].setText(Integer.parseInt(buttons[i].getText()) + 1 + "");

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private static class ThreadThing extends Thread {
            ButtonPanel b = null;

            public ThreadThing(ButtonPanel b) {
                this.b = b;
            }

            public void run() {
                synchronized (this) {
                    while (true) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("yeah");
                        b.maxRandomButton();
                    }
                }
            }

            void notifyWithin() {
                synchronized (this) {
                    notify();
                }

            }
        }

    }

}
