package fr.istic.prg1.list;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import fr.istic.prg1.list_util.Comparison;
import fr.istic.prg1.list_util.Iterator;
import fr.istic.prg1.list.util.List;
import fr.istic.prg1.list.SmallSet;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2018-10-02
 */

public class MySet extends List<SubSet> {

	/**
	 * Borne supérieure pour les rangs des sous-ensembles.
	 */
	private static final int MAX_RANG = 128;
	/**
	 * Sous-ensemble de rang maximal à mettre dans le drapeau de la liste.
	 */
	private static final SubSet FLAG_VALUE = new SubSet(MAX_RANG,
			new SmallSet());
	/**
	 * Entrée standard.
	 */
	private static final Scanner standardInput = new Scanner(System.in);

	public MySet() {
		super();
		setFlag(FLAG_VALUE);
	}

	/**
	 * Fermer tout (actuellement juste l'entrée standard).
	 */
	public static void closeAll() {
		standardInput.close();
	}

	private static Comparison compare(int a, int b) {
		if (a < b) {
			return Comparison.INF;
		} else if (a == b) {
			return Comparison.EGAL;
		} else {
			return Comparison.SUP;
		}
	}

	/**
	 * Afficher à l'écran les entiers appartenant à this, dix entiers par ligne
	 * d'écran.
	 */
	public void print() {
		System.out.println(" [version corrigee de contenu]");
		this.print(System.out);
	}

	// //////////////////////////////////////////////////////////////////////////////
	// //////////// Appartenance, Ajout, Suppression, Cardinal
	// ////////////////////
	// //////////////////////////////////////////////////////////////////////////////

	/**
	 * Ajouter à this toutes les valeurs saisies par l'utilisateur et afficher
	 * le nouveau contenu (arrêt par lecture de -1).
	 */
	public void add() {
		System.out.println(" valeurs a ajouter (-1 pour finir) : ");
		this.add(System.in);
		System.out.println(" nouveau contenu :");
		this.printNewState();
	}

	/**
	 * Ajouter à this toutes les valeurs prises dans is.
	 * C'est une fonction auxiliaire pour add() et restore().
	 * 
	 * @param is
	 *            flux d'entrée.
	 */
	public void add(InputStream is) {
		Scanner sc = new Scanner(is);
		int n = sc.nextInt();
		while(n!=-1) {
			this.addNumber(n);
			n = sc.nextInt();
		}
	}

	/**
	 * Ajouter value à this.
	 * 
	 * @param value
	 *            valuer à ajouter.
	 */
	public void addNumber(int value) {
		Iterator<SubSet> it = this.iterator();
		while(it.getValue().rank<(value/256)){
			it.goForward();
		}
		if(it.getValue().rank==(value/256)) {
			it.getValue().set.add(value%256);
		} else {
			it.addLeft(new SubSet(value/256, new SmallSet()));
			it.getValue().set.add(value%256);
		}
	}

	/**
	 * Supprimer de this toutes les valeurs saisies par l'utilisateur et
	 * afficher le nouveau contenu (arrêt par lecture de -1).
	 */
	public void remove() {
		System.out.println("  valeurs a supprimer (-1 pour finir) : ");
		this.remove(System.in);
		System.out.println(" nouveau contenu :");
		this.printNewState();
	}

	/**
	 * Supprimer de this toutes les valeurs prises dans is.
	 * 
	 * @param is
	 *            flux d'entrée
	 */
	public void remove(InputStream is) {
		Scanner sc = new Scanner(is);
		int n = sc.nextInt();
		while(n!=-1) {
			this.removeNumber(n);
			n = sc.nextInt();
		}
	}

	/**
	 * Supprimer value de this.
	 * 
	 * @param value
	 *            valeur à supprimer
	 */
	public void removeNumber(int value) {
		Iterator<SubSet> it = this.iterator();
		while(it.getValue().rank<(value/256)){
			it.goForward();
		}
		if(it.getValue().rank==(value/256)) {
			it.getValue().set.remove(value%256);
			if(it.getValue().set.isEmpty()) {
				it.remove();
			}
		}
	}

	/**
	 * @return taille de l'ensemble this
	 */
	public int size() {
		Iterator<SubSet> it = this.iterator();
		int cpt = 0;
		while(it.getValue().rank != 128) {
			cpt = cpt + it.getValue().set.size();
			it.goForward();
		}
		return cpt;
	}


