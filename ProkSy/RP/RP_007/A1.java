package ProkSy.RP.RP_007;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A1 {
    public static void main(String[] args) {
        Integer[] arr = { 10, 20, 30, 40 };
        Arrays.stream(arr).filter(e -> (e % 20) == 0)
                .forEach(e -> System.out.println(e));

        List<Integer> liste = Arrays.asList(11, 21, 24, 36, 41, 55, 62, 66);
        System.out.println(liste.stream().map(e -> (e + "")).collect(Collectors.joining(" ")));
        System.out.println(liste.stream().sorted((x, y) -> Integer.compare(x % 8, y % 8)).map(e -> e + "")
                .collect(Collectors.joining(" ")));
    }
}
