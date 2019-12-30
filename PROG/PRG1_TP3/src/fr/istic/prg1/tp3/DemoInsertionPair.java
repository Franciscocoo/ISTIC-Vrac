package fr.istic.prg1.tp3;

import java.util.Scanner;

public class DemoInsertionPair {

	public static void main(String[] args) {
		
		/*
		 * Démonstration de la classe InsertionPair avec utilisations des méthoes et affichage des resultats
		 */
		
		InsertionPair a= new InsertionPair();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Veuillez entrer les valeurs de votre tableau par Pair:");
		a.createArray(s);
		System.out.println("Voici le tableau trié :");
		System.out.println(a.toString());
		System.out.println("Choisisser une valeur à insérer :");
		int x = s.nextInt();
		int y = s.nextInt();
		a.insert(new Pair(x,y));
		System.out.println("Voici le tableau trié après insertion de [ "+ x +" "+ y + " ] :");
		System.out.println(a.toString());
	}
}
