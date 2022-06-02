package ProkSy.RP.RP_005.A4_4;

import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class UnitSelector extends JComboBox<String> {
    int index;

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     */
    public UnitSelector(int index, String[] options) {
        super(options);
        this.addActionListener(new comboBoxListener());
        this.index = index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    private class comboBoxListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(e.getSource() instanceof UnitSelector))
                return;
            UnitSelector combobox = (UnitSelector) e.getSource();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((UnitSelector) e.getSource());
            if (topFrame instanceof Umrechner) {
                ((Umrechner) topFrame).changeUnit(combobox.getIndex(), combobox.getSelectedIndex());
            }

        }

    }
}
