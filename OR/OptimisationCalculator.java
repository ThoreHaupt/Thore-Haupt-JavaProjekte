package OR;

import java.util.Scanner;

import OR.lib.LinearProblem;
import OR.lib.Restriction;
import OR.lib.Solution;
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
                "addR -1x1 + 1x2 <= 4",
                "addR -2x1 + 1x2  <= 2",
                "setTF max 1x1 + 2x2",
                "print n",
                "standardize",
                "print",
                "solve"
        };

        while (!stopped) {
            System.out.print(">>");
            String inp = c < initOrders.length ? initOrders[c++] : scanner.nextLine(); // scanner.nextLine()
            int wordEnd = inp.indexOf(" ");
            wordEnd = (wordEnd != -1) ? wordEnd : inp.length();
            String command = inp.substring(0, wordEnd);
            String arg = inp.substring(wordEnd, inp.length());
            arg = arg.strip();
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
                    if (arg.equals("n"))
                        System.out.println(lp.problemToString());
                    else
                        System.out.println(lp.problemToString_st());
                    break;

                case "solve":
                    Solution s = Simplex.solve(lp);
                    System.out.println(s);
                    break;

                case "standardize":
                    lp.standardize();
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }
}