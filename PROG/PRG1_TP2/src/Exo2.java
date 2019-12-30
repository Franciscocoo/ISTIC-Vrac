import java.util.Arrays;

public class Exo2 {
	
	//False pas present et true present
	public static boolean mysterieux(int n) {
		boolean[] bool1= new boolean[10];
		boolean cond1 = true;
		boolean cond2 = true;
		int n1= n*n;
		int n2 = n*n*n;
		while(n1>0) {
			int temp = n1%10;
			if(bool1[temp] == true) {
				return false;
			}
			bool1[temp]=true;
			n1= n1/10;
		}
		while(n2>0) {
			int tempo = n2%10;
			if(bool1[tempo] == true) {
				return false;
			}
			bool1[tempo]=true;
			n2= n2/10;
		}
		for(int i =0;i<bool1.length;i++) {
			if(bool1[i]==false) {
				cond2 = false;
			}
		}
		return (cond1 && cond2);
	}
	
	public static void main(String[] args) {
		System.out.println(mysterieux(76));
		System.out.println(mysterieux(61));
		System.out.println(mysterieux(66));
		System.out.println(mysterieux(24));
		//System.out.println(26597%10);
	}

}
