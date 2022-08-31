package neuralNetTestsIG.NeuralInterfaceProgramm;

import Commons.CalulationTools.SupportingCalculations;
import Commons.FileHandiling.Pair;
import Commons.UIElements.FileChooserInterface;
import neuralNetTestsIG.Data.Dataset;
import neuralNetTestsIG.TestBasicNeuralNet.ImageApproximation;
import neuralNetTestsIG.TestBasicNeuralNet.NeuralNet;
import neuralNetTestsIG.TestBasicNeuralNet.StoredNet;

import javax.swing.*;
import java.awt.*;
import java.util.PrimitiveIterator.OfInt;
import java.util.jar.JarEntry;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HandwritingWindow extends JFrame {

    final public static String SIGMOID = NeuralNet.SIGMOID;

    public static void main(String[] args) {
        new HandwritingWindow();
    }

    Container contentPain;
    NeuralNet NN;
    JPanel[][] pixel;
    ImageApproximation currentImage;
    JPanel imagePanel;
    JTextArea scoreTextArea;

    int imagePixelSize = 20;
    Dimension imageDimension = new Dimension(560, 560);

    boolean drawingEnabled = false;

    boolean currentNetworkSaved = false;

    public HandwritingWindow() {

        Dataset trainingData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");

        int pixelNumX = trainingData.getImagePixel_X();
        int pixelNumY = trainingData.getImagePixel_Y();

        this.NN = new NeuralNet(28 * 28, new int[] { 128, 32 }, new String[] { SIGMOID,
                SIGMOID,
                SIGMOID, SIGMOID });

        NN.setTestData(new Dataset("neuralNetTestsIG/Data/Datasets/NIST/test-images",
                "neuralNetTestsIG/Data/Datasets/NIST/test-labels"));

        NN.train(0, 0.15, 500, NeuralNet.SQUAREDISTANCE, trainingData, 6);

        pixel = new JPanel[pixelNumY][pixelNumX];

        contentPain = this.getContentPane();
        JPanel panel = new JPanel(new BorderLayout(1, 1));

        imagePanel = new JPanel();

        // calculate optimal pixl size
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

        imagePanel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawingEnabled) {
                    int xPos = e.getX() / 20;
                    int yPos = e.getY() / 20;

                    for (int i = xPos - 1; i < xPos + 1; i++) {
                        for (int j = yPos - 1; j < yPos + 1; j++) {
                            if (!(i < 0 || j < 0 || i > 28 || j > 28)) {
                                pixel[j][i].setBackground(new Color(0, 0, 0));
                            }
                        }
                    }
                }
                int[] image = new int[28 * 28];
                for (int i = 0; i < pixel.length; i++) {
                    for (int j = 0; j < pixel[i].length; j++) {
                        image[28 * i + j] = 255 - pixel[i][j].getBackground().getBlue();
                    }
                }

                currentImage = NN.testImage(image);
                setNewParameters();
            }

        });

        JPanel InfoActionPanel = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 4;
        c.gridx = 3;
        c.fill = 2;
        c.weightx = 0.25;
        InfoActionPanel.setLayout(gb);

        JPanel loadSaveTrainPanel = new JPanel(new GridLayout(4, 1));

        JButton loadNetWorkButton = new JButton("load network");
        loadNetWorkButton.addActionListener(e -> {
            JFileChooser loader = new JFileChooser();
            loader.setCurrentDirectory(
                    new File(
                            "C:/Users/torer/Desktop/Code/Java/repoitories/https---github.com-ThoreHaupt-SortingAlgorithmProject/neuralNetTestsIG/Data/trainedDNN"));
            loader.showOpenDialog(null);
            StoredNet newNN = null;
            try {
                ObjectInputStream oInStream;
                oInStream = new ObjectInputStream(new FileInputStream(loader.getSelectedFile()));
                newNN = (StoredNet) oInStream.readObject();
                oInStream.close();
            } catch (ClassNotFoundException | IOException e1) {
                System.out.println("MaybeFileNotFound");
                e1.printStackTrace();
            }
            if (newNN == null) {
                System.out.println("Some Error");
                return;
            }
            NN = newNN.restoreNet();
        });
        loadSaveTrainPanel.add(loadNetWorkButton);

        //Save the network to a file
        JButton saveCurrentNetworkButton = new JButton("save as");

        saveCurrentNetworkButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(
                    new File(
                            "C:/Users/torer/Desktop/Code/Java/repoitories/https---github.com-ThoreHaupt-SortingAlgorithmProject/neuralNetTestsIG/Data/trainedDNN"));
            fileChooser.showOpenDialog(null);
            File destination = fileChooser.getSelectedFile();
            storeNN(destination, NN);
        });
        loadSaveTrainPanel.add(saveCurrentNetworkButton);

        JButton trainCurrentNetworkButoon = new JButton("continue training");
        loadSaveTrainPanel.add(trainCurrentNetworkButoon);

        JButton newNetworkButton = new JButton("create new Network");
        newNetworkButton.addActionListener(e -> createNewNet());
        loadSaveTrainPanel.add(newNetworkButton);
        c.gridx = 0;
        InfoActionPanel.add(loadSaveTrainPanel);

        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(" Information (Confidence)");
        infoPanel.add(title, BorderLayout.NORTH);
        scoreTextArea = new JTextArea();
        scoreTextArea.setEditable(false);
        infoPanel.add(scoreTextArea, BorderLayout.CENTER);

        InfoActionPanel.add(infoPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
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

        JButton drawOwnImageButton = new JButton("Draw A Number yourself");
        drawOwnImageButton.addActionListener(e -> {
            clearCanvas();
            drawingEnabled = true;
        });
        drawOwnImageButton.setBackground(new Color(255, 51, 0));
        buttonPanel.add(drawOwnImageButton);

        c.gridx = 4;
        InfoActionPanel.add(buttonPanel);

        currentImage = NN.testImage(6969);
        setNewImage();

        panel.add(imagePanel, BorderLayout.NORTH);
        panel.add(InfoActionPanel, BorderLayout.CENTER);
        contentPain.add(panel);
        setBasics();

    }

    private void clearCanvas() {
        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {
                JPanel p = pixel[i][j];
                int pw = 255;
                p.setBackground(new Color(pw, pw, pw));
            }
        }
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
        setNewParameters();
    }

    private void calculateOptimalSize() {

    }

    private void setNewParameters() {
        scoreTextArea.setText(getText(currentImage));
        if (currentImage.isCorrect())
            scoreTextArea.setForeground(new Color(0, 204, 0));
        else if (currentImage.getActualNumber() != -1)
            scoreTextArea.setForeground(new Color(255, 51, 0));
        else
            scoreTextArea.setForeground(new Color(0, 0, 0));
    }

    String getText(ImageApproximation image) {
        String predictionScorestest = "";
        if (currentImage.getActualNumber() != -1)
            predictionScorestest += "Actual Number = " + currentImage.getActualNumber() + "\n";
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

    // open dialog to put in options for the new net
    /**
     * 0. Select path 
     * 1. Select training data 
     * 2. Select testData 
     * 
     * 3. Select Sizes of hidden Layers
     * 4. Select Activation functions
     * 
     */
    private void createNewNet() {
        JDialog createNNDialoagWindow = new JDialog(this, true);
        Container contentPane = createNNDialoagWindow.getContentPane();

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        FileChooserInterface NNFileLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "neuralNetTestsIG/Data/trainedDNN", "location");

        mainPanel.add(NNFileLocation);

        contentPane.removeAll();
        contentPane.add(mainPanel);
        createNNDialoagWindow.setVisible(true);
    }

    public void storeNN(File f, NeuralNet DNN) {
        try {
            f.createNewFile();
            ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(f));
            objOutStream.writeObject(new StoredNet(DNN));
            objOutStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
