package Animaux;
public class Animal {
	private String nom;
	private int age;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Animal(String nom, int age) {
		this.nom =nom;
		this.age=age;
	}
	
	public String toString() {
		return nom + " a " + age + " ans.";
	}
	
	public Boolean equals(Animal a) {
		if(a.getNom().equals(this.nom) && a.getAge()==this.age) {
			return true;
		}
		else return false;
	}
	
	
	
	
	
}
