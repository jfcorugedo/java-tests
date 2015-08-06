package es.juan.test.java8.lambdas;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MethodReferenceTest {

	@Test
	public void testStaticMethod() {
		
		Function<Integer, Integer> lambdaSumFunction = (a) -> twice(a);
		Function<Integer, Integer> methodReferenceSumFunction = MethodReferenceTest::twice;
		
		int usingLambda = lambdaSumFunction.apply(5);
		int usingMethodReference = methodReferenceSumFunction.apply(5);
		
		assertThat(usingLambda).isEqualTo(10);
		assertThat(usingLambda).isEqualTo(usingMethodReference);
		
	}
	
	private static int twice(int a){
		return a*2;
	}
	
	@Test
	public void testInstanceMethodOfArbitaryType() {
		
		MethodReferenceTest object = new MethodReferenceTest();
		BiFunction<MethodReferenceTest, Double, Double> lambdaFunction = (a, b) -> a.decimate(b);
		BiFunction<MethodReferenceTest, Double, Double> methodReferenceFunction = MethodReferenceTest::decimate;
		
		double lambdaResult = lambdaFunction.apply(object, 20d);
		double methodReferenceResult = methodReferenceFunction.apply(object, 20d);
		
		assertThat(lambdaResult).isEqualTo(2d);
		assertThat(lambdaResult).isEqualTo(methodReferenceResult);
	}
	
	public double decimate(double a) {
		return a/10;
	}
	
	@Test
	public void testInstanceMethodOfExistingObject() {
		
		Function<Double, Double> lambdaFunction = (b) -> decimate(b);
		Function<Double, Double> methodReferenceFunction = this::decimate;
		
		double lambdaResult = lambdaFunction.apply(20d);
		double methodReferenceResult = methodReferenceFunction.apply(20d);
		
		assertThat(lambdaResult).isEqualTo(2d);
		assertThat(lambdaResult).isEqualTo(methodReferenceResult);
	}
}
