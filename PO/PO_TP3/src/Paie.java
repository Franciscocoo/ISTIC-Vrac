
public class Paie {

	public static void main(String[] args) {
		// Type false est un employé qui gagne 30% en heure supp
		// Type true est un employé qui gagne 50 % en heure supp
		Employe []t= new Employe[3];
		t[0]=new EmployeHoraire("Pierre",140,10,9.88,true);
		t[1]=new EmployeHoraire("Martin");
		t[2]=new Commercial("Louise",1539,35000);
		//Employe(t[1])
		
		
		for(int i=0;i<t.length;i++) {
			System.out.println(t[i].toString());
		}
		
	}

}
