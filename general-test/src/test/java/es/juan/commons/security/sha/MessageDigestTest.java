package es.juan.commons.security.sha;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MessageDigestTest {

	@Test
	public void testGenerateSHA256Hash() throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		
		byte[] shaHash = messageDigest.digest("Hola".getBytes(StandardCharsets.UTF_8));
		
		String encodeValue = new String(Base64.getEncoder().encode(shaHash), StandardCharsets.UTF_8);
		
		assertThat(encodeValue).isEqualTo("5jP0/Hm63qHcXblwzzl8gki6xHzDrPmRW6YLXXaw6I8=");

	}
}
