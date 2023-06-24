package Projects.MensaFoodTracker.View;

import javax.swing.*;

import Projects.MensaFoodTracker.Model.MensaMealWrapper;
import Projects.MensaFoodTracker.Model.MensaTrackerModel;
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
            MensaMealEntry mEntry = MensaMealEntry.MensaMealEntryBuilder(m);
            panel.add(mEntry);
            entries.add(mEntry);
        }
        return panel;
    }

    public JPanel buildListPanelSubdevided(ArrayList<MensaMealWrapper> mealList) {
        Collections.sort(mealList, SortOrder.DATE.getComp(sortAccending));
        ArrayList<MensaMealWrapper> localMealList = new ArrayList<MensaMealWrapper>();
        localMealList.addAll(mealList);
        // HashMap with lists for each Line.
        HashMap<MensaLine, ArrayList<MensaMealWrapper>> lineWrapper = new HashMap<>();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        panelConstraints.weightx = 1;
        panelConstraints.weighty = 1;
        panelConstraints.gridheight = 1;
        panelConstraints.gridwidth = 1;
        panelConstraints.gridy = 0;
        panelConstraints.gridx = 0;

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
            ArrayList<Entry<MensaLine, ArrayList<MensaMealWrapper>>> lineList = new ArrayList<Entry<MensaLine, ArrayList<MensaMealWrapper>>>(
                    lineWrapper.entrySet().stream().filter(e -> e.getValue().size() > 0).toList());
            //when lineList has only 1 element -> combine Line and Date Border
            if (lineList.size() == 1) {
                Entry<MensaLine, ArrayList<MensaMealWrapper>> entry = lineList.get(0);

                JPanel linePanel = new JPanel(new GridLayout(entry.getValue().size(), 1));
                linePanel.setBackground(Color.WHITE);

                String borderText = MensaTrackerModel
                        .parseDateFormatToNormal(entry.getValue().get(0).getDate().toString()) + " - "
                        + entry.getKey().toString();

                for (MensaMealWrapper meal : entry.getValue()) {
                    MensaMealEntry mEntry = MensaMealEntry.MensaMealEntryBuilder(meal);
                    linePanel.add(mEntry);
                    entries.add(mEntry);
                }
                JPanel dayPanel = new JPanel(new BorderLayout());
                dayPanel.setBorder(BorderFactory.createTitledBorder(borderText));
                dayPanel.add(linePanel, BorderLayout.CENTER);
                dayPanel.setBackground(Color.WHITE);
                dayPanel.add(Box.createRigidArea(new Dimension(5, 2)), BorderLayout.EAST);

                panelConstraints.gridheight = entry.getValue().size();
                panel.add(dayPanel, panelConstraints);
                panelConstraints.gridy += entry.getValue().size();
                continue;
            }
            JPanel dayPanel = new JPanel(new GridBagLayout());
            dayPanel.setBackground(Color.WHITE);
            int dayLen = 0;
            GridBagConstraints dayPanelConstraints = new GridBagConstraints();
            dayPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
            dayPanelConstraints.weightx = 1;
            dayPanelConstraints.weighty = 1;
            dayPanelConstraints.gridheight = 1;
            dayPanelConstraints.gridwidth = 1;
            dayPanelConstraints.gridy = 0;
            dayPanelConstraints.gridx = 0;

            Collections.sort(lineList, MensaMealWrapper.getLineComparator());
            for (Entry<MensaLine, ArrayList<MensaMealWrapper>> entry : lineList) {
                JPanel linePanel = new JPanel(new GridLayout(entry.getValue().size(), 1));
                linePanel.setBackground(Color.WHITE);
                linePanel.setBorder(BorderFactory.createTitledBorder(entry.getKey().toString()));
                /*   */
                for (MensaMealWrapper meal : entry.getValue()) {
                    MensaMealEntry mEntry = MensaMealEntry.MensaMealEntryBuilder(meal);
                    linePanel.add(mEntry);
                    entries.add(mEntry);
                    dayLen++;
                }
                dayPanel.setBorder(
                        BorderFactory.createTitledBorder(
                                MensaTrackerModel
                                        .parseDateFormatToNormal(entry.getValue().get(0).getDate().toString())));
                dayPanelConstraints.gridheight = entry.getValue().size();
                dayPanel.add(linePanel, dayPanelConstraints);
                dayPanelConstraints.gridy += entry.getValue().size();
            }
            panelConstraints.gridheight = dayLen;
            panel.add(dayPanel, panelConstraints);
            panelConstraints.gridy += dayLen;
        }
        return panel;
    }

    public static class MensaMealEntry extends JButton {
        private JCheckBox b = new JCheckBox();
        private MensaMealWrapper m;
        JButton button;

        static MensaMealEntry MensaMealEntryBuilder(MensaMealWrapper m) {
            if (m.getUnusedEntry() == null) {
                return new MensaMealEntry(m);
            } else {
                return m.getUnusedEntry();
            }

        }

        public MensaMealEntry(MensaMealWrapper m) {
            super();
            this.m = m;
            buildEntry();
            m.setEntry(this);
        }

        private void buildEntry() {
            b.setBackground(Color.WHITE);
            setLayout(new BorderLayout());
            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setBackground(Color.WHITE);
            setBackground(Color.WHITE);
            Dimension d = getPreferredSize();
            d.height = 30;
            setPreferredSize(d);
            setMinimumSize(new Dimension(0, 30));
            setMaximumSize(new Dimension(900, 0));
            addActionListener(e -> {
                b.setSelected(!b.isSelected());
            });
            JPanel ingPanel = new JPanel(new GridLayout(1, 5, 5, 20));
            ingPanel.setBackground(Color.WHITE);

            Dimension boxSize = new Dimension(100, 30);
            JTextArea KcalInfo = new JTextArea("" + (int) m.getKcal() + " Kcal");
            KcalInfo.setBackground(Color.RED);
            KcalInfo.setPreferredSize(boxSize);
            JTextArea fatInfo = new JTextArea("" + (int) m.getFat() + " g");
            fatInfo.setBackground(Color.CYAN);
            fatInfo.setPreferredSize(boxSize);
            JTextArea sugarInfo = new JTextArea("" + (int) m.getSugar() + " g");
            sugarInfo.setBackground(Color.ORANGE);
            sugarInfo.setPreferredSize(boxSize);
            JTextArea priceInfo = new JTextArea("" + m.getPrice() + " €");
            sugarInfo.setBackground(Color.gray);
            sugarInfo.setPreferredSize(boxSize);

            ingPanel.add(priceInfo);
            ingPanel.add(Box.createRigidArea(boxSize));
            ingPanel.add(KcalInfo);
            ingPanel.add(fatInfo);
            ingPanel.add(sugarInfo);
            ingPanel.setPreferredSize(new Dimension(270, 30));

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
        sortAccending = selected ? -1 : 1;
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
