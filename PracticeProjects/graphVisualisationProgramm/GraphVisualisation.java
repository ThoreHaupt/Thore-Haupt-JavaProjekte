package PracticeProjects.graphVisualisationProgramm;

import javax.swing.*;

public class GraphVisualisation extends JPanel {
    JFrame frame;

    public GraphVisualisation() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graph");
        frame.add(this);
        frame.setSize(700, 550);
    }

}
