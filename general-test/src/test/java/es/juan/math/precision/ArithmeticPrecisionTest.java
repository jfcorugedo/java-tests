package es.juan.math.precision;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ArithmeticPrecisionTest {

	@Test
	public void testPrecission() {
		
		double result = (55.0 - 10.6) / 6;
		
		assertThat(result).isEqualTo(7.3999999999999995);
		assertThat((double)Math.round(result * 1000000)/1000000).isEqualTo(7.4);
	}
	
	@Test
	public void testPrecission2() {
		
		double result = 100000000*(55.0 - 10.6) / (6*100000000);
		
		assertThat(result).isEqualTo(7.4);
	}
}
