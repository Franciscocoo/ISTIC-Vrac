package fr.istic.prg1.tp3;

/**
 * 
 * @author MickaÃ«l Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2015-11-10
 * 
 *        Classe reprÃ©sentant des doublets non modifiables
 */

public class Pair implements Comparable<Pair> {
	
	/**
	 * Premier élément entier d'une paire
	 */
	private final int elem1;
	
	/**
	 * Deuxieme élément entier d'une paire
	 */
	private final int elem2;
	
	public Pair(int a, int b) {
		elem1=a;
		elem2=b;
	}
	
	@Override
	public int compareTo(Pair d) {
		if((this.elem1<d.elem1) || (this.elem1==d.elem1 && this.elem2<d.elem2)) {
			return -1;
		} else if((this.elem1==d.elem1) && (this.elem2==d.elem2)) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public Pair clone() {
	    return new Pair(this.elem1,this.elem2);
	}

	@Override
	public String toString() {
	   return "[ " + this.elem1 + ", " + this.elem2 + " ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		if (((Pair)obj).elem1==this.elem1 && ((Pair)obj).elem2==this.elem2 ) {
			return true;
		}
		return false;
	}
}