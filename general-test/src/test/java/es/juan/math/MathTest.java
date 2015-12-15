package es.juan.math;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class MathTest {

	
	@Test
	public void arctan() {
		
		double result = Math.atan(0.35);
		
		assertThat(result*180/Math.PI).isBetween(19.29, 19.2901);
	}
}
