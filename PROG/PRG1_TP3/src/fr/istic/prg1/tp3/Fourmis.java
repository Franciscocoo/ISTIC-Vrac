package fr.istic.prg1.tp3;

public class Fourmis {
	
	/**
	 * @param s, un terme de la suite des fourmis
	 * @pre s.length() >0
	 * @return le term suivant de la suite des fourmis
	 */
	public static String next(String ui) {
		String temp = "";
		int cmpt = 1;
		for(int i=1;i<ui.length();i++){
			if(ui.charAt(i)==ui.charAt(i-1)) {
				cmpt++;
			} else {
				temp = temp.concat(String.valueOf(cmpt) + String.valueOf(ui.charAt(i-1)));//On rajoute le nombre de valeur ET ajoute la valeur actuelle
				cmpt=1;
			}
		}
		temp = temp.concat(String.valueOf(cmpt) + String.valueOf(ui.charAt(ui.length()-1)));//On rajoute le dernier nombre de valeur ET la valeur
		return temp;
	}
	
	public static void main(String[] args) {
		String u="1";
		System.out.println(u);
		for(int i=0;i<10;i++) {
			u=next(u);
			System.out.println(u);
		}
	}
}