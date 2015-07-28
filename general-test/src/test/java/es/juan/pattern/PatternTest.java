package es.juan.pattern;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class PatternTest {

	@Parameters
	public static Object[][] data() {
		return new Object[][]{
				{"(?iu)(id)", "Id", true},
				{"(?iu)(id)", "id", true},
				{"(?iu)(id)", "iD", true},
				{"(?iu)(id)", "ID", true},
				{"(?iu)(id)", "ids", false},
				{"(?iu)(íd)", "Íd", true},
				{"(?iu)(íd)", "íd", true},
				{"(?iu)(íd)", "íD", true},
				{"(?iu)(íd)", "ÍD", true},
				{"(?iu)(íd)", "íds", false}
		};
	}
	
	private String input;
	private boolean matchExpression;
	private String patternExpression;
	
	public PatternTest(String patternExpression, String input, boolean matchExpression) {
		this.patternExpression = patternExpression;
		this.input = input;
		this.matchExpression = matchExpression;
	}

	@Test
	public void testMatchUsingCaseInsensitive() {
		
		boolean matches = Pattern.matches(patternExpression, input);
		
		assertThat(matches).isEqualTo(matchExpression);
	}
}
