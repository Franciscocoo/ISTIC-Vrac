package Animaux;

public class Faucon extends Oiseaux {

	public Faucon(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.toString() + "C'est un Faucon. ";
	}
}
