//2021-11-08
//Exception & I/O

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Class20_20211108 {
	
	public static void main(String[] args) throws Exception {
	//Exception
		//Checked Vs. Unchecked
		//anything under runtime exception is unchecked, logic error
		//External error outside of the program is unchecked
		//Two options for handling checked exception: Try-Catch, Throw 
		Class20_20211108 c20 = new Class20_20211108 ();
		//c20.method1(); //must implement throws Exception in main to work
		
		try {
			c20.method1(); //with try catch, need to have throws in main
		} catch (Exception e) {
			System.out.println("Exception caught.");
		}
		
		//Custom Exception: business logic, rules
		int x = 0;
		try {
			if (x == 0) {throw new myexception();}
		} 
		//catch need to be ordered from most specific to most generic, first caught will handle this
		//if catch does not match the checked exception type, wont compile
		catch (Exception e) {
			System.out.println("Throw new Exception");
		} //catch (myexception me) {System.out.println("Throw myException");} //unreachable code, exception will handle it
		finally {
			System.out.println("Finally execution.");//will run no matter what
		}
	//I&O
		//Auto-closable with try with resources
		//Byte Stream (0,1) Vs. Character Stream (character encoding)
		//Stream --> byte raw data, reader/writer --> character
		String s = "\u0084\u4E96";
		byte[] code = s.getBytes();
		try {
			InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(code), StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//File Read & Write
		// inputstream outputsteam --> raw data
		// inputstreamreader --> set encoding, better option for cross-platform and language programs
		// filereader --> use default encoding, by character 
		// reading numeric data: must use datainputsteam: interpret fixed byte data
		// Buffered reader and writer: contains reader contains character stream
		// Bufferedreader cannot parse, Scanner can parse. Both can read. Scanner is slower
		
		// nio Path
		// BufferedWriter, need to be in try catch, write to doc
		// Writing to CSV, TSV file (check slides)
	}
	
	void method1() throws Exception { //ducking exception to the calling class, must have or won't complie
		System.out.println("Method 1.");
		try {
			throw new Exception ();
		} catch (Exception e1) {
			System.out.println("Catch exception in method.");
		}
		throw new RuntimeException();//throw unchecked exception, no need to throw, can compile
		//throw new Exception();//creating throw event, checked exception
	}
	
}

@SuppressWarnings("serial")
class myexception extends Exception{//extends Exception becomes checked, extends runtimeexception becomes unchecked
	myexception(){
		System.out.println("Throw myexception.");
	}
}
