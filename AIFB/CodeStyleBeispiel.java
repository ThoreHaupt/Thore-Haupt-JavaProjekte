package AIFB;

public class CodeStyleBeispiel {
    public static void main(String[] args) {
        /* 
        Einige Angewohnheiten, die Mir zumindest immer helfen. 
        Das ganze ist abhägig von eigenen Präferenzen.
        
        Warum? 
            Lesbarkeit
            neuen Code schnell verstehen
            Bugs fixen
            standartisieren
        
        Eine Klasse pro Datei -> keine inneren/ annonymen klassen (Klassen als Teil anderer Klassen /
             Klasse ohne public unter der top-level Klasse)
        Klassennamen: Großer Anfangsbuchstabe (siehe oben)
        Variablen/Methoden: kleiner Anfangsbuchstabe
        - Varablennamen nie nur a,b,c.. nennen, sondern immer ein beschreibendes Wort. (Ausnahme zum Beispiel i in einer For-Schleife)
        - Bei mehreren Worten neue Worte groß schreiben. bsp: leftArgument 
        
        Sprache: Einheitlich
        */

        int s = 0; // schlecht
        int summe = 0; // besser

        summe = summe % 3 + 1; // immer ein Leerzeichen um Operatoren
        summe++;

        // For-Schleifen:
        // nach if/for/while/ switch() .. immer, wenn ein neuer Code Block kommt, den ihr in Klammern tut, rückt ihn ein
        for (int i = 0; i < 10; i++) { // geschwungende Klammer hier, oder in der nächsten Zeile
            // mach irgendwas
        }

        // immer Geschwungende Klammern mitschreiben, auch nach if-Statement, um Flüchtigkeitsfehler zu vermeiden.

        // vier Indentionen tief
        // --> sieht unübersichtlich aus
        for (int i = 0; i < 15; i++) {
            if (i > 5) {
                if (i < 12) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            }
        }

        // eine Indention tief -> übersichtlicher, aber Faustregel: max 3 Indentionen, ab 4 wirds kritisch
        // --> Rausziehen durch entweder neue Methode
        // --> Vermeiden von Verschachtelten if-Statements durch reihenweises abfragen und dann beenden der Methode, geht nicht immer
        for (int i = 0; i < 15; i++) {
            if (!inRange1(5, 12, i)) {
                continue;
            }
            if (i % 2 != 0) {
                continue;
            }
            System.out.println(i);
        }
    }

    // Entwicklerkommentare können nützlich sein. Vor allem bei sehr großen Programmen sehr wichtig
    /**
     * gibt an, ob ein Wert in einem offenen Intervall liegt. (Vgl in Mathe runde Klammern)
     * @param untereGrenze die untere Grenze
     * @param obereGrenze die obere Grenze
     * @param zahl die zu Testende Zahl
     * @return boolean Wert, ob die Zahl in dem offenen Intervall liegt
     */
    public static boolean inRange1(int untereGrenze, int obereGrenze, int zahl) {
        if (zahl > obereGrenze) {
            return false;
        }
        if (zahl < untereGrenze) {
            return false;
        }
        return true;
    }
}