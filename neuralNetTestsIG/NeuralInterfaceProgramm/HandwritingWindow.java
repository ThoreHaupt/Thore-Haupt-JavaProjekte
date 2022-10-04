package neuralNetTestsIG.NeuralInterfaceProgramm;

import Commons.CalulationTools.SupportingCalculations;
import Commons.FileHandiling.Pair;
import Commons.UIElements.FileChooserInterface;
import Commons.UIElements.InputTextfield;
import Commons.UIElements.Progressbar;
import Commons.UIElements.technicalUIElements.DocumentNumberFilter;
import neuralNetTestsIG.Data.Dataset;
import neuralNetTestsIG.TestBasicNeuralNet.ImageApproximation;
import neuralNetTestsIG.TestBasicNeuralNet.NetworkFunctionCollection;
import neuralNetTestsIG.TestBasicNeuralNet.NeuralNet;
import neuralNetTestsIG.TestBasicNeuralNet.StoredNet;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HandwritingWindow extends JFrame {

    final public static String SIGMOID = NetworkFunctionCollection.SIGMOID;

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

        FlatDarkLaf.setup();

        Dataset trainingData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");

        int pixelNumX = trainingData.getImagePixel_X();
        int pixelNumY = trainingData.getImagePixel_Y();

        this.NN = new NeuralNet(28 * 28, new int[] { 128, 32 }, new String[] { SIGMOID,
                SIGMOID,
                SIGMOID, SIGMOID });

        NN.setTestData(new Dataset("neuralNetTestsIG/Data/Datasets/NIST/test-images",
                "neuralNetTestsIG/Data/Datasets/NIST/test-labels"));

        NN.train(0, 0.15, 500, NetworkFunctionCollection.SQUAREDISTANCE, trainingData, 6);

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
        InfoActionPanel.setLayout(gb);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        //c.gridheight = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        //c.anchor = GridBagConstraints.NORTH;

        // Buttons to load safe train and create a NN
        JPanel loadSaveTrainPanel = new JPanel(new GridLayout(4, 1));

        JButton loadNetWorkButton = new JButton("load network");
        loadNetWorkButton.addActionListener(e -> loadNetworkFromFile());
        loadSaveTrainPanel.add(loadNetWorkButton);

        JButton saveCurrentNetworkButton = new JButton("save as");
        saveCurrentNetworkButton.addActionListener(e -> saveCurrentNetwork());
        loadSaveTrainPanel.add(saveCurrentNetworkButton);

        JButton trainCurrentNetworkButoon = new JButton("continue training");
        trainCurrentNetworkButoon.addActionListener(e -> trainCurrentNetwork());
        loadSaveTrainPanel.add(trainCurrentNetworkButoon);

        JButton newNetworkButton = new JButton("create new Network");
        newNetworkButton.addActionListener(e -> createNewNet());
        loadSaveTrainPanel.add(newNetworkButton);

        c.gridy = 0;
        c.weighty = 0.15;
        InfoActionPanel.add(loadSaveTrainPanel, c);

        // InfoPanel on current Displayed Image
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(340, 400));
        JLabel title = new JLabel(" Information (Confidence)");
        infoPanel.add(title, BorderLayout.NORTH);
        scoreTextArea = new JTextArea();
        //scoreTextArea.setForeground(new Color(255, 255, 255));
        scoreTextArea.setEditable(false);
        infoPanel.add(scoreTextArea, BorderLayout.CENTER);

        c.gridy = 1;
        c.weighty = 0.40;
        InfoActionPanel.add(infoPanel, c);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton newImageButton = new JButton("test new Random Image from test-Set");
        newImageButton.addActionListener(e -> {
            int n = (int) (Math.random() * NN.getTestDataset().getDatasetSize());
            System.out.println(n);
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

        c.gridy = 2;
        c.weighty = 0.20;
        InfoActionPanel.add(buttonPanel, c);

        currentImage = NN.testImage(6969);
        setNewImage();

        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(InfoActionPanel, BorderLayout.CENTER);
        contentPain.add(panel);
        setBasics();

    }

    private void trainCurrentNetwork() {
        int epochAmount = 0;
        // create dialoge to set the amount of epochs to train;
        JDialog createNNDialoagWindow = new JDialog(this, true);
        createNNDialoagWindow.setSize(new Dimension(300, 400));
        Container contentPane = createNNDialoagWindow.getContentPane();

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        mainPanel.setLayout(gbl);
        c.fill = GridBagConstraints.HORIZONTAL;

        InputTextfield epochField = new InputTextfield("Epochs:", new DocumentNumberFilter(), new Dimension(250, 50),
                new Dimension(70, 50));
        epochField.setText("2");
        c.gridy = 0;
        mainPanel.add(epochField, c);
        InputTextfield learnrateField = new InputTextfield("Learnrate [%]:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        learnrateField.setText("25");
        c.gridy++;
        mainPanel.add(learnrateField, c);
        InputTextfield batchsizeField = new InputTextfield("Batchsize:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        batchsizeField.setText("500");
        c.gridy++;
        mainPanel.add(batchsizeField, c);
        InputTextfield threadNUMField = new InputTextfield("Batchsize:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        threadNUMField.setText("6");
        c.gridy++;
        mainPanel.add(threadNUMField, c);

        JButton start = new JButton("train");
        start.addActionListener(e -> {

            int epochNUM = Integer.parseInt(epochField.getText());
            double learnrate = Integer.parseInt(learnrateField.getText()) / 100.0;
            int batchSize = Integer.parseInt(batchsizeField.getText());
            int threadNUM = Integer.parseInt(threadNUMField.getText());
            trainNetworkPanelManager(mainPanel, epochNUM, learnrate, batchSize, threadNUM, c);
        });

        start.setBackground(new Color(26, 117, 255));
        c.gridy++;
        mainPanel.add(start, c);

        contentPane.removeAll();
        contentPane.add(mainPanel);
        createNNDialoagWindow.setVisible(true);
    }

    private void trainNetworkPanelManager(JPanel backpanel, int epochNUM, double learnRate, int batchSize,
            int threadNUM,
            GridBagConstraints c) {

        Progressbar pbar = new Progressbar(epochNUM);
        backpanel.removeAll();
        c.gridy = 0;
        backpanel.add(pbar);
        backpanel.revalidate();
        backpanel.repaint();
        NN.train(epochNUM, learnRate, batchSize, threadNUM, pbar);
    }

    private void loadNetworkFromFile() {
        JFileChooser loader = new JFileChooser();
        loader.setCurrentDirectory(
                new File(
                        "neuralNetTestsIG/Data/trainedDNN"));
        if (loader.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;
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
        NN.setTestData(new Dataset("neuralNetTestsIG/Data/Datasets/NIST/test-images",
                "neuralNetTestsIG/Data/Datasets/NIST/test-labels"));
    }

    private void saveCurrentNetwork() {
        if (NN == null)
            return;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(
                new File(
                        "C:/Users/torer/Desktop/Code/Java/repoitories/https---github.com-ThoreHaupt-SortingAlgorithmProject/neuralNetTestsIG/Data/trainedDNN"));
        fileChooser.showOpenDialog(null);
        File destination = fileChooser.getSelectedFile();
        storeNN(destination, NN);
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
            scoreTextArea.setForeground(new Color(255, 255, 255));
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
        this.setSize(800, 560);
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
        createNNDialoagWindow.setSize(new Dimension(500, 300));
        Container contentPane = createNNDialoagWindow.getContentPane();

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        FileChooserInterface NNFileLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "neuralNetTestsIG/Data/trainedDNN", "location");
        mainPanel.add(NNFileLocation, c);

        FileChooserInterface TrainingDataLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "neuralNetTestsIG/Data/trainedDNN", "TrainingData");
        c.gridy++;
        mainPanel.add(TrainingDataLocation, c);

        FileChooserInterface TestDataCreation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "neuralNetTestsIG/Data/trainedDNN", "TestData");
        c.gridy++;
        mainPanel.add(TestDataCreation, c);

        InputTextfield imageSizeTF = new InputTextfield("ImageSize:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        imageSizeTF.setText("28");
        c.gridy++;
        mainPanel.add(imageSizeTF, c);

        JButton createButton = new JButton("create");
        createButton.setBackground(new Color(26, 117, 255));
        createButton.addActionListener(e -> {
            System.out.println("createNewNN");
        });

        c.gridy++;
        mainPanel.add(createButton);

        contentPane.removeAll();
        contentPane.add(mainPanel);
        createNNDialoagWindow.setVisible(true);
    }

    public void storeNN(File f, NeuralNet DNN) {
        try {
            StoredNet SDNN = new StoredNet(DNN);
            f.createNewFile();
            ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(f));
            objOutStream.writeObject(SDNN);
            objOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
