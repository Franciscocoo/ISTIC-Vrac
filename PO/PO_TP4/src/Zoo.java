import Animaux.Animal;;

public class Zoo {
	private String nom;	
	private Animal[] t ;
	private int nbrMax;
	private int remplissage;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Animal[] getT() {
		return t;
	}
	public void setT(Animal[] t) {
		this.t = t;
	}
	
	public Zoo (String nom, int nbrMax) {
		this.nom = nom;
		this.t = new Animal[nbrMax];
		this.remplissage = 0;
	}
	
	public void ajouterAnimal( Animal a) {
		this.t[remplissage]= a;
		remplissage++;
		
	}
	
	public void supprimerAnimal(Animal a) {
		int ind=-1;
		for(int i =0; i<this.t.length;i++) {
			if(t[i].equals(a)) {
				ind = i;
				break;
			}
		}
		if(ind==-1) {
			System.out.println("\n"+"L'animal n'est pas dans le zoo");
			return;
		}
		t[ind]=null;
		remplissage--;
	}
	
	public void affichageZoo() {
		for(int i=0;i<remplissage;i++) {
			System.out.println(t[i]);
			if (i==remplissage-1) {
				break;
			}
		}
	}
	
	public void doyen() {
		Animal max = t[0];
		for(int i =1; i<t.length;i++) {
			if(t[i].getAge()>max.getAge()) {
				max=t[i];
			}
		}
		System.out.println("Le doyen est " + max.getNom());
	}
	
	public double agemoy() {
		double moy =0.0;
		for(int i=0;i<remplissage-1;i++) {
			moy=moy+t[i].getAge();
		}
		return(moy/remplissage-1);
	}
	
}
