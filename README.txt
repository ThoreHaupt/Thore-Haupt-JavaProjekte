2 Ordner: 
    1. Rechnerpraktika Aufgaben (/RP..)
    2. Experiments and Practice (/PracticeProjects..)


Experimente/ meine Implementationen von Sortieralgorithmen
    Die Menge der zu sortierenden Strings kann in Taskmanager angegeben werden. 
    Es werden zufällige Wörter aus einer Liste mit deutschen und englischen wörtern ausgewählt.
    Ausgabe der gemessenden Zeiten ist unter Textfiles/Console.


"Problem"/Frage in mergeInsertSort

Struktur: 
taskmanager: 
    main  
        compareSortingalgorithms
            testInsertSortingalgorithms
                Sort. mergeInsertSort: ln 68
            

Frage an Experten: 
    in Sort.java:mergeInsertSort: ln 21:
    steht:

    (int) chuncklist.get(chuncklist.size() - 1) == (int) chuncklist.get(chuncklist.size() - 2)

    Frage, warum müssen die explizite int casts da hin? Sollten nicht beide Werte von chuncklist einen int zurückgeben? 
    So wie es da steht funktioniert es, aber wenn (int) fehlt, funktioniert die Aussage nur bis zum Wert 1875 (das ist von den Parametern abhängig, 
    aber es gibt immer einen Schwellenwert) 
    Das ist der Wert den beide Variablen haben, zumindest im Debugmode auch. Jedoch gibt dieses Statement dann false zurück

    Hier ist die Deklaration und Initialisierung von chuncklist: (ln 42)
        ArrayList<Integer> chuncklist = new ArrayList<Integer>();

Vielen Dank für die Information!
