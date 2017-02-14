import java.util.*;

public class StringsAndArrays {
  public static void main(String []args)
	{
		char[] characters = {'a','b','c','d','e','f','g'};
		char[] charSpaces = {'M','r',' ','J','o','h','n',' ','S','m','i','t','h',' ',' ',' ',' '};
		String[][] stringArray = {
			{"01","02","03","04"},
			{"05","06","07","08"},
			{"09","10","11","12"},
			{"13","14","15","16"}
		};
		int[][] array = {
			{1, 0, 3, 4},
			{2, 1, 6, 5},
			{0, 7, 6, 0},
			{1, 2, 3, 5}
		};
		
		System.out.println(uniqueCharacter("abcdefg") + "\n");
		System.out.println(reverse(characters));
		System.out.println(permutation("abcdefg", "acdfegb") + "\n");
		System.out.println(replaceSpaces("a b c d e f g") + "\n");
		System.out.println(replaceSpacesInPlace(charSpaces) + "\n");
		System.out.println(compressedWord("aabbbccccddeeffggg") + "\n");

		for(int i = 0; i < stringArray.length; i++)
			System.out.println(Arrays.toString(rotateRight(stringArray)[i]));
		System.out.println();
		
		for(int i = 0; i < stringArray.length; i++)
			System.out.println(Arrays.toString(rotateLeft(stringArray)[i]));
		System.out.println();
		
		int[][] zeroArray = zeroArray(array);
		for(int i = 0; i < zeroArray.length; i++)
			System.out.println(Arrays.toString(zeroArray[i]));
		System.out.println();
		
		System.out.println(isRotation("abcdefg", "efgabcd") + "\n");
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
	
	//1.4 - In Place
	public static String replaceSpacesInPlace(char[] charArray) {
		for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] == ' ') {
				for(int j = charArray.length - 1; j > i; j--) {
					charArray[j] = charArray[j - 2];
				}
				
				charArray[i] = '%';
				charArray[i+1] = '2';
				charArray[i+2] = '0';
			}
		}
		
		//This part is not part of the algorithm, just for printing
		StringBuffer returnString = new StringBuffer();
		for(int i = 0 ; i < charArray.length; i++)
			returnString.append(charArray[i]);
		
		return returnString.toString();
	}
	
	//1.5
	public static String compressedWord(String word) {
		StringBuffer compressed = new StringBuffer();
		
		char currentChar = word.charAt(0);
	    int currCount = 0;
			
		for(int i = 0; i < word.length(); i++) {			
			if(word.charAt(i) != currentChar) {
				compressed.append(currentChar);
				compressed.append(currCount + "");
				currentChar = word.charAt(i);
				currCount = 1;
			}
			else {
				currCount++;
			}
		}
		
		compressed.append(currentChar);
		compressed.append(currCount + "");
		
		return (compressed.toString().length() < word.length()) ? 
			compressed.toString() : word;
	}
	
	//1.6
	public static String[][] rotateLeft(String[][] array) {
		String[][] returnArray = new String[array.length][array.length];
		
		for(int row = 0; row < array.length; row++) {
			for(int col = 0; col < array.length; col++) {
				returnArray[row][col] = array[col][array.length-row-1];
			}
		}
		
		return returnArray;
	}
	
	public static String[][] rotateRight(String[][] array) {
		String[][] returnArray = new String[array.length][array.length];
		
		for(int row = 0; row < array.length; row++) {
			for(int col = 0; col < array.length; col++) {
				returnArray[row][col] = array[array.length-col-1][row];
			}
		}
		
		return returnArray;
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
		int word1Index = 0;
		int word2Index = 0;
		
		if(word1.length() != word2.length())
			return false;
		
		//find first letter
		char word1Char = word1.charAt(word1Index);
		while(word2.charAt(word2Index) != word1Char) {
			word2Index++;
			
			if(word2Index == word1.length())
				return false;
		}
		
		while(word1Index < word1.length()) {
			if(word1.charAt(word1Index) != word2.charAt(word2Index))
				return false;
			
			word1Index++;
			word2Index++;
			
			if(word2Index == word2.length())
				word2Index = 0;
		}
		
		return true;
	}
}