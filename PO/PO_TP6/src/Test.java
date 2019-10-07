
public class Test {

	public static void main(String[] args) {
		/*TCList List1 = new TCList();
		System.out.println(List1.get("40")); //1
		List1.associe("a","AVION");			 //2
		List1.associe("b","Batterie");
		List1.associe("c","Xylophone");
		List1.associe("d","Papyrus");
		System.out.println(List1.toString());
		List1.associe("d", "Toi");			  //3
		List1.associe("a", "Moi");
		System.out.println(List1.toString());
		List1.associe("b", "Il");				//4
		List1.associe("c", "Elle");
		System.out.println(List1.toString());
		TCList List2 = new TCList();			//5
		List2.supprime("a");
		List1.supprime("a");
		System.out.println(List1.toString());
		List1.supprime("c");
		System.out.println(List1.toString());
		List1.associe("f", "Moi");
		List1.associe("g", "Vous");
		List1.supprime("g");
		System.out.println(List1.toString());*/
		
		
		TCTree Tree1 = new TCTree();
		Tree1.associe("m", "1");
		Tree1.associe("f", "2");
		Tree1.associe("s", "3");
		Tree1.associe("d", "4");
		Tree1.associe("i", "5");
		Tree1.associe("e", "6");
		Tree1.associe("q", "7");
		Tree1.associe("p", "8");
		Tree1.associe("r", "9");
		Tree1.associe("c", "19");
		System.out.println(Tree1.toString());
		System.out.println();
		Tree1.supprime("q");
		System.out.println(Tree1.toString());
		
	}

}
