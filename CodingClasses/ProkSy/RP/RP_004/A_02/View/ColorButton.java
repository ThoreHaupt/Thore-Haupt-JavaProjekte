package CodingClasses.ProkSy.RP.RP_004.A_02.View;

import javax.swing.*;

import CodingClasses.ProkSy.RP.RP_004.A_02.Controller.ColorController;

public class ColorButton extends JButton {
    ColorController cc;

    public ColorButton(ColorController cc) {
        this.cc = cc;
        setOpaque(true);

        this.cc.changeColor(this);
    }
}
