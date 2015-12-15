package es.juan.apache.math.inference;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ChiSquareTestTest {

	@Test
	public void calculateChiSquare() {
		
		ChiSquareTest chiTest = new ChiSquareTest();
		long[] observed1 = new long[]{10, 20, 13, 40};
		long[] observed2 = new long[]{100, 200, 300, 400};
		
		
		boolean result = chiTest.chiSquareTestDataSetsComparison(observed1, observed2, 0.05);
		
		assertThat(result).isFalse();
	}
}
