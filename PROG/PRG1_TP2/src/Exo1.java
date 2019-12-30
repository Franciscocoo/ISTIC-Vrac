import java.util.Arrays;
import java.util.Scanner;

public class Exo1 {

	public static int[] create(int n) {
		Scanner sc = new Scanner(System.in);
		int[] tab = new int[n];
		for(int i=0;i<n;i++) {
			System.out.println(i+1 + " eme valeur du tableau :");
			int a = sc.nextInt();
			tab[i] = a;
		}
		return tab;
	}
	
	public static void tri(int tab[]) {
		for(int i=0;i<tab.length-2;i++) {
			int rangmin=i;
			for (int j= i+1;j<tab.length-1;j++) {
				if(tab[j]<tab[rangmin]) {
					rangmin=j;
				}
			}
			int aux = tab[i];
			tab[i] = tab[rangmin];
			tab[rangmin] = aux;
		}
	}
	
	public static boolean dichotomique(int tab[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Valeur a rechercher : ");
		int a = sc.nextInt();
		int deb = 0, fin = tab.length-1, milieu = (deb+fin)/2;
		while(deb <= fin && a!= tab[milieu]) {
			if(a <tab[milieu]) {
				fin = milieu -1;
			} else if (a>tab[milieu]) {
				deb = milieu + 1;
			}
			milieu = (deb+fin) / 2;
		}
		return deb <= fin;
	}
	
	public static void main(String[] args) {
		int[] t1 = create(10);
		System.out.println(Arrays.toString(t1));
		tri(t1);
		System.out.println(Arrays.toString(t1));
		System.out.println(dichotomique(t1));
	}
}
