package Animaux;

public class Felins extends Animal {

	public Felins(String nom, int age) {
		super(nom, age);
	}

	@Override
	public String toString() {
		return super.toString() +" C'est un Felin. ";
	}
}
