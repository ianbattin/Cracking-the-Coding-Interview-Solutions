import java.util.*;

public class JavaSolutions
{
	public static void main(String []args)
	{
		char[] characters = {'a','b','c','d','e','f','g'};
		int[][] array = {
			{0,2,3,4},
			{5,6,7,8},
			{9,1,0,3},
			{4,5,6,7}
		};
		
		System.out.println(uniqueCharacter("abcdefg") + "\n");
		System.out.println(reverse(characters));
		System.out.println(permutation("abcdefg", "acdfegb") + "\n");
		System.out.println(replaceSpaces("a b c d e f g") + "\n");
		System.out.println(compressedWord("aabbbccccddeeffggg") + "\n");

		for(int i = 0; i < rotateRight(array).length; i++)
			System.out.println(Arrays.toString(rotateRight(array)[i]));
		System.out.println();
		
		for(int i = 0; i < rotateRight(array).length; i++)
			System.out.println(Arrays.toString(rotateLeft(array)[i]));
		System.out.println();
		
		int[][] zeroArray = zeroArray(array);
		for(int i = 0; i < zeroArray.length; i++)
			System.out.println(Arrays.toString(zeroArray[i]));
		System.out.println();
		
		System.out.println(isRotation("abcdefg", "efgabcd"));
	}

	//1.1
	public static boolean uniqueCharacter(String word) {
		int[] character = new int[256];
		
		for(int i = 0; i < word.length(); i++) {
			int asciiValue = (int) word.charAt(i);
			if(character[asciiValue] != 0)
				return false;
			else
				character[asciiValue]++;
		}
		
		return true;
	}
	
	//1.2
	public static String reverse(char[] word) {
		StringBuffer string = new StringBuffer();
		for(int i = word.length - 1; i > 0; i--)
			string.append(word[i]);
		
		return string.toString();
	}
	
	//1.3
	public static boolean permutation(String word1, String word2) {
		char[] word1Char = new char[256];
		char[] word2Char = new char[256];
		
		for(int i = 0; i < word1.length(); i++) {
			int asciiValue = (int) word1.charAt(i);
			word1Char[asciiValue]++;
		}
		
		for(int i = 0; i < word2.length(); i++) {
			int asciiValue = (int) word2.charAt(i);
			word2Char[asciiValue]++;
		}
		
		return Arrays.equals(word1Char, word2Char);
	}
	
	//1.4
	public static String replaceSpaces(String sentence) {
		StringBuffer string = new StringBuffer();
		for(int i = 0; i < sentence.length(); i++)
			if(sentence.charAt(i) == ' ')
				string.append("%20");
			else
				string.append(sentence.charAt(i));
		
		return string.toString();
	}
	
	//1.5
	public static String compressedWord(String word) {
		int count = 1;
		int totalCount = 1;
		char lastLetter = word.charAt(0);
		StringBuffer compressed = new StringBuffer();
		
		for(int i = 1; i < word.length(); i++) {
			if(word.charAt(i) != lastLetter) {
				compressed.append(lastLetter);
				compressed.append(count);
				count = 1;
				totalCount++;
				lastLetter = word.charAt(i);
			}
			else {
				count++;
			}
		}
		compressed.append(lastLetter);
		compressed.append(count);
		
		if(totalCount == word.length()) return word;
		return compressed.toString();
	}
	
	//1.6
	public static int[][] rotateRight(int[][] array) {
		int[][] newArray = new int[array.length][array.length];
		for(int col = 0; col < array.length; col++) {
			int count = 0;
			for(int row = array.length - 1; row >= 0; row--, count++) {
				newArray[col][count] = array[row][col];
			}
		}
		
		return newArray;
	}
	
	public static int[][] rotateLeft(int[][] array) {
		int[][] newArray = new int[array.length][array.length];
		for(int col = 0; col < array.length; col++) {
			int count = 0;
			for(int row = array.length - 1; row >= 0; row--, count++) {
				newArray[count][col] = array[col][row];
			}
		}
		
		return newArray;
	}
	
	//1.7
	public static int[][] zeroArray(int[][] array) {
		boolean[][] zeroArray = new boolean[array.length][array[0].length];
		for(int row = 0; row < array.length; row++) {
			for(int col = 0; col < array[row].length; col++) {
				if(array[row][col] == 0)
					zeroArray[row][col] = true;
			}
		}
		
		for(int row = 0; row < zeroArray.length; row++) {
			for(int col = 0; col < zeroArray[row].length; col++) {
				if(zeroArray[row][col] == true) {
					for(int y = 0; y < array.length; y++)
						array[row][y] = 0;
					for(int x = 0; x < array[row].length; x++)
						array[x][col] = 0;
				}
			}
		}
		
		return array;
	}
	
	//1.8
	public static boolean isRotation(String word1, String word2) {
		if(word1.length() != word2.length())
			return false;
		
		char first = word1.charAt(0);
		int index1 = 0;
		int index2 = 0;
		
		while(word2.charAt(index2) != first)
			index2++;
		
		int end = index2 - 1;
		boolean b = true;
		while(index2 != end) {
			if(index2 >= word2.length())
				index2 = 0;
			
			if(word1.charAt(index1) != word2.charAt(index2))
				b = false;
			
			index1++;
			index2++;
		}
		
		return b;
	}
}