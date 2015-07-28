package es.juan.test.matrix;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testCreateIncreasingMatrix() {
		int width = 5;
		int height = 5;
		boolean format = true;
		
		int[][] matrix = null;
		if(format) {
			matrix = buildRowMajorMatrix(width, height);
		} else {
			matrix = buildColumnMajorMatrix(width, height);
		}
		
		
		for(int columnIndex = 0 ; columnIndex < width ; columnIndex++) {
			for(int rowIndex = 0 ; rowIndex < height ; rowIndex++) {
				System.out.print(matrix[columnIndex][rowIndex]);
			}
			System.out.println();
		}
	}

	private int[][] buildColumnMajorMatrix(int width, int height) {
		
		int[][] matrix = new int[width][height];
		int cellValue = 0;
		
		for(int rowIndex = 0 ; rowIndex < height ; rowIndex++) {
			for(int columnIndex = 0 ; columnIndex < width ; columnIndex++, cellValue++) {
				matrix[columnIndex][rowIndex] = cellValue;
			}
		}
		
		return matrix;
	}

	private int[][] buildRowMajorMatrix(int width, int height) {
		
		int[][] matrix = new int[width][height];
		int cellValue = 0;
		
		for(int columnIndex = 0 ; columnIndex < width ; columnIndex++) {
			for(int rowIndex = 0 ; rowIndex < height ; rowIndex++, cellValue++) {
				matrix[columnIndex][rowIndex] = cellValue;
			}
		}
		
		return matrix;
	}
}
