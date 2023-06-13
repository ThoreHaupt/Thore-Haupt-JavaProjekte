package CodingClasses.ProkSy.RP.RP_004.A_02.Model;

import java.awt.Color;

import CodingClasses.ProkSy.RP.RP_004.A_02.Controller.ColorController;

public class ColorModel {
    int r;
    int g;
    int b;
    ColorController cc;

    public void setController(ColorController c) {
        this.cc = c;
    }

    public void setNewColor(int r2, int g2, int b2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
    }

    public Color getColor() {
        return new Color(r, g, b);
    }

}
