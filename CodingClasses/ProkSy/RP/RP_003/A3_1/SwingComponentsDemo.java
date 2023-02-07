package CodingClasses.ProkSy.RP.RP_003.A3_1;

import java.awt.*;
import javax.swing.*;

public class SwingComponentsDemo extends JFrame {

    java.awt.Container c;

    public static void main(String[] args) {
        new SwingComponentsDemo();
    }

    public SwingComponentsDemo() {

        c = this.getContentPane();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("JComponents");
        JMenu submenu1 = new JMenu("Abstract Button");
        JMenu submenu2 = new JMenu("JToggleButton");
        JMenuItem submenuItem1 = new JMenuItem("JButton");
        JMenuItem submenuItem2 = new JMenuItem("JCheckbox");
        JMenuItem submenuItem22 = new JMenuItem("JRadioButton");
        JMenuItem Item3 = new JMenuItem("JLabel");
        JMenuItem Item4 = new JMenuItem("JComboBox");

        // menu.addSeparator();
        this.setJMenuBar(menuBar);
        submenu1.add(submenuItem1);
        submenu1.add(submenu2);
        submenu2.add(submenuItem2);
        submenu2.add(submenuItem22);
        menu.add(submenu1);
        menu.add(Item3);
        menu.add(Item4);
        menuBar.add(menu);

        setBasics();

    }

    private void setBasics() {
        this.setTitle("Hallo");
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
