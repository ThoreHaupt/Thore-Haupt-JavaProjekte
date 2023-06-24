package Projects.MensaFoodTracker.View;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map.Entry;

import Projects.MensaFoodTracker.Controller.MensaTracerController;
import Projects.MensaFoodTracker.Model.MensaStatistic;
import Projects.MensaFoodTracker.Model.MensaTrackerModel;
import Projects.MensaFoodTracker.Model.MensaMealWrapper.SortOrder;

public class MensaTrackerFrame extends JFrame {
    MensaTracerController controller;
    MensaTrackerModel model;

    JButton openHistory;
    JButton openSelection;

    JPanel historyPanel;
    JPanel selectionPanel;

    JPanel centerPanel;

    public MensaTrackerFrame(MensaTracerController controller, MensaTrackerModel model) {
        this.controller = controller;
        this.model = model;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Container c = getContentPane();
        buildHistoryPanel();
        buildSlectionPanel();
        c.add(buildMainPanel());
        setToCenterPanel(selectionPanel);
        setBasics();
    }

    private JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        centerPanel = new JPanel(new BorderLayout());

        JButton openHistory = new JButton("Historie");
        JButton openSelection = new JButton("Auswahl");
        openHistory.addActionListener(e -> {
            setToCenterPanel(historyPanel);
        });
        openSelection.addActionListener(e -> {
            setToCenterPanel(selectionPanel);
        });
        topPanel.add(openSelection);
        topPanel.add(openHistory);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel statsBackground = new JPanel(new BorderLayout());
        JPanel optionPanel = new JPanel();

        MensaStatistic ms = model.calculateHistoryStatistics();
        JPanel stats = new JPanel(new GridLayout(ms.getStatNum(), 1));
        statsBackground.add(stats, BorderLayout.NORTH);
        for (Entry<String, Float> stat : ms) {
            JPanel statPanel = new JPanel(new BorderLayout());
            statPanel.add(new JLabel(stat.getKey() + "  "), BorderLayout.WEST);
            statPanel.add(new JLabel(" " + stat.getValue()), BorderLayout.EAST);
            stats.add(statPanel);
        }

        MealTablePanel listPanel = new MealTablePanel(model.getMealHistory());
        optionPanel.add(buildSortSettingPanel(listPanel));

        JButton deleteFromHistoryButton = new JButton("Auswahl von Historie löschen");
        deleteFromHistoryButton.addActionListener(controller.getDeleteFromHistoryActionListener(listPanel));

        panel.add(stats, BorderLayout.WEST);
        panel.add(listPanel, BorderLayout.CENTER);
        panel.add(deleteFromHistoryButton, BorderLayout.SOUTH);
        panel.add(optionPanel, BorderLayout.NORTH);
        panel.setBackground(new Color(100, 0, 0));
        historyPanel = panel;
        return panel;
    }

    private JPanel buildSlectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 0, 100));
        JPanel optionPanel = new JPanel(new GridLayout(1, 3));
        JPanel dateSelectionPanel = new JPanel();
        JSpinner cal = new JSpinner(new SpinnerDateModel());
        JButton selectDateButton = new JButton("select");
        DateEditor editor = new DateEditor(cal, "dd.MM.yyyy");
        cal.setEditor(editor);

        LocalDate ld = model.getSelectedDate();
        Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        cal.getModel().setValue(date);
        selectDateButton.addActionListener(controller.getSetDateButtonListener(cal));
        //cal.addChangeListener(controller.getSetDateChangeListener(cal));

        dateSelectionPanel.add(cal);
        dateSelectionPanel.add(selectDateButton);

        optionPanel.add(dateSelectionPanel);

        MealTablePanel listPanel = new MealTablePanel(model.getMealSelection());

        optionPanel.add(buildSortSettingPanel(listPanel));

        JButton select = new JButton("Zur Historie hinzufügen");
        select.addActionListener(controller.getAddHistoryListener(listPanel));

        panel.add(optionPanel, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
        panel.add(select, BorderLayout.SOUTH);
        selectionPanel = panel;
        return panel;
    }

    private void setToCenterPanel(JPanel panel) {
        centerPanel.removeAll();
        centerPanel.add(panel);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void setBasics() {
        this.setTitle("MensaFoodTracker");
        this.setSize(800, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void rebuildHistoryPanelList() {
        historyPanel = buildHistoryPanel();
        setToCenterPanel(historyPanel);
    }

    public void rebuildSelectionPanelList() {
        selectionPanel = buildSlectionPanel();
        setToCenterPanel(selectionPanel);
    }

    public MealTablePanel getSelectionPanel() {
        return (MealTablePanel) selectionPanel.getComponent(1);
    }

    public JPanel buildSortSettingPanel(MealTablePanel sortedPanel) {
        JPanel panel = new JPanel();
        JComboBox<SortOrder> cb = new JComboBox<>(SortOrder.values());
        cb.addActionListener(e -> {
            sortedPanel.setSortedOrder((SortOrder) cb.getSelectedItem());
            sortedPanel.rebuildList();
        });
        panel.add(cb);
        JCheckBox accendingSelector = new JCheckBox("aufsteigend");
        accendingSelector.setSelected(false);
        accendingSelector.addActionListener(e -> {
            sortedPanel.setAccending(accendingSelector.isSelected());
            sortedPanel.rebuildList();
        });
        panel.add(accendingSelector);
        return panel;
    }
}