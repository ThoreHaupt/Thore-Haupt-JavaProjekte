package ProkSy.RP.RP_007;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String[] inp = { "hall", "hallo" };
        Arrays.stream(inp).mapToInt(String::length).forEach(s -> System.out.println(s));

    }
}
