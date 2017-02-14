import java.util.*;
import java.awt.*;
//import StacksAndQueues.*;

public class Recursion {
  public static void main(String[] args) {
    System.out.println(stairSteps(4));
    System.out.println(countWays(4));
    
    int[] array = {1, 3, 4, 7, 7, 7, 7, 7, 9, 10, 15, 16};
    System.out.println(findMagicIndex(array));
    
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i = 0; i < 0; i++)
      list.add(i);
    ArrayList<ArrayList<Integer>> powerSet = powerSet(list);
    for(int i = 0; i < powerSet.size(); i++) {
      System.out.println(powerSet.get(i).toString());
    }
    
    printPerms("12345");
    //System.out.println(nthPermutation(5, 5));
  }
  
  //8.1 Triple Step
  public static int stairSteps(int n) {
    if(n == 1)
      return 1;
    
    return stairSteps(n - 1) * 2;
  }
  
  public static int countWays(int n) {
    int[] memo = new int[n+1];
    Arrays.fill(memo, -1);
    return countWays(n, memo);
  }
  
  private static int countWays(int n, int[] memo) {
    if(n < 0)
      return 0;
    else if(n == 0)
      return 1;
    else if(memo[n] > -1)
      return memo[n];
    else {
      memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo);
      return memo[n];
    }
  }
  
  //8.2 Robot in a Grid
  //Making an assumption that our input is int[] with 0's meaning blocked and 1's otherwise
  //Making assumption that the path returned is an ArrayList<Point>
  public static ArrayList<Point> findPath(int[][] maze) {
    if(maze.length == 0 || maze == null)
      return null;
    ArrayList<Point> path = new ArrayList<Point>();
    HashSet<Point> visited = new HashSet<Point>();
    
    if(findPath(path, visited, new Point(0, 0), maze))
      return path;
    
    return null;
  }
  
  private static boolean findPath(ArrayList<Point> path, HashSet<Point> visited, Point current, int[][] maze) {
      if(current.x > maze[0].length || current.y > maze[0].length 
        || maze[current.y][current.x] == 0)
        return false;
   
      if(visited.contains(current))
        return false;
      
      boolean atEnd = (current.y == maze.length - 1) && (current.x == maze[0].length - 1);
      
      if(atEnd || findPath(path, visited, new Point(current.x + 1, current.y), maze)
        || findPath(path, visited, new Point(current.x, current.y + 1), maze)) {
          path.add(current);
          return true;
        }
        
      visited.add(current);
      return false;
  }
  
  //8.3 Magic Index
  //Theres an obvious O(n) algorithm - Just iterate through and check if i = A[i]
  //The only possible better could be O(logn) using a binary search
  //We need to focus on the fact that this is sorted with distinct integers
  public static int findMagicIndex(int[] array) {
    if(array.length == 0 || array == null)
      return -1;
    
    return findMagicIndex(0, array.length - 1, array);
  }
  
  private static int findMagicIndex(int min, int max, int[] array) {
    int mid = (min + max)/2;
    
    if(mid > max || mid < min)
      return -1;
    
    if(array[mid] > mid)
      return findMagicIndex(min, mid - 1, array);
    else if(array[mid] < mid)
      return findMagicIndex(mid + 1, max, array);
    else if(array[mid] == mid)
      return mid;
    else
      return -1;
  }
  
  //8.4 Power Set
  //The efficiency of this I believe is currently O(n2) because removing an element takes n time
  //It could be improved to O(n) if this process was constant
  public static ArrayList<ArrayList<Integer>> powerSet(ArrayList<Integer> array) {
    ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    HashSet<ArrayList<Integer>> hash = new HashSet<ArrayList<Integer>>();
    
    list.add(array);
    hash.add(array);
    
    for(int i = 0; i < array.size(); i++) {
      ArrayList<Integer> listMinusOne = new ArrayList<Integer>();
      for(int j = 0; j < array.size(); j++) {
        if(j != i)
          listMinusOne.add(array.get(j));
      }
      
      removeElement(list, hash, listMinusOne);
    }
    
    return list;
  }
  
  private static void removeElement(ArrayList<ArrayList<Integer>> list, HashSet<ArrayList<Integer>> hash, ArrayList<Integer> array) {    
    if(!hash.contains(array)) {
      list.add(array);
      hash.add(array);
    }
    
    if(array.size() == 0)
      return;
    
    for(int i = 0; i < array.size(); i++) {
      ArrayList<Integer> listMinusOne = new ArrayList<Integer>();
      for(int j = 0; j < array.size(); j++) {
        if(j != i)
          listMinusOne.add(array.get(j));
      }
      
      removeElement(list, hash, listMinusOne);
    }
  }
  
  //8.5 Recursive Multiply without *
  //So this is super easy iteratively...
  public static int multiply(int a, int b) {
    int product = 0;
    for(int i = 0; i < b; i++)
      product += a;
    
    return product;
  }
  
  //Its also super easy Recursively...
  //Is this efficient though?
  public static int recursiveMultiply(int a, int b) {
    if(b == 0)
      return 0;
    return a + recursiveMultiply(a, b-1);
  }
  
  //8.6 Towers of Hanoi
  
  //Print all permutations of an array
  public static void printPerms(String s) {
    printPerms("", s);
  }
  
  private static void printPerms(String prefix, String str) {
    if(str.length() == 0)
      System.out.println(prefix);
    else {
      for(int i = 0; i < str.length(); i++) {
        printPerms(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
      }
    }
  }
  
  public static String nthPermutation(int n, int k) {
    ArrayList<String> list = new ArrayList<String>();
    
    StringBuilder sb = new StringBuilder();
    for(int i = 1; i <= n; i++)
      sb.append(i);
    
    nthPermutation("", sb.toString(), list, k);
    return list.get(k-1);
  }
  
  private static String nthPermutation(String prefix, String str, ArrayList<String> list, int k) {
    if(str.length() == 0) {
      return prefix;
    }
    else {
      for(int i = 0; i < str.length(); i++) {
        String s = nthPermutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), list, k);
        if(s != null)
          list.add(s);
      }
    }
    
    return null;
  }
  
  /*private class Tower {
    private MyStack<Integer> disks;
    private int index;
    
    public Tower(int i) {
      disks = new MyStack<Integer>();
      index = i;
    }
    
    public int index() {
      return index;
    }
    
    public void add(int d) {
      if(!disks.isEmpty() && disks.peek() <= d)
        System.out.println("Error placing disk " + d);
      else
        disks.push(d);
    }
    
    public void moveTopTo(Tower t) {
      int top = disks.pop();
      t.add(top);
    }
  }*/
}