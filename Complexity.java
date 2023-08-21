package Homeworks;

public class Complexity {

	
	public static void method1(int n) {
		// O(n^2)
		int counter = 0;
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++){
				System.out.println("Operation "+ counter);
				counter++;
			}
		}
	}
	
	
	
	public static void method2(int n) {
		// O(n^3)
		int counter =0;
		for (int i=1; i<n; i++) {
			for (int j=1; j<n*n; j++) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}	
	}
	
	
	public static void method3(int n) {
		// O(logn)
		int counter= 0;
		for (int i=1; i<0; i*= 2) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	public static void method4(int n) {
		// O(nlogn)
		int counter= 0;
		for (int i=1; i<n; i++) {
			for (int j=1; j<n; j*=2) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	
	
	public static void method5(int n) {
		//O(loglogn)
		int counter=0;
		for (int i=n; i >2; i = (int)(Math.pow(i, .5))) {
			System.out.println("Operation "+ counter);
			counter++;
			}
		}
	
	public static int method6(int n) {
		if (n<=0) {
			return n;
		}else {
			return method6(n-1) + method6(n-2);
		}
	}
}
