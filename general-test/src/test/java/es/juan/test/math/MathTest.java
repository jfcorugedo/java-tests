package es.juan.test.math;

import org.apache.commons.math3.util.FastMath;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MathTest {

	@Test
	public void testFloorDiv() {
		int result = Math.floorDiv(10, 3);
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void testMax() {
		double ate = 1000;
		double goal = 2000;
		
		double max = FastMath.max(FastMath.max(ate, goal),0);
		
		double min = FastMath.min(FastMath.min(ate, goal),0);
		
		assertThat(max).isEqualTo(2000);
		assertThat(min).isEqualTo(0);
	}
}
