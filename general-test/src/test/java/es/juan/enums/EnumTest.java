package es.juan.enums;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class EnumTest {

	@Test
	public void getDescription() {
		
		String desc = SpecialEnum.value1.getDescription();
		
		assertThat(desc).isEqualTo("Super amazing description");
	}
	
	@Test
	public void getName() {
		
		String name = SpecialEnum.value2.name();
		
		assertThat(name).isEqualTo("value2");
	}
}

enum SpecialEnum {
	
	value1("Super amazing description"), value2("Very strange description");
	
	String description;
	
	SpecialEnum(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
}
