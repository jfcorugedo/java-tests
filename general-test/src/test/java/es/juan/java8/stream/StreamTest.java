package es.juan.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class StreamTest {

	@Test
	public void testAddNumberToList() {
		List<Double> testList = new ArrayList<Double>();
		testList.add(1d);
		testList.add(2d);
		testList.add(3d);
		testList.add(4d);
		testList.add(5d);
		
		testList = testList.stream().map(v -> v + 5).collect(Collectors.toList());
		
		assertThat(testList).containsExactly(6.0,7.0,8.0,9.0,10.0);
	}
	
	@Test
	public void testSubstractNumberToList() {
		List<Double> testList = new ArrayList<Double>();
		testList.add(1d);
		testList.add(2d);
		testList.add(3d);
		testList.add(4d);
		testList.add(5d);
		
		testList = testList.stream().map(v -> v - 5).collect(Collectors.toList());
		
		assertThat(testList).containsExactly(-4.0,-3.0,-2.0,-1.0,0.0);
	}
	
	@Test
	public void cloneList() {
		List<Double> firstList = new ArrayList<Double>();
		firstList.add(1d);
		firstList.add(2d);
		firstList.add(3d);
		firstList.add(4d);
		firstList.add(5d);
		
		
		List<Double> secondList = firstList.stream().collect(Collectors.toList());
		
		firstList.add(-25.0);
		secondList.remove(0);
		
		assertThat(firstList).hasSize(6);
		assertThat(firstList).containsExactly(1.0,2.0,3.0,4.0,5.0,-25.0);
		assertThat(secondList).hasSize(4);
		assertThat(secondList).containsExactly(2.0,3.0,4.0,5.0);
	}
	
	@Test
	public void testConcatArrays() {
		double[] array1 = new double[]{1d,2d,3d,4d,5d};
		double[] array2 = new double[]{6d,7d,8d,9d,10d};
		
		double[] fullArray = new double[array1.length + array2.length];
		System.arraycopy(array1, 0, fullArray, 0, array1.length);
		System.arraycopy(array2, 0, fullArray, array1.length, array2.length);
		
		assertThat(fullArray).hasSize(10);
		assertThat(fullArray).containsExactly(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d);
	}
}
