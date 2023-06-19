package Projects.NeuralNetwork.NeuralInterfaceProgramm;

import Commons.CalulationTools.SupportingCalculations;
import Commons.FileHandiling.Pair;
import Commons.UIElements.FileChooserInterface;
import Commons.UIElements.InputTextfield;
import Commons.UIElements.Progressbar;
import Commons.UIElements.technicalUIElements.DocumentNumberFilter;
import Projects.NeuralNetwork.Data.Dataset;
import Projects.NeuralNetwork.TestBasicNeuralNet.ImageApproximation;
import Projects.NeuralNetwork.TestBasicNeuralNet.NeuralNet;
import Projects.NeuralNetwork.TestBasicNeuralNet.StoredNet;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

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

    int imageXSize;
    int imageYSize;

    final String defaultPath = "Projects/NeuralNetwork/Data/trainedDNN";
    String currentNNPath = defaultPath;

    public HandwritingWindow() {

        FlatDarkLaf.setup();

        Dataset trainingData = new Dataset("Projects/NeuralNetwork/Data/Datasets/NIST/train-images",
                "Projects/NeuralNetwork/Data/Datasets/NIST/train-labels");

        int pixelNumX = trainingData.getImagePixel_X();
        int pixelNumY = trainingData.getImagePixel_Y();

        this.NN = new NeuralNet(28 * 28, new int[] { 128, 32 }, new String[] { SIGMOID,
                SIGMOID,
                SIGMOID, SIGMOID });

        NN.setTestData(new Dataset("Projects/NeuralNetwork/Data/Datasets/NIST/test-images",
                "Projects/NeuralNetwork/Data/Datasets/NIST/test-labels"));

        //NN.train(0, 0.15, 500, NeuralNet.SQUAREDISTANCE, trainingData, 6);

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
                if (NN != null) {
                    currentImage = NN.testImage(image);
                } else
                    System.out.println("currently there is no NN loaded");
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
            //System.out.println(n);
            currentImage = NN.testImage(n);
            currentImage.setActualNumber(NN.getTestData().getAbsolutValueByIndex(n));
            setNewImage();
        });
        newImageButton.setBackground(new Color(0, 153, 255));
        buttonPanel.add(newImageButton);

        JButton newFalseImageButton = new JButton("test new Image from test-Set false");
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

        // currentImage = NN.testImage(6969);
        // setNewImage();
        clearCanvas();

        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(InfoActionPanel, BorderLayout.CENTER);
        contentPain.add(panel);
        setBasics();

    }

    private void trainCurrentNetwork() {
        int epochAmount = 0;
        // create dialoge to set the amount of epochs to train;
        JDialog createNNDialoagWindow = new JDialog(this, true);
        createNNDialoagWindow.setSize(new Dimension(500, 400));
        Container contentPane = createNNDialoagWindow.getContentPane();

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        mainPanel.setLayout(gbl);
        c.fill = GridBagConstraints.HORIZONTAL;

        FileChooserInterface trainDataLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "Projects/NeuralNetwork/Data/Datasets/NIST/train-images", "TrainingData");
        trainDataLocation.setPreferredSize(new Dimension(450, 40));
        c.gridy++;
        mainPanel.add(trainDataLocation, c);

        FileChooserInterface trainLabelLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "Projects/NeuralNetwork/Data/Datasets/NIST/train-labels", "TrainingLabel");
        trainLabelLocation.setPreferredSize(new Dimension(400, 40));
        c.gridy++;
        mainPanel.add(trainLabelLocation, c);

        InputTextfield epochField = new InputTextfield("Epochs:", new DocumentNumberFilter(), new Dimension(250, 50),
                new Dimension(70, 50));
        epochField.setText("2");
        c.gridy++;
        mainPanel.add(epochField, c);
        InputTextfield learnrateField = new InputTextfield("Learnrate [%%]:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        learnrateField.setText("1000");
        c.gridy++;
        mainPanel.add(learnrateField, c);
        InputTextfield momentumRateField = new InputTextfield("Momentumrate [%%]:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        momentumRateField.setText("500");
        c.gridy++;
        mainPanel.add(momentumRateField, c);
        InputTextfield batchsizeField = new InputTextfield("Batchsize:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        batchsizeField.setText("100");
        c.gridy++;
        mainPanel.add(batchsizeField, c);
        InputTextfield threadNUMField = new InputTextfield("Threadcount:", new DocumentNumberFilter(),
                new Dimension(250, 50),
                new Dimension(70, 50));
        threadNUMField.setText("6");
        c.gridy++;
        mainPanel.add(threadNUMField, c);

        JButton start = new JButton("train");
        start.addActionListener(e -> {

            int epochNUM = Integer.parseInt(epochField.getText());
            double learnrate = Integer.parseInt(learnrateField.getText()) / 10000.0;
            double momentumRate = Integer.parseInt(momentumRateField.getText()) / 10000.0;
            int batchSize = Integer.parseInt(batchsizeField.getText());
            int threadNUM = Integer.parseInt(threadNUMField.getText());
            trainNetworkPanelManager(mainPanel, epochNUM, learnrate, momentumRate, batchSize, threadNUM, c,
                    createNNDialoagWindow);

        });

        start.setBackground(new Color(26, 117, 255));
        c.gridy++;
        mainPanel.add(start, c);

        contentPane.removeAll();
        contentPane.add(mainPanel);
        createNNDialoagWindow.setVisible(true);
    }

    private void trainNetworkPanelManager(JPanel backpanel, int epochNUM, double learnRate, double momentumRate,
            int batchSize,
            int threadNUM,
            GridBagConstraints c, JDialog NNDialoagWindow) {

        Progressbar pbar = new Progressbar(epochNUM);
        pbar.getBar().addChangeListener(e -> {
            JProgressBar comp = (JProgressBar) e.getSource();
            if (comp.getValue() >= comp.getMaximum()) {
                NNDialoagWindow.dispose();
            }
        });
        backpanel.removeAll();
        c.gridy = 0;
        backpanel.add(pbar);
        backpanel.revalidate();
        backpanel.repaint();
        NN.train(epochNUM, learnRate, momentumRate, batchSize, threadNUM, pbar);
        System.out.println("started Training!");
    }

    private void loadNetworkFromFile() {
        JFileChooser loader = new JFileChooser();
        loader.setCurrentDirectory(new File(defaultPath));
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
        NN.setTestData(new Dataset("Projects/NeuralNetwork/Data/Datasets/NIST/test-images",
                "Projects/NeuralNetwork/Data/Datasets/NIST/test-labels"));

        System.out.println("loaded Network from File: " + loader.getSelectedFile().getName());
    }

    private void saveCurrentNetwork() {
        if (NN == null)
            return;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(currentNNPath));
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
        System.out.println("opening createNewNN Context Menu");
        JDialog createNNDialoagWindow = new JDialog(this, true);
        createNNDialoagWindow.setSize(new Dimension(500, 400));
        Container contentPane = createNNDialoagWindow.getContentPane();

        JPanel mainPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        mainPanel.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridy = 0;

        FileChooserInterface NNFileLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "Projects/NeuralNetwork/Data/trainedDNN", "location");

        NNFileLocation.setPreferredSize(new Dimension(450, 40));
        mainPanel.add(NNFileLocation, c);

        //Dataset Paths:

        FileChooserInterface testDataLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "Projects/NeuralNetwork/Data/Datasets/NIST/test-images", "TestData");

        testDataLocation.setPreferredSize(new Dimension(450, 40));
        c.gridy++;
        mainPanel.add(testDataLocation, c);

        FileChooserInterface testLabelLocation = new FileChooserInterface(JFileChooser.FILES_ONLY,
                "Projects/NeuralNetwork/Data/Datasets/NIST/test-labels", "TestLabel");
        testLabelLocation.setPreferredSize(new Dimension(450, 40));
        c.gridy++;
        mainPanel.add(testLabelLocation, c);

        InputTextfield hiddenLayerSizesTF = new InputTextfield("LayerSizes:", null,
                new Dimension(430, 40),
                new Dimension(250, 40));
        hiddenLayerSizesTF.setText("{784, 128, 32}");
        c.gridy++;
        mainPanel.add(hiddenLayerSizesTF, c);

        InputTextfield activationFunctionsTF = new InputTextfield("ActivationFunctions:", null,
                new Dimension(430, 40),
                new Dimension(250, 40));
        activationFunctionsTF
                .setText("{" + NeuralNet.SIGMOID + ", " + NeuralNet.SIGMOID + ", " + NeuralNet.SIGMOID + ", "
                        + NeuralNet.SIGMOID + "}");
        c.gridy++;
        mainPanel.add(activationFunctionsTF, c);

        /* InputTextfield costFunctionTF = new InputTextfield("costFunction:", new DocumentNumberFilter(),
                new Dimension(430, 40),
                new Dimension(250, 40));
        costFunctionTF.setText("squaredDistanceFunction");
        c.gridy++;
        mainPanel.add(costFunctionTF, c); */

        JButton createButton = new JButton("create");
        createButton.setBackground(new Color(26, 117, 255));
        createButton.addActionListener(e -> {
            System.out.println("createNewNN");

            // System.out.println(saving old one to its location)
            //save old NN ?

            String filelocation = NNFileLocation.getCurrentPath();
            String testDataFile = testDataLocation.getCurrentPath();
            String testLabelFile = testLabelLocation.getCurrentPath();

            String hiddenLayerSizesInputString = hiddenLayerSizesTF.getText();
            String activationFunctionInputString = activationFunctionsTF.getText();
            // String costFunctionInputString = costFunctionTF.getText();

            if (!confirmsStaticArrayInitStandarts(hiddenLayerSizesInputString)) {
                // usually we would want to show an error message here, but for now it will just do nothing
                System.out.println("illegal input in arraySizes");
                return;
            }

            if (!confirmsStaticArrayInitStandarts(activationFunctionInputString)) {
                // usually we would want to show an error message here, but for now it will just do nothing
                System.out.println("illegal input in activation Functions");
                return;
            }

            hiddenLayerSizesInputString = hiddenLayerSizesInputString.substring(1,
                    hiddenLayerSizesInputString.length() - 1);
            activationFunctionInputString = activationFunctionInputString.substring(1,
                    activationFunctionInputString.length() - 1);

            int[] hiddenLayerSizes_ = Arrays.stream(hiddenLayerSizesInputString.split(", ")).mapToInt(Integer::parseInt)
                    .toArray();
            int[] hiddenLayerSizes = new int[hiddenLayerSizes_.length - 1];
            String[] activationFunctions = activationFunctionInputString.split(", ");

            if (hiddenLayerSizes_.length + 1 != activationFunctions.length) {
                System.out.println("you always need one extra Activation function for the output Layer.");
                return;
            }
            for (int i = 1; i < hiddenLayerSizes_.length; i++) {
                hiddenLayerSizes[i - 1] = hiddenLayerSizes_[i];
            }

            // This is still a literal, needs to be replaced, when this is actually usable with other data.
            NN = new NeuralNet(hiddenLayerSizes_[0], hiddenLayerSizes, activationFunctions);
            //System.out.println(testDataFile);
            //System.out.println(testLabelFile);
            NN.setTestData(new Dataset(testDataFile, testLabelFile));
            System.out.println("initiated new Neural Net and set given Data as default");
            createNNDialoagWindow.dispose();
        });

        c.gridy++;
        c.gridy++;
        mainPanel.add(createButton, c);
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

    public boolean confirmsStaticArrayInitStandarts(String s) {
        if (!s.substring(0, 1).equals("{") ||
                !s.substring(s.length() - 1, s.length()).equals("}"))
            return false;
        return true;
    }

}
