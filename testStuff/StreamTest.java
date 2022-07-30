package testStuff;

import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream.iterate(0, x -> x = x + 1).limit(100).sorted().forEach(x -> System.out.println(x));
        ;

    }
}
