package ProkSy.RP.RP_007;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A3 {
        public static void main(String[] args) {
                List<Integer> l = Stream.generate(Math::random).map(x -> (int) (201 * x) - 100).limit(50)
                                .collect(Collectors.toList());
                l.size();
                System.out.println(
                                Stream.iterate(1, x -> ++x).map(x -> Math.pow(2, x)).limit(10).map(i -> i + "")
                                                .collect(Collectors.joining(" ")));

                List<Double> doubleListe = Stream.iterate(43.46, x -> x + 0.17 + 2 * new Random().nextGaussian())
                                .map(x -> ((int) (100 * x)) / 100.0).limit(1000).collect(Collectors.toList());

                new Plot(doubleListe);
        }
}
