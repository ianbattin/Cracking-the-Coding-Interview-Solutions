import java.util.*;
import TreesAndGraphs.*;

public class TreesAndGraphs {
  public static void main(String[] args) {
    testBST();
    System.out.println(canConstruct("abcdef", "bcda"));    
  }
  
  public static void testBST() {
    MyBST<String, Integer> bst = new MyBST<String, Integer>();
    bst.insert("a", 3);
    bst.insert("b", 1);
    bst.insert("c", 5);
    bst.insert("d", 0);
    bst.insert("e", 2);
    bst.insert("f", 4);
    bst.insert("g", 6);
    bst.printTree();
    
    System.out.println(bst.findMinElement());
    
    bst.delete(5);
    bst.printTreeWithKey();
    bst.delete(4);
    bst.printTreeWithKey();
    
    System.out.println(bst.find(1));
  }
  
  public static boolean canConstruct(String ransomNote, String magazine) {
    if(ransomNote.length() > magazine.length())
      return false;
    
    int[] noteCharCount = new int[26];
    int[] magazineCharCount = new int[26];
    
    
    for(int i = 0; i < magazine.length(); i++) {
        if(i < ransomNote.length()) 
            noteCharCount[(int)ransomNote.charAt(i)-97]++;
            
        magazineCharCount[(int)magazine.charAt(i)-97]++;
    }
    
    String one = "";
    String two = "";
    
    for(int i = 0; i < noteCharCount.length; i++) {
      one += noteCharCount[i] + ", ";
      two += magazineCharCount[i] + ", ";
      
      if(magazineCharCount[i] - noteCharCount[i] < 0)
          return false;
    }
    
    System.out.println(one);
    System.out.println(two);
    
    return true;
  }
  
  //5.4
  public static ArrayList<MyLinkedList<Integer>> BSTLevelList(MyBST bst) {
    ArrayList<MyLinkedList<Integer>> list = new ArrayList<MyLinkedList<Integer>>();
    MyQueue<Integer> queue = new MyQueue<Integer>();
    
  }
}