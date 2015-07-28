package es.juan.math.simulation;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.math3.random.EmpiricalDistribution;
import org.junit.Test;
public class DistributionTest {

	@Test
	public void testAskForSpecificThreshold() {
		EmpiricalDistribution distribution = new EmpiricalDistribution(2);
		
		distribution.load(new double[]{1d,2d,3d,4d,5d,6d,7d,8d,9d,10d});
		
		
		assertThat(distribution.cumulativeProbability(1d)).isEqualTo(0.0);
	}
}
