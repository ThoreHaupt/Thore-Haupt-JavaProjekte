package OR;

import java.util.Scanner;

import OR.lib.LinearProblem;
import OR.lib.Restriction;
import OR.lib.TargetFunction;
import OR.SimplexStuff.Simplex;

public class OptimisationCalculator {
    boolean stopped = false;

    public static void main(String[] args) {
        new OptimisationCalculator();
    }

    public OptimisationCalculator() {
        Scanner scanner = new Scanner(System.in);
        LinearProblem lp = new LinearProblem(null, null);
        int c = 0;
        String[] initOrders = {
                "addR 1x1 + 2x3 - 4x3 + 2 x4 <= 30",
                "addR 3x1 + 2x3  <= 40",
                "addR 1x1 - 6x2 + 2 x4 <= 23",
                "setTF max x1 + 4x2",
                "print"
        };

        while (!stopped) {
            System.out.print(">>");
            String inp = c < initOrders.length ? initOrders[c++] : scanner.nextLine(); // scanner.nextLine()
            int wordEnd = inp.indexOf(" ");
            wordEnd = (wordEnd != -1) ? wordEnd : inp.length();
            String command = inp.substring(0, wordEnd);
            String arg = inp.substring(wordEnd, inp.length());

            switch (command) {
                case "shutdown":
                    stopped = true;
                    break;

                case "help":
                    break;

                case "addR":
                    lp.addRestriction(new Restriction(arg));
                    break;

                case "setTF":
                    lp.setTF(new TargetFunction(arg));
                    break;

                case "print":
                    System.out.println(lp);
                    break;

                case "solve":
                    Simplex.solve(lp);
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}