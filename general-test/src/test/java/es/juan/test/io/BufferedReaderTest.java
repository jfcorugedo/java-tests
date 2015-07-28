package es.juan.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;

public class BufferedReaderTest {

	@Test(expected=OutOfMemoryError.class)
	public void testReadFileWithoutReturnLineCharacter() throws IOException {
		BufferedReader bf = new BufferedReader(getInfiniteReader());
		
		bf.readLine();
		
		bf.close();
	}

	private Reader getInfiniteReader() {
		return new Reader(){

			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				return 'A';
			}

			@Override
			public void close() throws IOException {
				
			}
			
		};
	}
}
