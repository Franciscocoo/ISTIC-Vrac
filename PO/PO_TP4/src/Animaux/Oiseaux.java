package Animaux;

public class Oiseaux extends Animal {

	public Oiseaux(String nom, int age) {
		super(nom, age);
	}
	@Override
	public String toString() {
		return super.toString() + " C'est un Oiseau. ";
	}
}