	/**
	 * @return true si le nombre saisi par l'utilisateur appartient à this,
	 *         false sinon
	 */
	public boolean contains() {
		System.out.println(" valeur cherchee : ");
		int value = readValue(standardInput, 0);
		return this.contains(value);
	}

	/**
	 * @param value
	 *            valeur à tester
	 * @return true si valeur appartient à l'ensemble, false sinon
	 */

	public boolean contains(int value) {
		Iterator<SubSet> it = this.iterator();
		while(it.getValue().rank<=(value/256)){
			if(it.getValue().rank==(value/256)) {
				return it.getValue().set.contains(value%256);
			}
			it.goForward();
		}
		return false;
	}

	// /////////////////////////////////////////////////////////////////////////////
	// /////// Difference, DifferenceSymetrique, Intersection, Union ///////
	// /////////////////////////////////////////////////////////////////////////////

	/**
	 * This devient la différence de this et set2.
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void difference(MySet set2) {
		Iterator<SubSet> it1 = this.iterator();
		Iterator<SubSet> it2 = set2.iterator();
		if(this==set2) {
			this.clear();
		} else {
			while(!it1.isOnFlag()) {
				SubSet s1 = it1.getValue();
				SubSet s2 = it2.getValue();
				if(s1.rank == s2.rank) {
					s1.set.difference(s2.set);
					if(s1.set.isEmpty()) {
						it1.remove();
					} else {
						it1.goForward();
					}
					it2.goForward();
				} else if(s1.rank < s2.rank) {
					it1.goForward();
				} else {
					it2.goForward();
				}
			}
		}
	}

	/**
	 * This devient la différence symétrique de this et set2.
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void symmetricDifference(MySet set2) {
		Iterator<SubSet> it1 = this.iterator();
		Iterator<SubSet> it2 = set2.iterator();
		if(this==set2) {
			this.clear();
		} else {
			while(!it1.isOnFlag() || !it2.isOnFlag()) {
				SubSet s1 = it1.getValue();
				SubSet s2 = it2.getValue();
				if(s1.rank == s2.rank) {
					s1.set.symmetricDifference(s2.set);
					if(s1.set.isEmpty()) {
						it1.remove();
					} else {
						it1.goForward();
					}
					it2.goForward();
				} else if(s1.rank < s2.rank) {
					it1.goForward();
				} else {
					it1.addLeft(s2.clone());
					it2.goForward();
				}
			}
		}
	}

	/**
	 * This devient l'intersection de this et set2.
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void intersection(MySet set2) {
		Iterator<SubSet> it1 = this.iterator();
		Iterator<SubSet> it2 = set2.iterator();
		while(!it1.isOnFlag()) {
			SubSet s1 = it1.getValue();
			SubSet s2 = it2.getValue();
			if(s1.rank == s2.rank) {
				s1.set.intersection(s2.set);
				if(s1.set.isEmpty()) {
					it1.remove();
				} else {
					it1.goForward();
				}
				it2.goForward();
			} else if(s1.rank < s2.rank){
				it1.remove();
			} else {
				it2.goForward();
			}
		}
	}

	/**
	 * This devient l'union de this et set2.
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void union(MySet set2) {
		Iterator<SubSet> it1 = this.iterator();
		Iterator<SubSet> it2 = set2.iterator();
		while(!it2.isOnFlag()){
			SubSet s1 = it1.getValue();
			SubSet s2 = it2.getValue();
			if(s1.rank == s2.rank) {
				s1.set.union(s2.set);
				it1.goForward();
				it2.goForward();
			} else if(s1.rank < s2.rank) {
				it1.goForward();
			} else {
				it1.addLeft(s2.clone());
				it1.goForward();
				it2.goForward();
			}
		}
		
	}

	// /////////////////////////////////////////////////////////////////////////////
	// /////////////////// Egalitï¿½, Inclusion ////////////////////
	// /////////////////////////////////////////////////////////////////////////////

	/**
	 * @param o
	 *            deuxième ensemble
	 * 
	 * @return true si les ensembles this et o sont égaux, false sinon
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null) {
			return  false;
		} else if (!(o instanceof MySet)) {
			return false;
		} else {
			Iterator<SubSet> it1 = this.iterator();
			Iterator<SubSet> it2 = ((MySet)o).iterator();
			while(!it1.isOnFlag() || !it2.isOnFlag()) {
				SubSet s1 = it1.getValue();
				SubSet s2 = it2.getValue();
				if(s1.rank == s2.rank) {
					if(s1.set.equals(s2.set)) {
						it1.goForward();
						it2.goForward();
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param set2
	 *            deuxième ensemble
	 * @return true si this est inclus dans set2, false sinon
	 */
	public boolean isIncludedIn(MySet set2) {
		Iterator<SubSet> it1 = this.iterator();
		Iterator<SubSet> it2 = set2.iterator();
		if(this.equals(set2)) {
			return true;
		} else {
			while(!it1.isOnFlag()) {
				if(it1.getValue().rank == it2.getValue().rank) {
					if(it1.getValue().set.isIncludedIn(it2.getValue().set)) {
						it1.goForward();
						it2.goForward();
					}else{
						return false;
					}
				}else if(it1.getValue().rank > it2.getValue().rank){
					it2.goForward();
				} else {
					return false;
				}
			}
		}
		return true;
	}

