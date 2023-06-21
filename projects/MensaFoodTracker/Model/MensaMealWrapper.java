package Projects.MensaFoodTracker.Model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Comparator;

import CodingClasses.testStuff.abstractClass;
import edu.kit.aifb.atks.mensascraper.lib.MensaLine;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

public class MensaMealWrapper implements Comparable<MensaMealWrapper> {
    MensaMeal m;
    LocalDate date;
    private final int ARGNUM = 12;
    private final long ID = System.nanoTime();

    public MensaMealWrapper(MensaMeal m, LocalDate date) {
        this.m = m;
        this.date = date;
    }

    public MensaMealWrapper(String storedString) {
        String[] vals = storedString.split(";");
        if (vals.length != ARGNUM) {
            throw new IllegalArgumentException();
        }
        date = LocalDate.parse(vals[1]);
        m = MensaMeal.builder().name(vals[0]).line(MensaLine.valueOf(vals[2]))
                .price(Float.parseFloat(vals[3])).type(MensaMealType.valueOf(vals[4]))
                .carbs(Float.parseFloat(vals[5])).kcal(Float.parseFloat(vals[6])).fat(Float.parseFloat(vals[7]))
                .salt(Float.parseFloat(vals[8])).proteins(Float.parseFloat(vals[9])).sugar(Float.parseFloat(vals[10]))
                .additives(Arrays.asList(vals[11].replace("[", "").replace("]", "").split(","))).build();
    }

    public String getName() {
        return m.getName();
    }

    public float getPrice() {
        return m.getPrice();
    }

    public float getCarbs() {
        return m.getCarbs();
    }

    public float getFat() {
        return m.getFat();
    }

    public float getProteins() {
        return m.getProteins();
    }

    public float getKcal() {
        return m.getKcal();
    }

    public String toSafeString() {
        String s = "";
        s += m.getName() + ";" + date.toString()
                + ";" + m.getLine() + ";" + m.getPrice() + ";" + m.getType() + ";" + m.getCarbs()
                + ";" + m.getKcal() + ";" + m.getFat() + ";" + m.getSalt() + ";" + m.getProteins()
                + ";" + m.getSugar()
                + ";" + m.getAdditives().toString();
        return s;
    }

    @Override
    public String toString() {
        return "" + ID;
    }

    public ChronoLocalDate getDate() {
        return date;
    }

    static abstract class FlexComparator<T> implements Comparator<T> {

        int factor = 1;

        /**
         * @param factor the factor to set
         */
        public void setFactor(int factor) {
            this.factor = factor;
        }
    }

    public static FlexComparator<MensaMealWrapper> getDateMealComparator() {
        return new FlexComparator<MensaMealWrapper>() {
            @Override
            public int compare(MensaMealWrapper o1, MensaMealWrapper o2) {
                return o1.getDate().compareTo(o2.getDate()) * factor;
            }
        };

    }

    public static FlexComparator<MensaMealWrapper> getPriceMealComparator() {
        return new FlexComparator<MensaMealWrapper>() {
            @Override
            public int compare(MensaMealWrapper o1, MensaMealWrapper o2) {
                return Float.compare(o1.getPrice(), o2.getPrice()) * factor;
            }
        };
    }

    public static FlexComparator<MensaMealWrapper> getKcalMealComparator() {
        return new FlexComparator<MensaMealWrapper>() {
            @Override
            public int compare(MensaMealWrapper o1, MensaMealWrapper o2) {
                return Float.compare(o1.getKcal(), o2.getKcal()) * factor;
            }
        };
    }

    public static enum SortOrder {
        DATE("Datum", getDateMealComparator()),
        PRICE("Preis", getPriceMealComparator()),
        KCAL("Kalorien", getKcalMealComparator());

        String name;
        FlexComparator<MensaMealWrapper> comp;

        private SortOrder(String s, FlexComparator<MensaMealWrapper> comp) {
            this.name = s;
            this.comp = comp;
        }

        public FlexComparator<MensaMealWrapper> getComp(int sortAccending) {
            comp.setFactor(sortAccending);
            return comp;
        }
    }

    public MensaLine getLine() {
        return m.getLine();
    }

    @Override
    public int compareTo(MensaMealWrapper o) {
        return ID == o.ID ? 0 : getDateMealComparator().compare(this, o);
    }
}
