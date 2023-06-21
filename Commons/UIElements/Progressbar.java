package Commons.UIElements;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.*;

public class Progressbar extends JPanel {
    int progress = 0;
    int max = 0;
    Container ContentPane;

    JProgressBar bar;

    JLabel label1;
    JLabel label2;
    JLabel infoLabel;

    public Progressbar(int max) {
        this.max = max;
        buildProgressbar();
    }

    public void buildProgressbar() {

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = .2;

        label1 = new JLabel("progress:");
        c.fill = GridBagConstraints.HORIZONTAL;
        add(label1, c);

        c.gridy++;
        c.weighty = .1;
        c.weightx = 0.8;
        bar = new JProgressBar();
        bar.setMaximum(max);
        add(bar, c);

        c.gridx++;
        c.weightx = 0.2;
        label2 = new JLabel("0/" + max);
        add(label2, c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 1;
        infoLabel = new JLabel("");
        add(infoLabel);
    }

    public void advance(int i) {
        progress += i;
        bar.setValue(progress);
        label2.setText(" " + progress + "/" + max);

    }

    public JProgressBar getBar() {
        return bar;
    }

    public void setInfoLabel(String text) {
        infoLabel.setText(text);
    }
}
