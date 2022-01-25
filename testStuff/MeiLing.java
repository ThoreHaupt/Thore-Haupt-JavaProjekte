package testStuff;

public class MeiLing {

    // das ist das Object für später, dont worry about it
    public static class myObj {
        public int value;

        public myObj(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        System.out.println("int:");

        int i = 5;
        verringern(i); // macht nichts
        System.out.println(i); // 5
        System.out.println(verringern(i)); // 4

        System.out.println("");
        // das war das äquivalent zu der Aufgabe.
        // Hier wurde durch das erste Aufrufen von Verringern nichts verändert,
        // weil der returnwert von verringern(i) nicht in i gespeichert wurde.

        System.out.println("Integer:");

        Integer j = 5;
        verringern(j); // macht auch nichts
        System.out.println(j); // 5
        System.out.println(verringern(j)); // 4

        System.out.println("");

        // Ich habe gerade Sche*** gelabert( sorry :) ), mit den Hüllklassen
        // funktioniert das nicht(anders als ich gerade behauptet hatte).
        // Das liegt daran, dass diese besonders sind in dem Sinne,
        // dass sie immutable sind, also der Wert von dem Intger Object kann nicht
        // verändert werden, sondern wenn ein Integer verändert werden soll, erstellt
        // Java ein neues Object. Das ist ähnlich wie bei Strings (ungefähr, frag da
        // sonst nochmal deinen Tutor oder so)

        System.out.println("myObject");
        myObj obj = new myObj(5);
        verringern(obj); // verändert obj.value um -1
        System.out.println(obj.value); // 4
        System.out.println(verringern(obj).value); // 3

        System.out.println("");
        // Hier siehst du was passiert wenn du einen richtigen Referenzdatentyp (obj) in
        // verringern gibst und einen Wert von myObj veränderst. Das liegt daran, dass
        // du hier die Variable auf dem gleichen Speicherplatz veränderst. myObject ist
        // nur die Adresse von dem Object, hat aber keinen eigentlichen Wert in dem
        // Sinne.

    }

    public static int verringern(int a) {
        a--;
        return a;
    }

    public static Integer verringern(Integer a) {
        a--;
        return a;
    }

    public static myObj verringern(myObj a) {
        a.value--; // das ändert den wert von a.value, da es eine Referenz ist, ist das auch
                   // außerhalb der Methode so.
        return a;
    }
}
