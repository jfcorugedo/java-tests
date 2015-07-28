package es.juan.os;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.mockito.internal.util.io.IOUtil;

public class ProcessExecutorTest {

	@Test
	public void testProcessExecutor() throws IOException {
		List<String> command = new ArrayList<String>();
		command.add("echo");
		command.add("argument to Main method");
		command.add("another argument to Main method");
		ProcessBuilder builder = new ProcessBuilder(command);
		File workingDirectory = new File(".");
		builder.directory(workingDirectory);
		Process process = builder.start();
		
		Collection<String> lines = IOUtil.readLines(process.getInputStream());
		for(String line : lines) {
			System.out.println(line);
		}
	}
}