	// /////////////////////////////////////////////////////////////////////////////
	// //////// Rangs, Restauration, Sauvegarde, Affichage //////////////
	// /////////////////////////////////////////////////////////////////////////////

	/**
	 * Afficher les rangs présents dans this.
	 */
	public void printRanks() {
		System.out.println(" [version corrigee de rangs]");
		this.printRanksAux();
	}

	private void printRanksAux() {
		int count = 0;
		System.out.println(" Rangs presents :");
		Iterator<SubSet> it = this.iterator();
		while (!it.isOnFlag()) {
			System.out.print("" + it.getValue().rank + "  ");
			count = count + 1;
			if (count == 10) {
				System.out.println();
				count = 0;
			}
			it.goForward();
		}
		if (count > 0) {
			System.out.println();
		}
	}

	/**
	 * Créer this à partir d'un fichier choisi par l'utilisateur contenant une
	 * séquence d'entiers positifs terminée par -1 (cf f0.ens, f1.ens, f2.ens,
	 * f3.ens et f4.ens).
	 */
	public void restore() {
		String fileName = readFileName();
		InputStream inFile;
		try {
			inFile = new FileInputStream(fileName);
			System.out.println(" [version corrigee de restauration]");
			this.clear();
			this.add(inFile);
			inFile.close();
			System.out.println(" nouveau contenu :");
			this.printNewState();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("fichier " + fileName + " inexistant");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("probleme de fermeture du fichier " + fileName);
		}
	}

	/**
	 * Sauvegarder this dans un fichier d'entiers positifs terminé par -1.
	 */
	public void save() {
		System.out.println(" [version corrigee de sauvegarde]");
		OutputStream outFile;
		try {
			outFile = new FileOutputStream(readFileName());
			this.print(outFile);
			outFile.write("-1\n".getBytes());
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("pb ouverture fichier lors de la sauvegarde");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("probleme de fermeture du fichier");
		}
	}

	/**
	 * @return l'ensemble this sous forme de chaîne de caractères.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		int count = 0;
		SubSet subSet;
		int startValue;
		Iterator<SubSet> it = this.iterator();
		while (!it.isOnFlag()) {
			subSet = it.getValue();
			startValue = subSet.rank * 256;
			for (int i = 0; i < 256; ++i) {
				if (subSet.set.contains(i)) {
					String number = String.valueOf(startValue + i);
					int numberLength = number.length();
					for (int j = 6; j > numberLength; --j) {
						number += " ";
					}
					result.append(number);
					++count;
					if (count == 10) {
						result.append("\n");
						count = 0;
					}
				}
			}
			it.goForward();
		}
		if (count > 0) {
			result.append("\n");
		}
		return result.toString();
	}

	/**
	 * Imprimer this dans outFile.
	 * 
	 * @param outFile
	 *            flux de sortie
	 */
	private void print(OutputStream outFile) {
		try {
			String string = this.toString();
			outFile.write(string.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Afficher l'ensemble avec sa taille et les rangs présents.
	 */
	private void printNewState() {
		this.print(System.out);
		System.out.println(" Nombre d'elements : " + this.size());
		this.printRanksAux();
	}

	/**
	 * @param scanner
	 * @param min
	 *            valeur minimale possible
	 * @return l'entier lu au clavier (doit Ãªtre entre min et 32767)
	 */
	private static int readValue(Scanner scanner, int min) {
		int value = scanner.nextInt();
		while (value < min || value > 32767) {
			System.out.println("valeur incorrecte");
			value = scanner.nextInt();
		}
		return value;
	}

	/**
	 * @return nom de fichier saisi psar l'utilisateur
	 */
	private static String readFileName() {
		System.out.print(" nom du fichier : ");
		String fileName = standardInput.next();
		return fileName;
	}
}
