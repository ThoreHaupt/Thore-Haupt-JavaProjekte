package projects.GeschenkGeldVerteiler;

import java.util.ArrayList;

import Commons.CalulationTools.SupportingCalculations;

public class Person implements Comparable {
    public String name;
    public double paid;
    public double balance;
    public ArrayList<Transaction> incomingTransactions = new ArrayList<Transaction>();
    public ArrayList<Transaction> outgoingTransactions = new ArrayList<Transaction>();

    public Person(String name, double paid) {
        this.name = name;
        this.paid = paid;
    }

    public boolean compareTo(Person p) {
        return this.balance >= p.balance;
    }

    public double getDifference() {
        return balance;
    }

    @Override
    public String toString() {
        return name + ": " + balance;
    }

    @Override
    public int compareTo(Object o) {
        if (this.balance >= ((Person) o).balance)
            return 1;
        return 0;
    }

    public Transaction transfer(Transaction t) {
        outgoingTransactions.add(t);
        this.balance += t.amount;
        balance = SupportingCalculations.round(balance, 4);
        return t;
    }

    public boolean getTransfer(Transaction t) {
        if (t.recieves != this)
            return false;
        balance -= t.amount;
        balance = SupportingCalculations.round(balance, 4);
        incomingTransactions.add(t);
        return true;
    }
}
