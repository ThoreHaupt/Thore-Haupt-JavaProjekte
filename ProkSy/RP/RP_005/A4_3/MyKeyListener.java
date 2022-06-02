package ProkSy.RP.RP_005.A4_3;

import java.awt.event.*;

class MyKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent evt) {

        System.out.println("Check for key characters: " + evt.getKeyChar());

        if (evt.getKeyCode() == KeyEvent.VK_HOME) {
            System.out.println("Check for key codes: " + evt.getKeyCode());
        }
    }
}