
public class TCTree extends TableCorrespondance {

	private class Noeud {
		private String cle;
		private String valeur;
		private Noeud FilsG;
		private Noeud FilsD;
			
		public Noeud(String c, String v) {
			this.cle=c;
			this.valeur=v;
			this.FilsG=null;
			this.FilsD=null;
		}
		
		public Noeud(String c, String v, Noeud G, Noeud D) {
			this.cle=c;
			this.valeur=v;
			this.FilsG=G;
			this.FilsD=D;
		}
	}
	private Noeud racine;
	
	private void associeRec(String c, String v, Noeud courant){
		if (courant.cle == c){
			courant.valeur = v;
			return;
		}
		if (courant.cle.compareTo(c) < 0){
		    if (courant.FilsG == null){
		    	courant.FilsG = new Noeud(c,v,null,null);
		    }
		    else{
		    	associeRec(c,v,courant.FilsG);
		    }
		}
		else{
		    if (courant.FilsD == null){
			   	courant.FilsD = new Noeud(c,v,null,null);
			    }
		    else {
			    associeRec(c,v,courant.FilsD);
			     }
		}
	}
	
	@Override
	public void associe(String c, String v){
		if(racine == null){
			racine = new Noeud(c,v,null,null);
			return;
		}
		associeRec(c,v,racine);
	}

	
	
	private Noeud getPere(Noeud courant) {
        Noeud pere = new Noeud("","",null,null);
        Noeud b = racine;
        while(b.FilsG != null && b.FilsD!=null && b.cle!=courant.cle) {
            if(b.cle.compareTo(courant.cle)<0) {
                pere = b;
                b=b.FilsG;
            }
            else {
                pere = b;
                b=b.FilsD;
            }
        }
        return pere;
    }

	@Override
	public void supprime(String c){
		supprimeREC(racine,c);
	}
	
	private void supprimeREC(Noeud courant,String c){
		Noeud supp = getRec(c,racine);
		Noeud pere = getPere(supp);
		if (supp.FilsG == null && supp.FilsD == null) { // premier cas, c'est une feuille
			if(pere.FilsG == supp){
				pere.FilsG = null;
			}
			else {
				pere.FilsD = null;
			}
		}	
		//Deuxieme cas, on a un fils et une feuille
		else if(supp.FilsG==null && supp.FilsD!=null) { // Le seul fils est celui de gauche
			if(pere.FilsD == supp){
				pere.FilsD = supp.FilsD;
	        }
	        else{
	        	pere.FilsG = supp.FilsD;
	        }
		}
		else if(supp.FilsG!= null && supp.FilsD==null) { // Le seul fils est celui de droite
			if(pere.FilsD == supp) {
				pere.FilsD = supp.FilsG;
			}
			else {
				pere.FilsG = supp.FilsG;
			}
		}
		// Troisieme cas : le noeud a deux fils
		if(supp.FilsD != null && supp.FilsG != null) {
			racine = supprimerRacine(racine,c);
		}
	}

	//Fonction pour remonter l'arbre avec 2 noeuds
	private Noeud supprimerRacine(Noeud courant,String cle){
        if (courant == null){
            return courant; 
        }
        if (cle.compareTo(courant.cle) > 0) {
        	courant.FilsG = supprimerRacine(courant.FilsG, cle); 
        }
        else if (cle.compareTo(courant.cle) <  0) {
        	courant.FilsD = supprimerRacine(courant.FilsD, cle); 
        }
        else { 
            if ((courant.FilsD == null && courant.FilsG == null) || (courant.FilsD != null && courant.FilsG ==null) || (courant.FilsD != null && courant.FilsG == null)){
                if(courant.FilsD==null) {
                	return courant.FilsG;
                }
                else {
                	return courant.FilsD;
                }
            }
            String derniereCle = courant.FilsD.cle; 
            while (courant.FilsD.FilsG != null) { 
                 derniereCle = courant.FilsD.FilsG.cle; 
                 courant.FilsD = courant.FilsD.FilsG; 
             } 
            courant.cle = derniereCle;
            courant.FilsD = supprimerRacine(courant.FilsD, courant.cle); 
        } 
        return courant; 
    }
	
	
	private Noeud getRec(String c, Noeud courant) {
		if(courant==null) {
			return null;
		}
		else if(courant.cle.equals(c)) {
			return courant;
		}
		else {
			if(courant.cle.compareTo(c)<0) {
				return courant =getRec(c,courant.FilsG);
			}
			else {
				return courant=getRec(c, courant.FilsD);
			}
		}
	}
	
	@Override
	public String get(String c) {
		Noeud res = getRec(c,racine);
		if(res==null) {
			return null;
		}
		else {
			return res.valeur;
		}
	}
	//Visualisation en ordre préfixe
	private String toStringRec(Noeud courant, String prefixe) {
		String resultat = "";
		if(courant!=null) {
			resultat +=
					prefixe + "("+ courant.cle + ", " + courant.valeur + ")" + "\n"
					+ toStringRec(courant.FilsG, prefixe + "  ") 
					+ toStringRec(courant.FilsD, prefixe + "  ") ;			
			return resultat;
		}
		return "";
		
	}
	
	public String toString() {
		if(racine==null) {
			return "arbre vide";
		}
		else return toStringRec(racine,"");
	}
}
