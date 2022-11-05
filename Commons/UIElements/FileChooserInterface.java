package Commons.UIElements;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import PracticeProjects.test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileChooserInterface extends JPanel {
    String defualtPath = "";
    String currentPath = "";
    String tag = "";

    JLabel tagLabel;
    JTextField pathTextField;
    JButton openFile;

    JFileChooser fileChooser;
    int selectionMode;

    Color illegalColor = new Color(255, 51, 0);

    /**
     * @param defaultPath
     * @param tag
     */
    public FileChooserInterface(int selectionMode, String defaultPath, String tag) {
        this.selectionMode = selectionMode;
        this.defualtPath = defaultPath;
        this.currentPath = defaultPath;
        this.tag = tag;

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(defaultPath == "" ? null : new File(defaultPath));

        buildInterface();
    }

    /**
     * @param tag
     */
    public FileChooserInterface(int selectionMode, String tag) {
        this(selectionMode, "", tag);
    }

    private void buildInterface() {

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        this.setLayout(gbl);

        tagLabel = new JLabel(tag + " ");
        c.weightx = 0.3;
        add(tagLabel);

        pathTextField = new JTextField(defualtPath);
        pathTextField.setEditable(true);
        pathTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                currentPath = pathTextField.getText();
                adjustTextfieldColor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                currentPath = pathTextField.getText();
                adjustTextfieldColor();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currentPath = pathTextField.getText();
                adjustTextfieldColor();
            }

        });

        c.weightx = 0.6;
        c.gridx = 1;
        add(pathTextField);

        openFile = new JButton(" browse");
        openFile.addActionListener(e -> {
            if (!currentPath.equals(defualtPath)) {
                fileChooser.setCurrentDirectory(new File(currentPath));
            }
            fileChooser.setFileSelectionMode(selectionMode);
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                currentPath = file.getAbsolutePath();
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                pathTextField.setText(currentPath);
            }
        });

        c.weightx = 0.1;
        c.gridx = 2;
        add(openFile);
    }

    private void adjustTextfieldColor() {
        if (testCurrentPathExistane()) {
            pathTextField.setForeground(new Color(0, 0, 0));
        } else {
            pathTextField.setForeground(illegalColor);
        }
    }

    /**
     * @return the currentPath
     */
    public String getCurrentPath() {
        return currentPath;
    }

    public boolean testCurrentPathExistane() {
        File f = new File(currentPath);
        return f.exists();
    }
}
