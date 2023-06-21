package Projects.MensaFoodTracker.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MensaStatistic implements Iterable<Entry<String, Float>> {

    private final HashMap<String, Float> stats;

    public MensaStatistic(HashMap<String, Float> stats) {
        this.stats = stats;
    }

    public int getStatNum() {
        return stats.size();
    }

    @Override
    public Iterator<Entry<String, Float>> iterator() {
        return stats.entrySet().iterator();
    }

}
