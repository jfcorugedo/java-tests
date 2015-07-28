package es.juan.math.simulation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.random.EmpiricalDistribution;
import org.apache.commons.math3.random.ValueServer;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;

public class SimulatorDistribution {

	@Test
	public void testObtainRandomUniformNumbers() throws ZeroException,
			NullArgumentException, IOException {
		ValueServer valueServer = new ValueServer();
		valueServer.setMode(ValueServer.UNIFORM_MODE);
		valueServer.setMu(5d);

		double[] values = new double[1000000];
		valueServer.fill(values);

		EmpiricalDistribution distribution = new EmpiricalDistribution();
		distribution.load(values);

		assertThat(Math.round(distribution.getNumericalMean())).isEqualTo(5);
		assertThat(distribution.getNumericalMean()).isLessThan(5.01)
				.isGreaterThan(4.99);
	}

	@Test
	public void testObtainRandomDigestNumbers()
			throws MathIllegalStateException, MathIllegalArgumentException,
			IOException {
		double[] values = new double[10];

		for (int i = 0; i < values.length; i++) {
			values[i] = i + 0.45;
		}

		ValueServer valueServer = new ValueServer();
		valueServer.setMode(ValueServer.GAUSSIAN_MODE);
		valueServer.setMu(0d);
		valueServer.setSigma(5);

		// valueServer.fill(values);

		EmpiricalDistribution distribution = new EmpiricalDistribution();
		distribution.load(values);

		System.out.println(distribution.getSampleStats().getMean());
		System.out
				.println(distribution.getSampleStats().getStandardDeviation());
		try {
			System.out.println(distribution.getNextValue());
			System.out.println(distribution.getNextValue());
		} catch (NotStrictlyPositiveException e) {
			System.out.println(e);
			valueServer.setMu(distribution.getSampleStats().getMean());
			valueServer.setSigma(distribution.getSampleStats()
					.getStandardDeviation());
			valueServer.setMode(ValueServer.GAUSSIAN_MODE);

			System.out.println(valueServer.getNext());
		}
	}

	@Test
	public void testCumulativeDistribution() {
		double[] values = new double[] { 1, 2, 3, 4, 5 };
		EmpiricalDistribution distribution = new EmpiricalDistribution(
				values.length);
		distribution.load(values);
		double percentile = distribution.cumulativeProbability(4);
		assertThat(percentile).isEqualTo(0.8);
	}

	@Test
	public void testCalculateFrequency() {
		Frequency freq = createFrequency();
		
		assertThat(freq.getPct(13.0)).isEqualTo(1.0/20);
		assertThat(freq.getPct(34.0)).isEqualTo(0);
		assertThat(freq.getPct(6.0)).isEqualTo(2.0/20);
		assertThat(freq.getSumFreq()).isEqualTo(20);
		
		Iterator<Comparable<?>> values = freq.valuesIterator();
		Double value = null;
		while(values.hasNext()) {
			value = (Double)values.next();
			System.out.println(String.format("%f with probability %f", value, freq.getPct(value)));
		}
	}
	
	@Test
	public void testEnumeratedDistribution() {
		List<Pair<Double, Double>> probabilityMassFunction = createPmf();
		
		EnumeratedDistribution<Double> distribution = new EnumeratedDistribution<Double>(probabilityMassFunction);
		
		Double[] values = distribution.sample(100, new Double[0]);
		
		SummaryStatistics sampleStats = new SummaryStatistics();
        for (int i = 0; i < values.length; i++) {
            sampleStats.addValue(values[i]);
        }
        
        System.out.println(String.format("Population generated with mean %f and deviation %f", sampleStats.getMean(), sampleStats.getStandardDeviation()));
        
        Arrays.stream(values).forEach(System.out::println);
	}

	private List<Pair<Double, Double>> createPmf() {
		List<Pair<Double, Double>> probabilityMassFunction = new ArrayList<Pair<Double,Double>>();
		Frequency freq = createFrequency();
		
		Iterator<Comparable<?>> values = freq.valuesIterator();
		Double value = null;
		while(values.hasNext()) {
			value = (Double)values.next();
			probabilityMassFunction.add(new Pair<Double, Double>(value, freq.getPct(value)));
		}
		return probabilityMassFunction;
	}

	private Frequency createFrequency() {
		double[] population = new double[20];
				population[0] = 10;
				population[1] = 5;
				population[2] = 20;
				population[3] = 12;
				population[4] = 4;
				population[5] = 11;
				population[6] = 23;
				population[7] = 0;
				population[8] = 12;
				population[9] = 6;
				population[10] = 6;
				population[11] = 11;
				population[12] = 14;
				population[13] = 13;
				population[14] = 16;
				population[15] = 18;
				population[16] = 19;
				population[17] = 12;
				population[18] = 0;
				population[19] = 4;

				SummaryStatistics sampleStats = new SummaryStatistics();
		        for (int i = 0; i < population.length; i++) {
		            sampleStats.addValue(population[i]);
		        }
		        
		        System.out.println(String.format("Initial population with mean %f and deviation %f", sampleStats.getMean(), sampleStats.getStandardDeviation()));
		        
		Frequency freq = new Frequency();
		for (int i = 0; i < population.length ; i++) {
			final double value = population[i];
			if (!Double.isNaN(value)) {
				freq.addValue(Double.valueOf(value));
			}
		}
		return freq;
	}
}
