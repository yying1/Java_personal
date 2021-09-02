package lab0a;

public class Numbers {
	public static void main(String[] args) {
		Numbers n =  new Numbers();
		System.out.println(n.sayWhat(5));
		System.out.println(n.nthPrime(2));
	}
	//fix this method
	public String sayWhat(int number) {
		String result;
		if (number%2 == 0) {result = "even";}
		else {result = "odd";}
		return result;
	}
	//fix this method
	public boolean isPrime(int number) {
		for (int i  =  1;   i  < number; i++) {   
			//can you improve this loop??
			if   (number %  i  ==   0 && i != 1) 
				return false;
		}
		return true;
	}
	
	public int nthPrime(int number) {
		int prime = 1;
		boolean done = true;
		for (int i  =  0;   i  < number; i++) { 
			
			
			do {
				prime +=1;
				for ( int a = 2; a < prime; a++) {
					if (prime % a ==0) {
						done = false;
						//System.out.println(a);
						break;
					}
					else {done = true;}
				}
//				System.out.println(done);
//				System.out.println(prime);
			}
			while (done == false);
			
		}
		return prime;
	}
}
