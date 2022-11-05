package Commons.UIElements;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import java.awt.*;

public class InputTextfield extends JPanel {

    String text;
    String inputText = "";
    DocumentFilter df;

    JTextField textField = new JTextField();
    JLabel describtor = new JLabel();

    public InputTextfield(String text, DocumentFilter df, Dimension size, Dimension textFieldSize) {
        describtor.setText(text);
        if (df != null)
            ((PlainDocument) textField.getDocument()).setDocumentFilter(df);

        setLayout(new BorderLayout());

        setPreferredSize(size);
        textField.setPreferredSize(textFieldSize);

        add(describtor, BorderLayout.WEST);
        add(textField, BorderLayout.EAST);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String string) {
        textField.setText(string);
    }

}
