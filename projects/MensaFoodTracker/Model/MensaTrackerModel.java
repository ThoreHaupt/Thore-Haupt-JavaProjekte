package Projects.MensaFoodTracker.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaScraperException;

public class MensaTrackerModel {
    ArrayList<MensaMealWrapper> history = new ArrayList<MensaMealWrapper>();
    ArrayList<MensaMealWrapper> selection = new ArrayList<MensaMealWrapper>();
    LocalDate date = LocalDate.now();

    final String HISTORYFILEPATH = "Projects\\MensaFoodTracker\\historyData.csv";

    public MensaTrackerModel() {
        selection = getMealSelection(LocalDate.now());
        history = loadHistoryData();
    }

    public ArrayList<MensaMealWrapper> getMealSelection(String dateString) {
        return getMealSelection(LocalDate.parse(dateString));
    }

    public ArrayList<MensaMealWrapper> getMealSelection(LocalDate date) {
        ArrayList<MensaMealWrapper> meals = null;
        while (meals == null) {
            try {
                KITMensaScraper ms = new KITMensaScraper();
                meals = new ArrayList<MensaMealWrapper>(ms.fetchMeals(MensaLocation.ADENAUERRING, date)
                        .stream()
                        .map(m -> new MensaMealWrapper(m, date)).toList());
            } catch (MensaScraperException e) {
                System.out.println("illegal day");
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("unsuccessfull attempt to get Meals");
            }
        }
        return meals;

    }

    private ArrayList<MensaMealWrapper> loadHistoryData() {
        ArrayList<MensaMealWrapper> formerHistory = new ArrayList<MensaMealWrapper>();
        try {
            File f = new File(HISTORYFILEPATH);
            if (!f.exists()) {
                System.out.println("Warning: File not found");
                return formerHistory;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr, 100);
            String header = br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                try {
                    formerHistory.add(new MensaMealWrapper(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Meal in History File has been tampered with, can not load the meal");
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formerHistory;
    }

    private void safeHistoryData() throws IOException {
        String s = MensaMealWrapper.getHeaderString() + "\n";
        for (int i = 0; i < history.size(); i++) {
            s += history.get(i).toSafeString() + "\n";
        }
        File f = new File(HISTORYFILEPATH);
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        fw.append(s);
        fw.flush();
        fw.close();
    }

    public void closeModel() {
        try {
            safeHistoryData();
        } catch (IOException e) {
        }
    }

    public void setDate(LocalDate date) {
        if (this.date.compareTo(date) == 0)
            return;
        this.date = date;
        selection = getMealSelection(date);
    }

    public ArrayList<MensaMealWrapper> getMealSelection() {
        return selection;
    }

    public void addToHistory(MensaMealWrapper[] activeMeals) {
        history.addAll(Arrays.asList(activeMeals));
    }

    public LocalDate getSelectedDate() {
        return date;
    }

    public ArrayList<MensaMealWrapper> getMealHistory() {
        return history;
    }

    public void deleteFromHistoryButton(MensaMealWrapper[] activeMeals) {
        System.out.println("deleting: " + activeMeals.length);
        System.out.println("Länge voher: " + history.size());
        history.removeAll(Arrays.asList(activeMeals));
        System.out.println("Länge nachher: " + history.size());
    }

    public MensaStatistic calculateHistoryStatistics() {
        float cost = 0;
        float carbs = 0;
        float fat = 0;
        float proteins = 0;
        float kohlen = 0;
        float sugar = 0;

        for (MensaMealWrapper meal : history) {
            cost += meal.getPrice();
            carbs += meal.getCarbs();
            fat += meal.getFat();
            proteins += meal.getProteins();
            kohlen += meal.getKcal();
            sugar += meal.getSugar();
        }

        HashMap<String, Float> stats = new HashMap<>();
        stats.put("Kosten:", cost);
        stats.put("Kalorien", carbs);
        stats.put("Fett", fat);
        stats.put("sugar", sugar);
        stats.put("Proteine", proteins);
        stats.put("Kohlenhydrate", kohlen);
        return new MensaStatistic(stats);
    }

    public static String reverseDateFormat(String s, String del) {
        String[] arr = s.split(del);
        return arr.length == 3 ? arr[2] + "-" + arr[1] + "-" + arr[0] : "0-0-0";
    }
}
