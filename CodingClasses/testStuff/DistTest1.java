package CodingClasses.testStuff;

import java.util.Arrays;
import java.util.stream.Stream;

class DistTest1 {
    public static void main(String[] args) {
        int[] h = new int[10];
        Stream.generate(Math::random).limit(10000).map(Math::sqrt).forEach(x -> h[(int) (x * 10)]++);
        System.out.println(Arrays.toString(h));
        System.out.println(Arrays.stream(h).sum());
    }
}