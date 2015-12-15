package es.juan.number;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class DoubleTest {

	
	@Test(expected=NumberFormatException.class)
	public void valueOfHexadecimal() {
		
		String hexValue = "0x2222";
		
		double value = Double.valueOf(hexValue);
		
	}
	
	@Test
	public void valueOfRealNumberUsingDotAsDecimalSeparator() {
		
		String octalValue = "34.56";
		
		double value = Double.valueOf(octalValue);
		
		assertThat(value).isEqualTo(34.56);
		assertThat(NumberUtils.isNumber("34,4")).isFalse();
		assertThat(NumberUtils.isNumber("34.4")).isTrue();
	}
	
	@Test(expected=NumberFormatException.class)
	public void valueOfRealNumberUsingCommaAsDecimalSeparator() {
		
		String octalValue = "34,56";
		
		double value = Double.valueOf(octalValue);
		
		assertThat(value).isEqualTo(34.56);
	}
}
