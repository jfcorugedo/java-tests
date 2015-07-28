package es.juan.apache.math.distribution;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class EmpiricalDistributionTest {

	@Test
	public void testLoadDistribution() {
		double[] data = new double[]{10.6,18.0,18.0,19.0,23.0,24.0,25.0,26.0,31.0,32.0,37.0,38.0,38.0,40.0,45.0,54.0,55.0};
		
		EmpiricalDistribution myDistribution = new EmpiricalDistribution(6);
		org.apache.commons.math3.random.EmpiricalDistribution apacheDistribution = new org.apache.commons.math3.random.EmpiricalDistribution(6);
		
		myDistribution.load(data);
		apacheDistribution.load(data);
		
		assertThat(myDistribution.getBinStats().get(0).getN()).isEqualTo(3);
		assertThat(apacheDistribution.getBinStats().get(0).getN()).isEqualTo(1);
	}
}
