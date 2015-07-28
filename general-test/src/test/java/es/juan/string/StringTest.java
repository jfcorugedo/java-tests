package es.juan.string;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class StringTest {

	@Test
	public void testFormatNullString() {
		TestObject test = new TestObject();
		test.setMessage(null);
		
		String message = String.format("Message: %s", test.getMessage());
		
		assertThat(message).isEqualTo("Message: null");
	}
	
	@Test
	public void testFormatNullObject() {
		TestObject test = new TestObject();
		test.setComplexObject(null);
		
		String message = String.format("Message: %s", test.getComplexObject());
		
		assertThat(message).isEqualTo("Message: null");
	}
	
	@Test
	public void testFormatNotNullObject() {
		TestObject test = new TestObject();
		test.setComplexObject(new Integer(45));
		
		String message = String.format("Message: %s", test.getComplexObject());
		
		assertThat(message).isEqualTo("Message: 45");
	}
	
	private class TestObject {
		private String message;
		
		private Object complexObject;
		
		public void setMessage(String message){
			this.message = message;
		}
		
		public String getMessage(){
			return this.message;
		}

		public Object getComplexObject() {
			return complexObject;
		}

		public void setComplexObject(Object complexObject) {
			this.complexObject = complexObject;
		}
	}
}
