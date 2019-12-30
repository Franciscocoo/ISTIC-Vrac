package fr.istic.prg1.tp3;

import java.util.Scanner;

public class InsertionPair {
	
	private static final int SIZE_MAX=10;
	
	/**
	 * nombre d'entiers présents dans t,0<= size <=SIZE_MAX
	 */
	private int size;
	
	private Pair[] array = new Pair[SIZE_MAX];
	
	public InsertionPair(){
		size=0;
	}
	
	/**
	 * 
	 * @return copie de la partie remplie du tableau
	 */
	public Pair[] toArray() {
		Pair[] tab = new Pair[size];
		for(int i=0;i<size;i++) {
			tab[i]=array[i];
		}
		return tab;
	}
	/**
	 * 
	 * @param value, une paire
	 * @return true si la paire est insérée dans la tableau, false sinon
	 */
	public boolean insert (Pair value) {
		if(size < SIZE_MAX) { //Si le tableau n'est pas remplis
			for(int i=0;i<size;i++) {
				if(value.equals(array[i])) { //Si la valeur est deja dans le tableau
					return false;
				} else if(value.compareTo(array[i])==-1) { //Si la valeur est inferieur au reste du tableau
					size++;
					for(int j=size-1;j>i;j--){
						array[j] = array[j-1]; // décalage 
					}
					array[i]=value;
					return true;	
				}
			}
			//Si la valeur n'a pas ete insere suite au parcours => Donc valeur plus grand que tout le tableau
				array[size]=value;
				size++;
				return true;
		}
		return false;
	}
	
	public void createArray(Scanner scanner) {
		int a = scanner.nextInt(); //On recupere le premier entier de la paire
		while(a!=-1 && a>=0) {
			int b = scanner.nextInt(); // On recupere le deuxieme entier de la paire
			if(b!=-1 && b>=0) {
				insert(new Pair(a,b)); // On insere la paire
				a = scanner.nextInt();
			} else {
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		String res = "{ ";
		for(int i=0;i<size;i++) {
			res= res + array[i].toString() + " ";
		}
		return res + " }";
	}
}