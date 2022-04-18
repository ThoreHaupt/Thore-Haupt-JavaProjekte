package projects.GeschenkGeldVerteiler;

import projects.SupportingClaculations;

public class Transaction {
    double amount;
    Person send;
    Person recieves;

    public Transaction(double amount, Person send, Person recieves) {
        this.amount = Math.abs(amount);
        this.send = send;
        this.recieves = recieves;
        send.transfer(this);
        recieves.getTransfer(this);
    }

    @Override
    public String toString() {
        return send.name + " >> " + SupportingClaculations.round(this.amount, 2) + " >> " + recieves.name;
    }
}
