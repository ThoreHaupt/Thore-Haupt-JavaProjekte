package ProkSy.ProkSyAltKlausurVorbereitung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class tesr123455 {
    public static void main(String[] args) {
        Supplier<Double> f = Math::random;
        for (int i = 0; i < 3; i++) {
            System.out.println(f.get());
        }

        ArrayList<String> name = new ArrayList<>();
        name.addAll(Arrays.asList(new String[] { "Ida", "Marc", "Erja", "Jan", "Stella" }));
        name.replaceAll(String::toUpperCase);
        name.sort(String::compareTo);
        name = new ArrayList<>(name.stream().map(x -> x.toLowerCase()).collect(Collectors.toList()));
        System.out.println(name.toString());
    }
}
