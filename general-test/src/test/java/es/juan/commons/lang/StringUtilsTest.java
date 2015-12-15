package es.juan.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class StringUtilsTest {

	@Test
	public void testSplitByWholeSeparator() {
		String[] result = StringUtils.splitByWholeSeparator("ab de fg", null);
		
		assertThat(result).hasSize(3);
		assertThat(result).containsExactly("ab", "de", "fg");
	}
	
	@Test
	public void testSplitByWholeSeparatorEmptyString() {
		String[] result = StringUtils.splitByWholeSeparator("ab de fg", "");
		
		assertThat(result).hasSize(3);
		assertThat(result).containsExactly("ab", "de", "fg");
	}
	
	@Test
	public void generateNumbers(){
		for(int i = 0 ; i < 3000 ; i++) {
			System.out.print(new java.util.Random().nextDouble() * 10000 + ",");
		}
	}
}
