package neuralNetTestsIG.NeuralInterfaceProgramm;

import Commons.CalulationTools.SupportingCalculations;
import Commons.FileHandiling.Pair;
import neuralNetTestsIG.TestBasicNeuralNet.ImageApproximation;
import neuralNetTestsIG.TestBasicNeuralNet.NeuralNet;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class HandwritingWindow extends JFrame {
    Container contentPain;
    NeuralNet NN;
    JPanel[][] pixel;
    ImageApproximation currentImage;
    JPanel imagePanel;
    JTextArea scoreTextArea;

    public HandwritingWindow(NeuralNet NN) {

        this.NN = NN;

        int pixelNumX = 28;
        int pixelNumY = 28;
        pixel = new JPanel[pixelNumY][pixelNumX];

        contentPain = this.getContentPane();
        JPanel panel = new JPanel(new BorderLayout(1, 1));

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(560, 560));
        GridLayout g = new GridLayout(28, 28, 2, 2);
        imagePanel.setLayout(g);
        for (int i = 0; i < pixelNumY; i++) {
            for (int j = 0; j < pixelNumX; j++) {
                JPanel p = new JPanel();
                p.setOpaque(true);
                p.setPreferredSize(new Dimension(20, 20));
                pixel[i][j] = p;
                p.add(new JLabel(""));
                imagePanel.add(p);
            }
        }

        JPanel InfoActionPanel = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 4;
        c.gridx = 3;
        c.fill = 2;
        c.weightx = 0.25;
        InfoActionPanel.setLayout(gb);

        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(" Information (Confidence)");
        infoPanel.add(title, BorderLayout.NORTH);
        scoreTextArea = new JTextArea();
        scoreTextArea.setEditable(false);
        infoPanel.add(scoreTextArea, BorderLayout.CENTER);

        InfoActionPanel.add(infoPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton newImageButton = new JButton("test new Random Image from test-Set");
        newImageButton.addActionListener(e -> {
            int n = (int) (Math.random() * NN.getTestDataset().getDatasetSize());
            currentImage = NN.testImage(n);
            setNewImage();
        });
        newImageButton.setBackground(new Color(0, 153, 255));
        buttonPanel.add(newImageButton);
        JButton newFalseImageButton = new JButton("test new Random Image from test-Set");
        newFalseImageButton.addActionListener(e -> {
            int i = 0;
            while (currentImage.isCorrect()) {
                int n = (int) (Math.random() * NN.getTestDataset().getDatasetSize());
                currentImage = NN.testImage(n);
                i++;
                if (i > 500) {
                    break;
                }
            }
            setNewImage();
        });
        newFalseImageButton.setBackground(new Color(255, 51, 0));
        buttonPanel.add(newFalseImageButton);

        c.gridx = 4;
        InfoActionPanel.add(buttonPanel);

        currentImage = NN.testImage(6969);
        setNewImage();

        panel.add(imagePanel, BorderLayout.NORTH);
        panel.add(InfoActionPanel, BorderLayout.CENTER);
        contentPain.add(panel);
        setBasics();

    }

    void setNewImage() {

        //update pixel
        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {
                JPanel p = pixel[i][j];
                int index = i * 28 + j;
                int pw = 255 - currentImage.getPixel()[index];
                p.setBackground(new Color(pw, pw, pw));
            }
        }
        imagePanel.repaint();
        // update Text
        scoreTextArea.setText(getText(currentImage));
        if (currentImage.isCorrect())
            scoreTextArea.setForeground(new Color(0, 204, 0));
        else
            scoreTextArea.setForeground(new Color(255, 51, 0));
    }

    String getText(ImageApproximation image) {
        String predictionScorestest = "Actual Number = " + currentImage.getActualNumber() + "\n";
        for (Pair<Integer, Double> i : currentImage.getImageScores()) {
            predictionScorestest += i.a.toString() + ": " + SupportingCalculations.round(i.b.doubleValue(), 4) + "\n";
        }
        return predictionScorestest;
    }

    private void setBasics() {
        this.setTitle("NN-Images");
        this.setSize(560, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
