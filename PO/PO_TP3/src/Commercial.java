
public class Commercial extends Employe {
	private int SalaireF;
	private double chiffreAff;
	
	public Commercial(String nom) {
		super(nom);
	}
	
	public Commercial(String nom, int SalaireF, double chiffreAff) {
		super(nom);
		this.SalaireF= SalaireF;
		this.chiffreAff = chiffreAff;
	}
	
	public void setInfosSalaires(int Salairef, double chiffreAff) {
		this.SalaireF=SalaireF;
		this.chiffreAff=chiffreAff;
	}
	
	@Override
	public double getSalaire() {
		return (chiffreAff*0.1)+SalaireF;
	}
	
	public String toString() {
		return "Le salaire de" + super.toString() + "est :" + getSalaire() + "€";
	}
}
