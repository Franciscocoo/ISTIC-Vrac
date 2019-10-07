import java.lang.Math;

public class Performance {

	public static void main(String[] args) {
		
		TCList List1 = new TCList();
		
		//Associe avec N=100000
		long start= System.nanoTime();
		for(int i =0; i<100000;i++) {
			int a = (int)( Math.random() * 99999 -1);
			int b = (int)( Math.random() * 99999 -1);
			String c = Integer.toString(a);
			String d = Integer.toString(b);
			List1.associe(c, d);
		}
		long temps=System.nanoTime()-start;
		System.out.println(temps);
		
		System.out.println();
		//get avec N=10000000
		long start2= System.nanoTime();
		for(int i =0; i<100000;i++) {
			int a = (int)( Math.random() * 99999 -1);
			String c = Integer.toString(a);
			List1.get(c);
		}
		long temps2=System.nanoTime()-start2;
		System.out.println(temps2);
				
		
		TCTree List2 = new TCTree();
		
		//Associe avec N=100000
		long start4 = System.nanoTime();
		for(int j= 0;j<100000;j++) {
			int a = (int)( Math.random() * 99999 -1);
			int b = (int)( Math.random() * 99999 -1);
			String c = Integer.toString(a);
			String d = Integer.toString(b);
			List2.associe(c, d);
		}
		long temps4=System.nanoTime()-start4;
		System.out.println(temps4);
		
		//Get avec N=100000
		long start5 = System.nanoTime();
		for(int j= 0;j<100000;j++) {
			int a = (int)( Math.random() * 99999 -1);
			String c = Integer.toString(a);
			List2.get(c);
		}
		long temps5=System.nanoTime()-start5;
		System.out.println(temps5);
		
		
		
		
		

	}

}
