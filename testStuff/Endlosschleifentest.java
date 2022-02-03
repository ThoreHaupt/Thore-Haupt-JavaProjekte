package testStuff;

public class Endlosschleifentest {

    public static void main(String[] args) {

        /*
         * while (true) {
         * 
         * } // nur eine Endlosschleife, kein Stack overflow Error
         * 
         * while (true) {
         * int i = 1;
         * } // Stack overflow error, da primitive Datentypen
         * // auf dem Stack gespeichert werden und der irgendwann voll ist.
         * 
         * myMethod(); // Stack overflow Error durch rekursion
         */
    }

    public static void myMethod() {
        myMethod();
    }
}
