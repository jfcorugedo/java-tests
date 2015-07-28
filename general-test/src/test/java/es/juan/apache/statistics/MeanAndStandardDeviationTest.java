package es.juan.apache.statistics;

import java.util.Arrays;

import org.apache.commons.math3.util.FastMath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class MeanAndStandardDeviationTest {

	@Parameters
	public static Object[][] data(){
		return new Object[][]{
				{new double[]{2, 4, 4, 4, 5, 5, 7, 9},   5, 2},
				{new double[]{2, 4, 5, 5, 5, 5, 8, 9},   5, 2},
				{new double[]{3, 4, 4, 5, 5, 7, 8, 9},   5, 2},
				{new double[]{5, 5.375, 5.625},   5.333, 0.25}
		};
	}
	
	private double[] values; 
	private double expectedMean;
	private double expectedSd;
	
	public MeanAndStandardDeviationTest(double[] values, double expectedMean, double expectedSd) {
		this.values = values;
		this.expectedMean = expectedMean;
		this.expectedSd = expectedSd;
	}
	
	@Test
	public void testCalculateStatistics() {
		double total = Arrays.stream(values).sum();
		double mean = total/(double)values.length;
		double sd = calculateSd(values, mean);
		
		System.out.println(mean + "/" + sd);
		assertThat(mean).isBetween(expectedMean - expectedSd, expectedMean + expectedSd);
		assertThat(sd).isBetween(expectedSd - 1.0, expectedSd + 1.0);
	}

	/**
	 * Calculate standard deviation:
	 * 
	 * sd: sqrt(sum( (Vi - mean)^2 ) / totalSamples)
	 * 
	 * @param values
	 * @return
	 */
	private double calculateSd(double[] values, double mean) {
		
		//Calculates sum of deviation of each data point
		double sumDeviations = 0;
		for(double value : values) {
			//Calculates the deviation of each data point
			sumDeviations += FastMath.pow((value - mean), 2); 
		}
		
		return FastMath.sqrt(sumDeviations/values.length);
	}
}
