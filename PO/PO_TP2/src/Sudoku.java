public class Sudoku {
	/*
	 * 
	 * PI2, TP n 2
	 * Groupe XXX
	 * Binome : XXX, YYY
	 * 
	 */
	static final int n = 3 ;		// taille des regions
	/*
	 * Terminologie
	 * 
	 * m est un plateau (de sudoku) si
	 * 	- m est un int [][] ne contenant que des entiers compris entre 0 et 9
	 * 	- m.length = n^2
	 *  - m[i].length = n^2 pour tous les i de 0 a n^2-1
	 *  
	 */

	static String enClair (int [][] m) {
		/*
		 * Prerequis : m est un plateau de sudoku
		 * Resultat : une chaine dont l'affichage permet de visualiser m
		 * 
		 */
		String r = "" ;		
		for (int i = 0; i < n*n ; i++) {
			for (int j = 0; j < n*n ; j++) {
				r = r + m[i][j] + " " ;
				if (j%n == n-1) {r = r + "  ";}
			}
			if (i%n == n-1) {r = r + "\n";}
			r = r + "\n";
		}		
		r = r + " " ;		
		return r ;
	} // enClair
	
	static int [][] aPartirDe (String s) {
		/*
		 * Prerequis : s est une chaine contenant au moins n^4 chiffres decimaux
		 * Resultat : un plateau de sudoku initialise avec les n^4 premiers chiffres
		 * decimaux de s (les chiffres sont consideres comme ranges par lignes).
		 */
		int [][] m = new int [n*n][n*n] ;
		int k = 0 ;
		for (int i = 0; i < m.length ; i++) {
			for (int j = 0; j < m[i].length ; j++) {
				while ("0123456789".indexOf(s.charAt(k))==-1) {k++;}
				m[i][j] = (int) s.charAt(k) - (int) '0' ;
				k++ ;
			}			
		}
		return m ;
		
	} // aPartirDe
	
	static boolean presentLigne (int [][] m, int v, int i) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la ligne i
		 * 
		 */
		if((i>=9)||(v>=10)) {
			return false;
		}
		for(int j=0;j<m[0].length;j++) {
			if(m[i][j]==v) {
				return true;
			}
		}
		return false;
	} // presentLigne
	
	static boolean presentColonne (int [][] m, int v, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - j est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la colonne j
		 * 
		 */
		if((j>=9)||(v>=10)) {
			return false;
		}
		for(int i =0;i<m.length;i++) {
			if(m[i][j]==v) {
				return true;
			}
		}
		return false ;
	} // presentColonne
	
	static boolean presentRegion  (int [][] m, int v, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i et j sont compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la region contenant la case <i, j>
		 * 
		 */
		int iCoin = i-i%3;
		int jCoin = j-j%3;
		for (int a  = iCoin; a<iCoin+3; a++) {
			for (int b = jCoin; b < jCoin+3; b++) {
				if (m[a][b]==v) {
					return true; 
				}
			}
		}
		return false ; 
	} // presentRegion
	
	static boolean [] lesPossiblesEn (int [][] m, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - i et j sont compris entre 0 et n^2-1
		 *  - m[i][j] vaut 0
		 * Resultat : un tableau r de longueur n^2+1 tel que, dans la tranche r[1..n^2]
		 * r[v] indique si v peut etre place en <i, j>
		 * 
		 */
		boolean []tab = new boolean[9];
		tab[0]= false;
		for (int a = 1; a<tab.length; a++) {
			
			if (presentRegion(m, a, i, j) || presentLigne(m,a,i)|| presentColonne(m,a,j)) {
				tab[a]= false;
			}
			else {
				tab[a]=true;
			}
		}
		return tab ; // A MODIFIER
	} // lesPossiblesEn
	
	static String enClair (boolean[] t) {
		/*
		 * Prerequis : t.length != 0
		 * Resultat :
		 * une chaine contenant tous les indices i de la tranche [1..t.length-1] tels
		 * que t[i] soit vrai
		 */
		String r = "{" ;
		for (int i = 1; i < t.length; i++) {
			if (t[i]) {r = r + i + ", " ; }
		}
		if (r.length() != 1) {r = r.substring(0, r.length()-2);}
		return r + "}" ;
	} // enClair
	
	static int toutSeul (int [][] m, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - i et j sont compris entre 0 et n^2-1
		 *  - m[i][j] vaut 0
		 * Resultat :
		 *  - v 	si la seule valeur possible pour <i, j> est v
		 *  - -1 	dans les autres cas
		 * 
		 */
		boolean [] t =lesPossiblesEn (m, i, j);
		int cmp = 0;
		int ind =0;
		for(int a=0;a<t.length;a++) {
			if(t[a]) {
				cmp = cmp+1;
				ind =a;
			}
			if(cmp==1 && a==t.length-1) {
				return ind;
			}
		}
		return -1;
		
	} // toutSeul
	
	//Fonction rajouté qui calcule le nombre de case à 0 présente dans la matrice
	static int NbrCaseVide(int [][]m) {
		int nb = 0;
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[i].length;j++) {
				if(m[i][j]==0) {
					nb=nb+1;
				}
			}
		}
		return nb;
	}
	
	
	
	static void essais (String grille) {
		/*
		 * Prerequis : grille represente une grille de sudoku
		 * (les chiffres sont consideres comme ranges par lignes)
		 * 
		 * Effet :
		 * 1) affiche en clair la grille
		 * 2) affecte, tant que faire se peut, toutes les cases pour lesquelles il n'y
		 *    a qu'une seule possibilite
		 * 3) affiche en clair le resultat final
		 */
		int[][] m = aPartirDe(grille);
		System.out.println("Probleme\n\n"+enClair(m));
		int ent = 0;
		int chg = 0;
		int nb = NbrCaseVide(m);
		int tour=0;
		while (chg<=nb) {
			for(int i=0;i<m.length;i++) {
				for(int j=0;j<m[i].length;j++) {
					if(m[i][j]==0) {
						ent = toutSeul(m,i,j);
						if(ent!=(-1)) {
							m[i][j]=ent;
							chg++;
						}
					}
				}
			}
			tour++;
			if(tour>=chg) {
				break;
			}
		}
		System.out.println("Il se peut qu'on ait avance\n\n"+enClair(m));
	}
	
	public static void main(String[] args) {
		String grille1 = 
			"040 001 006 \n" +
			"007 900 800 \n" +
			"190 086 074 \n" +
			"            \n" +
			"200 690 010 \n" +
			"030 405 090 \n" +
			"060 017 003 \n" +
			"            \n" +
			"910 750 042 \n" +
			"008 002 700 \n" +
			"400 300 080   " ;
		String grille2 = 
			"030 000 006 \n" +
			"000 702 300 \n" +
			"104 038 000 \n" +
			"            \n" +
			"300 020 810 \n" +
			"918 000 265 \n" +
			"062 050 007 \n" +
			"            \n" +
			"000 140 708 \n" +
			"001 209 000 \n" +
			"800 000 020   " ;
		
		//Test
		essais(grille1) ;
		//essais(grille2) ;
		System.out.println(presentLigne(aPartirDe(grille1),1,9));
		System.out.println(presentColonne(aPartirDe(grille2),6,8));
		System.out.println(presentRegion(aPartirDe(grille1),1,0,6));
		System.out.println();
		System.out.println("Tableau boolean");
		boolean []t = lesPossiblesEn(aPartirDe(grille1),1,1);
		for(int i = 0; i<9; i++) {
			System.out.println(t[i]);
		}
		System.out.println();
		System.out.println(toutSeul(aPartirDe(grille1),8,4));
		
		//Si on souhaite passer sur un tableau 16x16 (région 4x4), on doit rajouter une ligne à notre programme
		//Ou il nous suffit de modifier les variable en s'adaptant pour le nombres de lignes, de colonnes et la taille des régions
		
		}
		
		
}
