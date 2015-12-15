package es.juan.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class DownloadTest {

	@Test
	public void testDownloadFile() throws Exception{
		
		URL image = new URL("http://learning.es/blog/wp-content/uploads/2014/08/java.jpg");
		
		File tempFile = File.createTempFile("test-", ".jpg");
		OutputStream output = new FileOutputStream(tempFile);
		
		try{
			
			IOUtils.copyLarge(image.openStream(), output);
		} finally {
			output.close();
		}
		
		System.out.println(tempFile.getPath());
		System.out.println(tempFile.length());
	}
	
	@Test
	public void testDonwloadUsingJava8() throws Exception {
		
		URL image = new URL("http://learning.es/blog/wp-content/uploads/2014/08/java.jpg");
		
		Path tempFile = Files.createTempFile("test-java8-", ".jpg");
		Files.copy(image.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println(tempFile.toString());
		System.out.println(tempFile.toFile().length());
	}
}
