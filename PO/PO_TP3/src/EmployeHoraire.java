
public class EmployeHoraire extends Employe {
	private double SalHoraire;
	private int NbrHeures;
	private int HSupp;
	private boolean type;
	
	public EmployeHoraire(String nom) {
		super(nom);
	}
	
	public EmployeHoraire(String nom, int NbrHeures, int HSupp, double SalHoraire, boolean type) {
		super(nom);
		this.SalHoraire =SalHoraire;
		this.NbrHeures=NbrHeures;
		this.HSupp = HSupp;
		this.type = type;
	}
	
	public void setInfosSalaire(int NbrHeures, int HSupp, double SalHoraire) {
		this.NbrHeures = NbrHeures;
		this.HSupp = HSupp;
		this.SalHoraire = SalHoraire;
		this.type = type;
	}
	
	@Override
	public double getSalaire() {
		if(type) {
			return ((NbrHeures*SalHoraire)+(HSupp*(0.5*SalHoraire)));
		}
		else return ((NbrHeures*SalHoraire)+(HSupp*(0.3*SalHoraire)));
	}
	
	public String toString() {
		return "Le salaire de" + super.toString() + "est : " + getSalaire() + "€";
	}
}
