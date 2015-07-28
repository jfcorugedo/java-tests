package es.juan.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

public class BoundedBufferedReader extends Reader {
	
	public static final String DEFAULT_CHARSET = "ISO-8859-1";

	private BufferedReader simpleBufferedReader;
	
	private BoundedInputStream boundedInStream;
	
	
	public BoundedBufferedReader(InputStream inputStream, long maxLineLength, String encoding) throws UnsupportedEncodingException {
		this.boundedInStream = new BoundedInputStream(maxLineLength, inputStream);
		this.simpleBufferedReader = new BufferedReader(new InputStreamReader(boundedInStream, encoding));
	}
	
	public BoundedBufferedReader(InputStream inputStream, long maxLineLength) throws UnsupportedEncodingException {
		this.boundedInStream = new BoundedInputStream(maxLineLength, inputStream);
		this.simpleBufferedReader = new BufferedReader(new InputStreamReader(boundedInStream, DEFAULT_CHARSET));
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int result = this.simpleBufferedReader.read();
		this.boundedInStream.resetCount();
		return result;
	}

	@Override
	public void close() throws IOException {
		this.simpleBufferedReader.close();
	}

	@Override
	public int read(CharBuffer target) throws IOException {
		int result = this.simpleBufferedReader.read(target);
		this.boundedInStream.resetCount();
		return result;
	}

	@Override
	public int read() throws IOException {
		int result = this.simpleBufferedReader.read();
		this.boundedInStream.resetCount();
		return result;
	}

	@Override
	public int read(char[] cbuf) throws IOException {
		int result = this.simpleBufferedReader.read(cbuf);
		this.boundedInStream.resetCount();		
		return result;
	}

	@Override
	public long skip(long n) throws IOException {
		
		return this.simpleBufferedReader.skip(n);
	}

	@Override
	public boolean ready() throws IOException {
		
		return this.simpleBufferedReader.ready();
	}

	@Override
	public boolean markSupported() {
		
		return this.simpleBufferedReader.markSupported();
	}

	@Override
	public void mark(int readAheadLimit) throws IOException {
		
		this.simpleBufferedReader.mark(readAheadLimit);
	}

	@Override
	public void reset() throws IOException {
		
		this.simpleBufferedReader.reset();
	}

	public String readLine() throws IOException {
		String line = this.simpleBufferedReader.readLine();
		this.boundedInStream.resetCount();
		return line;
	}
}
