package sysImplementation;

import java.util.ArrayList;

public class Utilities {

	public static String addDelimiter(String str, char delimeter) {
		if (str.length() == 1) {
			return str;
		}
		return str.substring(0,1) + delimeter + addDelimiter(str.substring(1,str.length()), delimeter);
	}
	

	public static String getDigits(String str) {
		if (str.length() == 0) {
			return "";
		}
		else if (Character.isDigit(str.charAt(0))) {
			return str.charAt(0) + "" + getDigits(str.substring(1, str.length()));
		}
		return getDigits(str.substring(1, str.length()));
	}

	
	public static void replaceCharacter(char[] array, char target, char replacement) {
		replaceCharHelper(array, target, replacement, 0);
	}
	
	private static void replaceCharHelper(char[] array, char target, char replacement, int i) {
		if (i > (array.length - 1)) {
            return;
        }
		else if (array[i] == target) {
            array[i] = replacement;
        }
        replaceCharHelper(array, target, replacement, i + 1);
	}

	
	public static int getSumEven(int[] array) {
		return getSumHelper(array, 0);
	}
	
	private static int getSumHelper(int[] array, int i) {
		if (i > (array.length - 1)) {
			return 0;
		}
		else if ((array[i] % 2) == 0) {
			return array[i] + getSumHelper(array, i + 1);
		}
		return getSumHelper(array, i + 1);
	}

	
	public static ArrayList<Integer> getListRowIndices(int[][] array, int rowLength) {
		ArrayList<Integer> ind = new ArrayList<>();
        getListRowHelper(array, rowLength, ind, 0);
        return ind;
	}
	
	private static void getListRowHelper(int[][] array, int rowLength, ArrayList<Integer> ind, int i) {
		if (i > (array.length - 1)) {
			return;
		}
		else if (array[i].length == rowLength) {
			ind.add(i);
		}
		getListRowHelper(array, rowLength, ind, i + 1);
	}

	
	public static int replaceCells(char[][] array, int x, int y, char target, char replacement) {
		return replaceHelper(array, x, y, target, replacement);
	}
	
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
	
	private static boolean validXY(char[][] array, int x, int y) {
        return x >= 0 && x < array.length && y >= 0 && y < array[x].length;
    }
}
