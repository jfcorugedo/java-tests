package es.juan.apache.math.inference;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.random.ValueServer;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.vectorsf.kmd.common.collection.CollectionUtils.*;

public class KolmogorovSmirnovTestTest {

	public static final double CRITICAL_VALUE_0_05 = 1.36;
	
	@Test
	public void compareSimilarSamples() throws Exception{
		
		ValueServer generator = new ValueServer();
		generator.setMode(ValueServer.UNIFORM_MODE);
		generator.setMu(500.0);
		generator.setSigma(0.0);
		
		KolmogorovSmirnovTest kolmogorovSmirnovTest = new KolmogorovSmirnovTest();
		
		double pValue = kolmogorovSmirnovTest.kolmogorovSmirnovTest(generator.fill(1000000), generator.fill(1000000));
		
		assertThat(pValue).isGreaterThan(0.05);
	}
	
	@Test
	public void compareSimilarSamplesPValue() throws Exception{
		
		EnumeratedDistribution<Double> dist = new EnumeratedDistribution<>(
															newList(
																	new Pair<>(1.0, 1.0),
																	new Pair<>(2.0, 1.0),
																	new Pair<>(3.0, 1.0),
																	new Pair<>(4.0, 1.0),
																	new Pair<>(5.0, 1.0),
																	new Pair<>(6.0, 1.0),
																	new Pair<>(7.0, 1.0),
																	new Pair<>(8.0, 1.0),
																	new Pair<>(9.0, 1.0)
																)
															);
		
		KolmogorovSmirnovTest kolmogorovSmirnovTest = new KolmogorovSmirnovTest();
		
		Double[] sample = dist.sample(10000, new Double[10000]);
		double pValue = kolmogorovSmirnovTest.kolmogorovSmirnovTest(
				convertToPrimitives(Arrays.asList(sample)), 
				convertToPrimitives(Arrays.asList(sample)));
		
		System.out.println("p-value: " + pValue);
		assertThat(pValue).isLessThanOrEqualTo(0.05);
	}
	
	@Test
	public void testSameSamples() {
		
		KolmogorovSmirnovTest kolmogorovSmirnovTest = new KolmogorovSmirnovTest();
		
		double[] sample1 = IntStream.range(0, 30).asDoubleStream().toArray();
		
		double[] sample2 = IntStream.range(0, 30).asDoubleStream().toArray();
		
		double pValue = kolmogorovSmirnovTest.kolmogorovSmirnovTest(sample1, sample2);
		
		System.out.println(pValue);
		assertThat(pValue).isGreaterThan(0.05);
	}
}


