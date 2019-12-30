package fr.istic.prg1.tp3;

import java.util.Scanner;

public class InsertionInteger {
	
	private static final int SIZE_MAX = 10;
	/**
	 * nombre d'entiers présents dans t,0<= size <=SIZE_MAX
	 */
	private int size;
	private int[] array = new int[SIZE_MAX];
	
	public InsertionInteger() { 
		size = 0;
	}
	
	/**
	 * 
	 * @return copie de la partie remplie du tableau
	 */
	public int[] toArray() 
	{
		int[] tab = new int [size];
		for (int i = 0;i<size;i++) {
			tab[i]=array[i];
		}
		return tab;
	}
	
	/**
	 * si x n'appartient pas a array[0...size-1] et size <SIZE_MAX,
	 * size est incrémenté de 1, x est inséré dans array et les entiers array[0...size] sont triés par ordre croissant.
	 * sinon array est inchangé.
	 * 
	 * Exemple :
	 * pour x = 5, size = 3, array[0] = 1, array[1] = 6, array[2] =8,
	 * insertion delivre true, size = 4,
	 * array[0] = 1, array[1] = 5, array[2] = 6, array[3] = 8
	 * 
	 * @param value
	 * 			valeur à insérer
	 * @pre les valeurs de array[0..size-1] sont triées par ordre croissant
	 * 
	 * @return false si x appartient à array[0...size-1] ou si array est completement rempli;
	 * 		true si x n'appartient pas a array[0...size-1]
	 */
	 public boolean insert(int value) {
		if(size < SIZE_MAX) { //Si le tableau n'est pas remplis
			for(int i=0;i<size;i++) {
				if(value==array[i]) { //Si la valeur est deja dans le tableau
					return false;
				}
				else if(value<array[i]) { //Si la valeur est inferieur au reste du tableau
					size++;
					for(int j=size-1;j>i;j--){
						array[j] = array[j-1]; // d�calage 
					}
					array[i]=value;
					return true;	
				}
			} 
			//Si la valeur n'a pas ete insere suite au parcours => Donc valeur plus grand que tout le tableau (value>= pour eivter le rajout de valeur n�gative)
			array[size]=value;
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * array est rempli, par ordre croissant, en utilisant la fonction insert avec les valeurs lues par scanner.
	 * 
	 * @param scanner
	 */
	public void createArray(Scanner scanner) {
		int n=scanner.nextInt();
		while((n!=-1) && n>=0) {
			insert(n);
			n=scanner.nextInt();
		}
	}
	
	@Override
	public String toString() {
		String res = "[";
		for(int i  = 0; i<size;i++) {
			res= res + " " + array[i];
		}
		return res + " ]";
	}
	
}