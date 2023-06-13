package CodingClasses.ProkSy.RP.RP_004.A_02.View;

import javax.swing.JFrame;
import java.awt.*;

import CodingClasses.ProkSy.RP.RP_004.A_02.Controller.ColorController;
import CodingClasses.ProkSy.RP.RP_004.A_02.Model.ColorModel;

public class ColorWindow extends JFrame {
    ColorController cc = null;
    ColorModel cm = null;
    public Container c;
    ColorButton cb;

    public ColorWindow() {
        c = getContentPane();
        setBasics();
    }

    private void setBasics() {
        this.setTitle("Color");
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setController(ColorController cc) {
        this.cc = cc;
    }

    public void setColorModel(ColorModel cm) {
        this.cm = cm;
    }

    public void initButton() {
        ColorButton cb = new ColorButton(cc);
        cb.addActionListener(cc.cbAl);
        c.add(cb);

    }
}
