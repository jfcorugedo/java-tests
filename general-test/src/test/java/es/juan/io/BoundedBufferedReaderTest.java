package es.juan.io;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class BoundedBufferedReaderTest {

	@Test(expected=SizeLimitExceededException.class)
	public void testReadFileWithoutReturnLineCharacter() throws IOException {
		BoundedBufferedReader bf = new BoundedBufferedReader(getInfiniteInputStream(-1), 1024);
		
		bf.readLine();
		
		bf.close();
	}
	
	@Test
	public void testReadSmallerEnoughtStream() throws IOException {
		BoundedBufferedReader bf = new BoundedBufferedReader(getInfiniteInputStream(1023), 1024);
		
		bf.readLine();
		
		bf.close();
	}
	
	@Test
	public void testRead() throws IOException {
		BoundedBufferedReader bf = new BoundedBufferedReader(getInfiniteInputStream(4), 1);
		
		for(int i = 0 ; i < 4 ; i++) {
			assertThat(bf.read()).isEqualTo('A');
		}
		
		bf.close();
	}
	
	@Test
	public void testReadBuffer() throws IOException {
		BoundedBufferedReader bf = new BoundedBufferedReader(getInfiniteInputStream(4), 2);
		
		char[] buffer = new char[2];
		for(int i = 0 ; i < 2 ; i++) {
			assertThat(bf.read(buffer)).isEqualTo(2);
			assertThat(buffer).isEqualTo(new char[]{'A','A'});
		}
		
		bf.close();
	}

	private InputStream getInfiniteInputStream(final long maxCharacters) {
		return new InputStream() {
			
			long currentCharactersReturned = 0L;
			
			@Override
			public int read() throws IOException {
				if(maxCharacters != -1 && currentCharactersReturned >= maxCharacters) {
					return -1;
				}
				currentCharactersReturned++;
				return 'A';
			}
		};
	}
}
