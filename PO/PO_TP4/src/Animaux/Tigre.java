package Animaux;

public class Tigre extends Felins {

	public Tigre(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.toString() + "C'est un Tigre. ";
	}
}
