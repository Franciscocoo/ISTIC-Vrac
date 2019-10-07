package Animaux;

public class Cervides extends Animal {

	public Cervides(String nom, int age) {
		super(nom, age);
	}
	@Override
	public String toString() {
		return super.toString() + " C'est un Cervide. ";
	}
}
