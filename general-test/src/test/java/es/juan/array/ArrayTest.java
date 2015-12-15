package es.juan.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;

import org.junit.Test;

public class ArrayTest {

	@Test(expected=ArrayStoreException.class)
	public void testSystemArrayCopy() {
		int[] source = new int[]{1,2,3,4,5};
		Integer[] dest = new Integer[5];
		
		System.arraycopy(source, 0, dest, 0, source.length);//Throws an exception!!!
		
		assertThat(dest).containsExactly(1,2,3,4,5);
	}
	
	@Test
	public void testCopyArraysUsingStream() {
		int[] source = new int[]{1,2,3,4,5};
		
		Integer[] result = (Integer[])Arrays.stream(source).boxed().toArray(Integer[]::new);
		
		assertThat(result).containsExactly(1,2,3,4,5);
	}
	
	@Test
	public void testConcat() throws Exception {
		
		/*
		 * String that simulates a file with this content:
		 * .fa-glass:before {
         *    content: "\f000";
         * }
         * .fa-music:before {
         *    content: "\f001";
         * }
         * .fa-search:before {
         *    content: "\f002";
         * }
		 */
		String cssFile = ".fa-glass:before {\n content: \"\\f000\";\n}\n.fa-music:before {\n content: \"\\f001\";\n}\n.fa-search:before {\ncontent: \"\\f002\";}";
		
		BufferedReader br = new BufferedReader(new StringReader(cssFile));
		
		String line = null;
		String name = null;
		String value = null;
		StringBuilder result = new StringBuilder();
		while( (line = br.readLine()) != null) {
			if(line.contains(".fa-")) {
				name = line.substring(line.indexOf(".fa-") + 4, line.indexOf(":")).toUpperCase();
			} else if(line.contains(";")){
				value = line.substring(line.indexOf(": \"") + 3, line.lastIndexOf("\""));
				result.append(String.format("%s(%s)\n", name, value));
			}
		}
		
		/*
		 * It will print:
		 * GLASS(\f000)
         * MUSIC(\f001)
         * SEARCH(\f002)
		 */
		System.out.println(result.toString());
		
		assertThat(result.toString()).isEqualTo("GLASS(\\f000)\nMUSIC(\\f001)\nSEARCH(\\f002)\n");
	}
	
}
