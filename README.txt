2 different folders: 
    1. Rechnerpraktika Aufgaben (/RP..)
    2. Experiments and Practice (TryingNewThings)


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
                Sort. mergeInsertSort: ln 89
            

Frage an Experten: 
    in Sort.java:mergeInsertSort: ln 993:
    steht:

    (int) chuncklist.get(chuncklist.size() - 1) == (int) chuncklist.get(chuncklist.size() - 2)

    Frage, warum müssen die explizite int casts da hin? Sollten nicht beide Werte von chuncklist einen int zurückgeben? 
    So wie es da steht funktioniert es, aber wenn (int) fehlt, funktioniert die Aussage nur bis zum Wert 128, das ist auch
    der Wert den beide Variablen haben, zumindest im Debugmode auch. Jedoch gibt dieses Statement dann false zurück

    Hier ist die Deklaration und Initialisierung von chuncklist: (ln 972)
        ArrayList<Integer> chuncklist = new ArrayList<Integer>();

Vielen Dank für die Information!