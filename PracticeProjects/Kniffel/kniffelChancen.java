package PracticeProjects.Kniffel;

public class kniffelChancen {

    public static double chanceSum = 0;

    public static void main(String[] args) {
        System.out.println(bernouli(6, 1, 0.1));
        calcKniffelChance();
        System.out.println(bernouli(4 * 13, 0, chanceSum));
    }

    public static void calcKniffelChance() {
        calcChanceKniffelAfter(1, 1, 1);
        System.out.println(chanceSum);
    }

    public static void calcChanceKniffelAfter(int level, double chance, int gezogen) {
        if (level == 3) {

            double tree = chance;
            for (int i = 0; i < gezogen; i++) {
                tree *= (1.0 / 6);
            }
            System.out.println(tree);
            chanceSum += tree;
        } else {
            for (int i = 0; i <= 5 - gezogen; i++) {
                System.out.print("l = " + level + " " + i + " ");
                calcChanceKniffelAfter(level + 1, chance * bernouli(5 - gezogen, i, (1.0 / 6)), gezogen + i);
            }
        }

    }

    public static double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= n;
        }
        return result;
    }

    public static double factorial_(int n, int k) {
        double result = 1;
        for (int i = k + 1; i <= n; i++) {
            result *= n;
        }
        return result;
    }

    public static double binominalCoefficient(int n, int k) {
        if (n < 15)
            return factorial(n) / (factorial(k) * factorial(n - k));
        else
            return factorial_(n, (n > (n - k) ? n : (n - k)));
    }

    public static double bernouli(int n, int k, double p) {
        return binominalCoefficient(n, k) * Math.pow(p, k) * Math.pow((1 - p), (n - k));
    }
}