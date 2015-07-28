package es.juan.scanner;

import java.util.Scanner;

import org.junit.Test;

public class ScannerTest {

	//@Test
	public void testReadWithespaces() {
		Scanner input=new Scanner(System.in);
		
		while(input.hasNext()) {
			System.out.println("Result: " + input.nextInt());
		}
	}
}
