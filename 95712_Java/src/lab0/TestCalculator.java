package lab0;
//package lab0a;

import static org.junit.Assert.*;
//import org.junit.Before;
import org.junit.Test;

public class TestCalculator {
	
	Calculator calc = new Calculator();
	
	@Test
	public void test1_testAdd() {
		assertEquals("Test add", 4, calc.add(2, 2));
	}
	
	@Test
	public void test2_testSubtract() {
		assertEquals("Test subtract", 0, calc.subtract(2, 2));
	}
	
	@Test
	public void test3_testMultiply() {
		assertEquals("Test multiply", 4, calc.multiply(2, 2));
	}

	@Test
	public void test4_testDivide() {
		assertEquals("Test divide", 1, calc.divide(2, 2));
	}
	
	@Test
	public void test4_testDivideByZero() {
		assertEquals("Test divide by zero", 0, calc.divide(2, 0));
	}
}
