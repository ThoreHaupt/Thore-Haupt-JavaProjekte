package testStuff.interfacetoabstract;

import java.util.Comparator;

public abstract class classe implements Comparator<classe> {

    @Override
    public int compare(classe o1, classe o2) {
        return 0;
    }

    abstract void m2();
}
