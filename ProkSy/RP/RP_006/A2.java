package ProkSy.RP.RP_006;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class A2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(" Alfons ", " Rocko ", " Willhelm ", " Henrietta ",
                " Heidrun ", " Leonie ");
        System.out.println(
                list.stream().filter(e -> e.toCharArray()[1] == 'H').sorted().collect(Collectors.joining(" ")));
        System.out.println(
                list.stream().filter(e -> e.length() > 5).sorted((x, y) -> Integer.compare(x.length(), y.length()))
                        .collect(Collectors.joining(" ")));

        System.out.println(
                list
                        .stream()
                        .map(e -> {
                            List<Character> l = Arrays
                                    .asList(e.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

                            Collections.reverse(l);
                            return l.stream().map(c -> c + "").collect(Collectors.joining(""));
                        })
                        .limit(4)
                        .collect(Collectors.joining(" ")));
        int i = list
                .stream()
                .map(e -> {
                    List<Character> l = Arrays
                            .asList(e.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

                    Collections.reverse(l);
                    return l.stream().map(c -> c + "").collect(Collectors.joining(""));
                })
                .limit(4)
                .map(s -> (int) (s.length() * 2))
                .mapToInt(e -> (int) e)
                .sum();

    }
}
