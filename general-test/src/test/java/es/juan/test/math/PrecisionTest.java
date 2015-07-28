package es.juan.test.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PrecisionTest {

	@Parameters
	public static List<Object[]> testData(){
		return Arrays.asList(new Object[][]{
			{5.123456789, 2, 5.12},
			{5.123456789, 5, 5.12346},
			{123.123456789, 5, 123.12346},
			{-123.123456789, 5, -123.12346},
			{0.123456789, 5, 0.12346},
			{0, 5, 0},
			{123, 5, 123}
		});
	}
	
	private double decimal;
	private int scale;
	private double decimalRounded;
	
	public PrecisionTest(double decimal, int scale, double decimalRounded){
		this.decimal = decimal;
		this.scale = scale;
		this.decimalRounded = decimalRounded;
	}
	
	@Test
	public void testRoundDecimal(){
		double result = Precision.round(decimal, scale);
		assertThat(result).isEqualTo(decimalRounded);
	}
}
