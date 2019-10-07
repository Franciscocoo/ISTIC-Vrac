package Animaux;

public class Aigle extends Oiseaux {

	public Aigle(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.toString() + "C'est un Aigle. ";
	}
}
