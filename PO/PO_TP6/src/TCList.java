
public class TCList extends TableCorrespondance {

	public class Paire{
		private String c;
		private String v;
		private Paire suivant;
		
		public String getC() {
			return c;
		}
		public void setC(String c) {
			this.c = c;
		}
		public String getV() {
			return v;
		}
		public void setV(String v) {
			this.v = v;
		}
		public Paire(String c, String v, Paire p) {
			this.c=c;
			this.v=v;
			this.suivant=p;
		}
		
	}
	
	private Paire tete;
	
	public TCList() {
		tete=null;
	}
	
	@Override
	public void associe(String cle, String valeur) {
		boolean remplace = false;
		Paire p = tete;	
		if(tete==null) {
				tete  =new Paire(cle,valeur, null);			
			}
		else {
			while(p.suivant!=null) {
				if(p.c.equals(cle)) {
					p.v=valeur;
					remplace=true;
				}
				p=p.suivant;
			}
		if(!remplace){
			if(p.c.equals(cle)) {
				p.v=valeur;
			}
			else {
				p.suivant= new Paire(cle,valeur,null);
			}
		}
		}
	}

	@Override
	public void supprime(String cle) {
		if(tete == null) {
			System.out.println("La liste est vide. ");
		}
		else if (tete.c.equals(cle)) {
			tete=tete.suivant;
		}
		else {
			Paire prep = tete;
			Paire act = tete.suivant;
			while(act!=null) {
				if(act.c.equals(cle)) {
					prep.suivant= act.suivant;
				}
				prep=prep.suivant;
				act=act.suivant;
			}	
		}
	}

	@Override
	public String get(String cle) {
		Paire p =tete;
		Paire act = new Paire("","",null);
		if(tete==null) {
			return null;
		}
		else {
			while (p!=null){
				if(p.c.equals(cle)) {
					act =p;
				}
			p=p.suivant;	
			}
		}
		return act.v;
	}
	
	public String toString() {
		String ch = "{" ;
		Paire p = tete;
		if(tete==null) {
			System.out.println("test");
			return "Liste vide. ";
		}
		else {
			while(p!=null) {
				ch =ch +"("+ p.c + ", "+ p.v + ")";
				p=p.suivant;
			}
		}
		return ch + "}";
	}

}
