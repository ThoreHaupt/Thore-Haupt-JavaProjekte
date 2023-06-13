package CodingClasses.ProkSy.RP.RP_004.A_02.Controller;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import CodingClasses.ProkSy.RP.RP_004.A_02.Model.ColorModel;
import CodingClasses.ProkSy.RP.RP_004.A_02.View.ColorButton;
import CodingClasses.ProkSy.RP.RP_004.A_02.View.ColorWindow;

public class ColorController {
    ColorModel cm;
    ColorWindow cf;
    public ActionListener cbAl = e -> {
        changeColor((ColorButton) e.getSource());
    };

    public ColorController(ColorModel cm, ColorWindow cf) {
        this.cm = cm;
        this.cf = cf;
        cf.setController(this);
        cf.initButton();
    }

    public void changeColor(ColorButton but) {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        cm.setNewColor(r, g, b);
        String text = "(" + cm.getColor().getRed() + ", " + cm.getColor().getGreen() + ", " + cm.getColor().getBlue()
                + ")";
        but.setToolTipText(text);
        but.setBackground(cm.getColor());
    }

}
