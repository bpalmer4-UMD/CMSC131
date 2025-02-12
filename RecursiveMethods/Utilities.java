package sysImplementation;

import java.util.ArrayList;

public class Utilities {

	// Returns a string where characters are separated by the specified 
	// delimiter character. A string with a single character will not have
	// a delimiter added.
	public static String addDelimiter(String str, char delimeter) {
		if (str.length() == 1) {
			return str;
		}
		return str.substring(0,1) + delimeter + addDelimiter(str.substring(1,str.length()), delimeter);
	}
	
	// Returns a string with the digits (if any) present in the str parameter.
	public static String getDigits(String str) {
		if (str.length() == 0) {
			return "";
		}
		else if (Character.isDigit(str.charAt(0))) {
			return str.charAt(0) + "" + getDigits(str.substring(1, str.length()));
		}
		return getDigits(str.substring(1, str.length()));
	}

	
	// Replaces the target character in the array with the specified replacement.
	public static void replaceCharacter(char[] array, char target, char replacement) {
		replaceCharHelper(array, target, replacement, 0);
	}
	
	// Helper method for replaceCharacter()
	private static void replaceCharHelper(char[] array, char target, char replacement, int i) {
		if (i > (array.length - 1)) {
            return;
        }
		else if (array[i] == target) {
            array[i] = replacement;
        }
        replaceCharHelper(array, target, replacement, i + 1);
	}

	// Returns the sum of even values (if any) present in the array parameter.
	public static int getSumEven(int[] array) {
		return getSumHelper(array, 0);
	}
	
	// Helper method for getSumEven()
	private static int getSumHelper(int[] array, int i) {
		if (i > (array.length - 1)) {
			return 0;
		}
		else if ((array[i] % 2) == 0) {
			return array[i] + getSumHelper(array, i + 1);
		}
		return getSumHelper(array, i + 1);
	}

	// This method returns an ArrayList with the indices of rows of the 
	// two-dimensional array having a length that corresponds to rowLength.
	public static ArrayList<Integer> getListRowIndices(int[][] array, int rowLength) {
		ArrayList<Integer> ind = new ArrayList<>();
        getListRowHelper(array, rowLength, ind, 0);
        return ind;
	}
	
	// Helper method for getListRowIndices()
	private static void getListRowHelper(int[][] array, int rowLength, ArrayList<Integer> ind, int i) {
		if (i > (array.length - 1)) {
			return;
		}
		else if (array[i].length == rowLength) {
			ind.add(i);
		}
		getListRowHelper(array, rowLength, ind, i + 1);
	}

	// This method replaces a character and instances of the character that are
	// adjacent to it, with a replacement character.
	public static int replaceCells(char[][] array, int x, int y, char target, char replacement) {
		return replaceHelper(array, x, y, target, replacement);
	}
	
	// Helper method for replaceCells()
	private static int replaceHelper(char[][] array, int x, int y, char target, char replacement) {
		int ct = 0;
		
		if (!validXY(array, x, y) || array[x][y] != target) {
            return 0;
        }
		
        array[x][y] = replacement;
        ct = ct + 1;
        
        
        ct += replaceHelper(array, x + 1, y, target, replacement);
        ct += replaceHelper(array, x - 1, y, target, replacement);
        ct += replaceHelper(array, x, y + 1, target, replacement);
        ct += replaceHelper(array, x, y - 1, target, replacement);
        ct += replaceHelper(array, x + 1, y + 1, target, replacement);
        ct += replaceHelper(array, x + 1, y - 1, target, replacement);
        ct += replaceHelper(array, x - 1, y - 1, target, replacement);
        ct += replaceHelper(array, x - 1, y + 1, target, replacement);

        return ct;
	}
	
	// This method validates the x y  pair of the array
	// passed in to replaceHelper()
	private static boolean validXY(char[][] array, int x, int y) {
        return x >= 0 && x < array.length && y >= 0 && y < array[x].length;
    }
}
