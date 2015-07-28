package es.juan.powermock;

public class MyController {

	public String doSomeStuff(String parameter) {
		
		getValidator().validate(parameter);
		
		// Perform other operations
		
		return "nextView";
	}
	
	public CoolValidator getValidator() {
		//Bad design, it's better to inject the validator or a factory that provides it
		return new CoolValidator();
	}
}
