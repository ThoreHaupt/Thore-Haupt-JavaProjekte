package Projects.MensaFoodTracker.View;

import javax.swing.*;

import Projects.MensaFoodTracker.Model.MensaMealWrapper;
import Projects.MensaFoodTracker.Model.MensaMealWrapper.SortOrder;
import edu.kit.aifb.atks.mensascraper.lib.MensaLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class MealTablePanel extends JPanel {

    ArrayList<MensaMealEntry> entries = new ArrayList<MensaMealEntry>();
    ArrayList<MensaMealWrapper> mealListe;
    SortOrder order = SortOrder.DATE;
    int sortAccending = 1;

    public MealTablePanel(ArrayList<MensaMealWrapper> mealListe) {
        this.mealListe = mealListe;
        setLayout(new BorderLayout());
        add(build(mealListe), BorderLayout.CENTER);
    }

    public JScrollPane build(ArrayList<MensaMealWrapper> mealListe) {
        JPanel scrolledPanel = buildListPanel(mealListe);

        JScrollPane scrollPane = new JScrollPane(scrolledPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    public JPanel buildListPanel(ArrayList<MensaMealWrapper> mealList) {
        if (order != SortOrder.DATE) {
            return buildListPanelNoSubdivision(mealList);
        } else {
            return buildListPanelSubdevided(mealList);
        }
    }

    public JPanel buildListPanelNoSubdivision(ArrayList<MensaMealWrapper> mealList) {
        JPanel panel = new JPanel(new GridLayout(mealList.size(), 1));
        Collections.sort(mealList, order.getComp(sortAccending));
        for (MensaMealWrapper m : mealList) {
            MensaMealEntry mEntry = new MensaMealEntry(m);
            panel.add(mEntry);
            entries.add(mEntry);
        }
        return panel;
    }

    public JPanel buildListPanelSubdevided(ArrayList<MensaMealWrapper> mealList) {
        ArrayList<JPanel> dateGroupPanels = new ArrayList<>();
        Collections.sort(mealList, SortOrder.DATE.getComp(sortAccending));
        ArrayList<MensaMealWrapper> localMealList = new ArrayList<MensaMealWrapper>();
        localMealList.addAll(mealList);
        // HashMap with lists for each Line.
        HashMap<MensaLine, ArrayList<MensaMealWrapper>> lineWrapper = new HashMap<>();

        while (localMealList.size() > 0) {
            lineWrapper.clear();
            for (MensaLine line : MensaLine.values()) {
                lineWrapper.put(line, new ArrayList<MensaMealWrapper>());
            }
            // get Meals with same Date
            java.util.List<MensaMealWrapper> sameDate = popAllSameDate(localMealList);
            for (MensaMealWrapper meal : sameDate) {
                lineWrapper.get(meal.getLine()).add(meal);
            }
            int diffLines = 0;
            for (ArrayList<MensaMealWrapper> line : lineWrapper.values()) {
                diffLines += Math.min(line.size(), 1);
            }
            JPanel dayPanel = new JPanel(new GridLayout(diffLines, 1));
            dayPanel.setBackground(Color.WHITE);
            if (diffLines == 1) {
                ArrayList<MensaMealWrapper> usedLine = new ArrayList<>();
                MensaLine usedLineName = MensaLine.UNKNOWN;
                for (Entry<MensaLine, ArrayList<MensaMealWrapper>> entry : lineWrapper.entrySet()) {
                    if (entry.getValue().size() != 0) {
                        usedLine = entry.getValue();
                        usedLineName = entry.getKey();
                        break;
                    }
                }
                JPanel linePanel = new JPanel(new GridLayout(usedLine.size(), 1));
                linePanel.setBackground(Color.WHITE);

                String borderText = usedLine.get(0).getDate().toString() + " - " + usedLineName.toString();
                dayPanel.setBorder(BorderFactory.createTitledBorder(borderText));
                for (MensaMealWrapper meal : usedLine) {
                    MensaMealEntry mEntry = new MensaMealEntry(meal);
                    linePanel.add(mEntry);
                    entries.add(mEntry);
                }
                dayPanel.add(linePanel);
                continue;
            }
            System.out.println(lineWrapper.values().size());
            for (Entry<MensaLine, ArrayList<MensaMealWrapper>> entry : lineWrapper.entrySet()) {
                if (entry.getValue().size() == 0)
                    continue;
                JPanel linePanel = new JPanel(new GridLayout(entry.getValue().size(), 1));
                linePanel.setBackground(Color.WHITE);
                linePanel.setBorder(BorderFactory.createTitledBorder(entry.getKey().toString()));
                for (MensaMealWrapper meal : entry.getValue()) {
                    MensaMealEntry mEntry = new MensaMealEntry(meal);
                    linePanel.add(mEntry);
                    entries.add(mEntry);
                }
                dayPanel.setBorder(
                        BorderFactory.createTitledBorder(entry.getValue().get(0).getDate().toString()));
                dayPanel.add(linePanel);
            }
            dateGroupPanels.add(dayPanel);
        }
        //dateGroupPanels.size(), 1
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        /* FlowLayout fl = new FlowLayout();
        panel.setLayout(fl);
        panel.setComponentOrientation(ComponentOrientation.); */
        for (JPanel block : dateGroupPanels) {
            panel.add(block);
        }
        return panel;
    }

    public static class MensaMealEntry extends JButton {
        private JCheckBox b = new JCheckBox();
        private MensaMealWrapper m;
        JButton button;

        public MensaMealEntry(MensaMealWrapper m) {
            super();
            this.m = m;
            /* if (m.getEntry() == null) {
                buildEntry();
                m.setEntry(this);
            } */

            buildEntry();
            m.setEntry(this);
        }

        private JCheckBox getJCheckBox() {
            return b;
        }

        private void buildEntry() {
            setLayout(new BorderLayout());
            JPanel infoPanel = new JPanel(new BorderLayout());

            Dimension d = getPreferredSize();
            d.height = 30;
            setPreferredSize(d);
            setMinimumSize(new Dimension(0, 30));
            setMaximumSize(new Dimension(900, 0));
            addActionListener(e -> {
                b.setSelected(!b.isSelected());
            });
            JPanel ingPanel = new JPanel(new GridLayout(1, 4, 5, 5));
            /* JPanel ingPanel = new JPanel();
            ingPanel.setLayout(new BoxLayout(ingPanel, BoxLayout.X_AXIS)); */
            ingPanel.setBackground(Color.WHITE);

            Dimension boxSize = new Dimension(20, 20);
            JPanel KcalInfo = new JPanel(new BorderLayout());
            KcalInfo.add(new JLabel("" + m.getKcal()), BorderLayout.CENTER);
            KcalInfo.setBackground(Color.RED);
            KcalInfo.setPreferredSize(boxSize);
            JPanel fatInfo = new JPanel(new BorderLayout());
            fatInfo.add(new JLabel("" + m.getFat()), BorderLayout.CENTER);
            fatInfo.setBackground(Color.YELLOW);
            fatInfo.setPreferredSize(boxSize);
            JPanel sugarInfo = new JPanel(new BorderLayout());
            sugarInfo.add(new JLabel("" + m.getSugar()), BorderLayout.CENTER);
            sugarInfo.setBackground(Color.GREEN);
            sugarInfo.setPreferredSize(boxSize);

            ingPanel.add(KcalInfo);
            ingPanel.add(fatInfo);
            ingPanel.add(sugarInfo);
            ingPanel.add(Box.createRigidArea(boxSize));

            infoPanel.add(ingPanel, BorderLayout.EAST);
            infoPanel.add(new JLabel(m.getName()), BorderLayout.WEST);
            add(infoPanel, BorderLayout.CENTER);
            add(b, BorderLayout.EAST);
        }

        public boolean isSelected() {
            return b.isSelected();
        }

        public MensaMealEntry setJCheckBox(boolean bo) {
            b.setSelected(bo);
            return this;
        }

        public MensaMealWrapper getMeal() {
            return m;
        }

    }

    public MensaMealWrapper[] getActiveMeals() {
        return entries.stream().filter(b -> b.isSelected()).map(e -> e.setJCheckBox(false).getMeal())
                .toArray(MensaMealWrapper[]::new);
    }

    private java.util.List<MensaMealWrapper> popAllSameDate(ArrayList<MensaMealWrapper> mealList) {
        if (mealList.size() == 0) {
            return null;
        }
        MensaMealWrapper first = mealList.get(0);
        java.util.List<MensaMealWrapper> sameMeals = mealList.stream()
                .filter(m -> m.getDate().compareTo(first.getDate()) == 0)
                .toList();
        mealList.removeAll(sameMeals);
        return sameMeals;
    }

    public void setAccending(boolean selected) {
        sortAccending = selected ? 1 : -1;
    }

    public void setSortedOrder(SortOrder selectedItem) {
        order = selectedItem;
    }

    public void rebuildList() {
        removeAll();
        add(build(mealListe), BorderLayout.CENTER);
        repaint();
        revalidate();
    }
}
