import Animaux.*;;
public class Test {

	public static void main(String[] args) {
		
		Zoo Lafleche = new Zoo("LaFleche", 25);
		Lafleche.ajouterAnimal( new Elan("Killian", 1));
		Lafleche.ajouterAnimal(new Daim("Nico", 7));
		Lafleche.ajouterAnimal(new Cerf("Léo",3));
		Lafleche.ajouterAnimal(new Perroquet("Mathis", 11));
		Lafleche.ajouterAnimal(new Aigle("Benji", 5));
		Lafleche.ajouterAnimal(new Faucon("Robin", 1));
		Lafleche.ajouterAnimal(new Lion("Lacarpe", 8));
		Lafleche.ajouterAnimal(new Tigre("Simon", 6));
		Lafleche.ajouterAnimal(new Panthere("Francis", 99));
		Lafleche.ajouterAnimal(new Faucon("Robert",34));
		Lafleche.affichageZoo();
		Lafleche.supprimerAnimal(new Faucon("Robert",34));
		System.out.println("");
		Lafleche.affichageZoo();
		System.out.println("");
		Lafleche.doyen();
		Lafleche.agemoy();

	}

}
