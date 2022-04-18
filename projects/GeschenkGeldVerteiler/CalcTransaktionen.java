package projects.GeschenkGeldVerteiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.border.LineBorder;

import PracticeProjects.Filemanager;
import projects.SupportingClaculations;

public class CalcTransaktionen {
    static ArrayList<Person> participants = new ArrayList<Person>();
    static String[] file;

    public static void main(String[] args) {
        file = PracticeProjects.Filemanager.getallLinesFromFile("projects/GeschenkGeldVerteiler/solution.txt");
        int line = 0;
        String[] lineString;

        while (!file[line++].equals("Gezahlt =")) {
        }
        System.out.println(line);
        while (!file[line].equals("Output =")) {
            lineString = file[line].split(" ");
            if (lineString.length <= 1)
                continue;
            System.out.println(lineString[0] + " " + lineString[1]);
            Person p = new Person(lineString[0], Double.parseDouble(lineString[1]));
            participants.add(p);
            line++;
        }
        double sum = 0;
        for (Person person : participants) {
            sum += person.paid;
        }

        double toContribute = sum / participants.size();
        String printstring = "Insgesammt Ausgegeben: " + SupportingClaculations.round(sum, 2) + "\n"
                + "Durchschnittline Kosten: " + SupportingClaculations
                        .round(toContribute, 2);

        Filemanager.writeToFile("projects/GeschenkGeldVerteiler/solution.txt", printstring, true);
        int debterCount = 0;
        for (Person person : participants) {
            person.balance = person.paid - toContribute;
            if (person.balance < 0)
                debterCount++;
        }
        Person[] debters = new Person[debterCount];
        Person[] loaners = new Person[participants.size() - debterCount];
        int counterDe = 0;
        int counterLo = 0;
        for (Person person : participants) {
            if (person.balance < 0) {
                debters[counterDe++] = person;
            } else {
                loaners[counterLo++] = person;
            }
        }
        Arrays.sort(debters);
        Arrays.sort(loaners);
        System.out.println(Arrays.toString(debters));
        System.out.println(Arrays.toString(loaners));

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        for (int i = 0, k = 0; i < debters.length; i++) {
            Person debtorInCycle = debters[i];
            while (debtorInCycle.balance < 0) {
                if (Math.abs(debtorInCycle.balance) < loaners[k].balance) {
                    Transaction t = new Transaction(debtorInCycle.balance, debtorInCycle, loaners[k]);
                    transactions.add(t);
                    break;
                } else {
                    Transaction t = new Transaction(loaners[k].balance, debtorInCycle, loaners[k]);
                    transactions.add(t);
                    if (k == loaners.length - 1) {
                        break;
                    } else {
                        k++;
                    }

                }
            }
        }
        System.out.println(Arrays.toString(transactions.toArray()));
        String[] arr = new String[transactions.size()];
        Object[] tArray = transactions.toArray();
        for (int i = 0; i < transactions.size(); i++) {
            arr[i] = tArray[i].toString();
            Filemanager.writeToFile("projects/GeschenkGeldVerteiler/solution.txt", transactions.toArray()[i].toString(),
                    true);
        }

    }
}
