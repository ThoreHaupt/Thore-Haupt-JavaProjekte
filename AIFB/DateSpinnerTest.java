package AIFB;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSpinnerTest extends JFrame {
    public static void main(String[] args) {
        new DateSpinnerTest();
    }

    public DateSpinnerTest() {

        Container c = getContentPane();
        JPanel panel = new JPanel(new BorderLayout());

        JSpinner cal = new JSpinner(new SpinnerDateModel());
        JButton selectButton = new JButton("select");
        DateEditor editor = new DateEditor(cal, "dd.MM.yyyy");
        cal.setEditor(editor);
        cal.setValue(new Date());
        SimpleDateFormat d = new SimpleDateFormat("dd.MM.yyyy");
        selectButton.addActionListener(e -> {
            String spinnerInp = editor.getFormat().format(cal.getValue());
            System.out.println(spinnerInp);
            try {
                Date date = d.parse(spinnerInp);
                System.out.println(date);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        panel.add(selectButton, BorderLayout.EAST);
        panel.add(cal, BorderLayout.CENTER);
        c.add(panel);
        setBasics();
    }

    private void setBasics() {
        this.setTitle("Hallo");
        this.setSize(400, 280);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
