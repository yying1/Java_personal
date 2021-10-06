//Name: Frank Yue Ying (yying2@)
package lab3;

public class MixedFraction extends Fraction {
	int naturalNumber;
	
	public MixedFraction(int naturalNumber, int numerator, int denominator) {
		this.naturalNumber = naturalNumber;
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString() {
		return String.valueOf(naturalNumber)+" "+String.valueOf(numerator)+"/"+String.valueOf(denominator);
	}
	
	public double toDecimal() {
		return (Double.valueOf(naturalNumber)*Double.valueOf(denominator)+Double.valueOf(numerator))/Double.valueOf(denominator);
	}
	
	public Fraction toFraction() {
		Fraction newfraction = new Fraction();
		newfraction.numerator = naturalNumber*this.denominator+this.numerator;
		newfraction.denominator = this.denominator;
		return newfraction;
	}
	
	public Fraction add(MixedFraction mf) {
		Fraction newf2 = new Fraction();
		newf2 = mf.toFraction();
		super.add(newf2);
		Fraction newf1 = new Fraction();
		newf1 = this.toFraction();
		return newf1.add(newf2);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
