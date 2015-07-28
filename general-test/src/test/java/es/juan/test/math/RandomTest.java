package es.juan.test.math;

import java.util.Random;

import org.junit.Test;

public class RandomTest {

	@Test
	public void testNextInt() {
		Random random = new Random();
		int one = 0;
		int zero = 0;
		for(int i = 0 ; i < 500 ; i++) {
			if(random.nextInt(2) == 0) {
				zero++;
			} else {
				one++;
			}
		}
		
		System.out.println(String.format("%d ones and %d zeros at the end of the test", one, zero));
	}
}
