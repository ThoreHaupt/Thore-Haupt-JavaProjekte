package CodingClasses.ProkSy.RP.RP_004.A_02;

import CodingClasses.ProkSy.RP.RP_004.A_02.Controller.ColorController;
import CodingClasses.ProkSy.RP.RP_004.A_02.Model.ColorModel;
import CodingClasses.ProkSy.RP.RP_004.A_02.View.ColorWindow;

public class ColorProgramm {
    public static void main(String[] args) {
        ColorModel cm = new ColorModel();
        ColorWindow cf = new ColorWindow();
        ColorController cc = new ColorController(cm, cf);
        cm.setController(cc);
        cf.setController(cc);
    }
}
