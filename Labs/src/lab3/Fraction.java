//Name: Frank Yue Ying (yying2@)
package lab3;

public class Fraction {
	int numerator;
	int denominator;
	
	public Fraction() {
		numerator = 1;
		denominator = 1;
	}
	
	public Fraction(int numerator,int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString() {
		return String.valueOf(numerator)+"/"+String.valueOf(denominator);
	}
	
	public double toDecimal() {
		return Double.valueOf(numerator)/Double.valueOf(denominator);
	}
	
	public Fraction add(Fraction f) {
		int new_d;
		int new_n;
		new_d = this.denominator*f.denominator;
		new_n = this.numerator*f.denominator + this.denominator*f.numerator;
		int GCD = findGCD(new_n,new_d);
		Fraction newf = new Fraction();
		newf.denominator = new_d/GCD;
		newf.numerator = new_n/GCD;
		return newf;
	}
	
	public int findGCD(int numerator,int denominator) {
		if (numerator == 0) {
			return 1;
		}
		else if (denominator == 0) {
			return numerator;
		}
		else {
			return findGCD(denominator,numerator%denominator);
		}
		 
	}
	public static void main(String[] args) {
		
		

	}

}
