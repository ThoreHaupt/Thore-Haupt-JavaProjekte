package CodingClasses.ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Diese Klasse stellt Klassenmethoden zur Verfügung, die beim Entschlüsseln von
 * Geheimtexten helfen
 * 
 * @author Jonas Lehner
 * 
 */
public class CodeKnacker {

	/**
	 * Diese Methode verschiebt die Buchstaben eines Worten um den angegebenen Wert
	 * 
	 * @param wort         zu bearbeitendes Wort
	 * @param verschiebung Weite der Verschiebung
	 * @return bearbeitetes Wort
	 */
	public static String verschiebeWort(String wort, int verschiebung) {
		while (verschiebung < 0) {
			verschiebung = verschiebung + 26;
		}
		char buchstaben[] = wort.toUpperCase().toCharArray();
		String wortNachher = "";
		for (char buchstabe : buchstaben) {
			char buchstabeNachher = (char) (((((int) buchstabe - 65) + verschiebung) % 26) + 65);
			wortNachher += buchstabeNachher;
		}
		return wortNachher;
	}

	/**
	 * Diese Methode gibt den häufigsten Buchstaben einer Zeichenkette zurück
	 * 
	 * @param nachricht zu untersuchende Zeichenkette
	 * @return häufigster Buchstabe
	 */
	public static char haeufigsterBuchstabe(String nachricht) {
		char[] buchstaben = nachricht.toUpperCase().toCharArray();
		int[] haeufigkeiten = new int[26];
		for (char buchstabe : buchstaben) {
			if (buchstabe >= 65 & buchstabe <= 90) {
				haeufigkeiten[buchstabe - 65]++;
			}
		}
		int maxValue = 0;
		char haeufigsterBuchstabe = 0;
		for (int i = 0; i < haeufigkeiten.length; i++) {
			if (haeufigkeiten[i] > maxValue) {
				maxValue = haeufigkeiten[i];
				haeufigsterBuchstabe = (char) (i + 65);
			}
		}
		return haeufigsterBuchstabe;
	}

	public static int analysiereDatei(File f) {
		String text = "";
		String line = ".";
		try {
			BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			while ((line = fr.readLine()) != null) {
				text += line;
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 'E' - haeufigsterBuchstabe(text);
	}

	public static String verschiebeTextInDatei(File f, int i) {
		String text = "";
		int lineLength = 0;
		final int lineMaxLength = 80;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			StreamTokenizer t = new StreamTokenizer(br);
			while (t.ttype != StreamTokenizer.TT_EOF) {

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text + "\n\n\n";
	}

	public static void main(String[] args) {
		File f = new File("C:\\Users\\torer\\eclipse-workspace\\RPDemos\\Codeknacker - Vorgabe\\geheimnachricht.txt");
		System.out.println(verschiebeTextInDatei(f, analysiereDatei(f)));
	}

}
