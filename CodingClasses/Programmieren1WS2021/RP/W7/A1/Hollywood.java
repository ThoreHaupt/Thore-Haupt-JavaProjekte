package RP.W7.A1;


/**
 * Hollywood-Klasse, die das Zusammenspiel der Klassen Serie, Charakter und Schauspieler demonstriert
 * @author Hans Wiwi
 * @version 1.0
 */

public class Hollywood
{
	 /**
     * Hauptprogramm.
     * @param args Kommandozeilenarguemnte (hier unverwendet)
     */
	public static void main ( String[] args)
	{
		Serie bb = new Serie("Breaking Bad", 3);
        Serie lost = new Serie ("LOST", 5);
        Serie mf = new Serie ("Modern Family", 3);
        Serie Expanse = new Serie("The Expanse", 3);

        //Demonstriere eine Serie///
        Schauspieler bryan = new Schauspieler("Bryan Cranston");
        Schauspieler aaron = new Schauspieler("Aaron Paul");
        Schauspieler anna = new Schauspieler("Anna Gunn");

        Schauspieler steven = new Schauspieler("Steven Strait");
        Schauspieler dom = new Schauspieler("Dominique Tipper");
        Schauspieler frankie = new Schauspieler("Fankie Adams");
        
        Charakter walter_white = new Charakter("Walter", bb, bryan);
        Charakter jesse = new Charakter("Jesse", bb, aaron);
        Charakter skyler = new Charakter("Skyler", bb, anna);

        Charakter james = new Charakter("James Holden", Expanse, steven);
        Charakter naomi = new Charakter("Naomi", Expanse, dom);
        Charakter robbie = new Charakter("Robbie", Expanse, frankie);
        


        System.out.println(bb);
        System.out.println(Expanse);
        //Weitere Serien//
        System.out.println(lost);
		System.out.println(mf);		
       
	}
}
