package CodingClasses.ProkSy.RP.RP_005.A4_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class On_Refresh extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
        if (topFrame instanceof Uhr) {
            ((Uhr) topFrame).refreshDate();
        } else {
            System.out.println("Internal Error");
        }

    }
}
