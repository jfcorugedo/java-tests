package es.juan.test.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Test;

public class BigDecimalTest {

	@Test
	public void testRoundingBigDecimal(){
		BigDecimal decimal = new BigDecimal(5.123456789);
		BigDecimal roudedDecimal = decimal.round(new MathContext(5));
		assertThat(roudedDecimal.toString()).isEqualTo("5.1235");
	}
	
	@Test
	public void testRoundingBigDecimalOverflow(){
		BigDecimal decimal = new BigDecimal(25.12);
		BigDecimal roudedDecimal = decimal.round(new MathContext(5));
		assertThat(roudedDecimal.toString()).isEqualTo("25.120");
	}
}
