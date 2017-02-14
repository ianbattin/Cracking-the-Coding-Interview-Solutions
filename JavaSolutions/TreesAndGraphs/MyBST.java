package TreesAndGraphs;

import java.util.*;
import StacksAndQueues.*;

public class MyBST<T, E extends Comparable<E>> {
  Node root;
  int size;
  
  public MyBST() {
    root = null;
    size = 0;
  }
  
  public T find(E key) {
    if(isEmpty())
      throw new NullPointerException();
    
    return find(root, key);
  }

  private T find(Node node, E key) {
    if(node == null)
      throw new NullPointerException();
    
    if(key.compareTo(node.key) == 0)
      return node.object;
    else if(key.compareTo(node.key) > 0)
      return find(node.right, key);
    else
      return find(node.left, key);
  }
  
  public boolean insert(T object, E key) {
    if(isEmpty()) {
      root = new Node(object, key);
      return true;
    }
    
    return insert(root, object, key);
  }
  
  private boolean insert(Node node, T object, E key) {
    if(node == null) {
      node = new Node(object, key);
      return true;
    }
    
    if(key.compareTo(node.key) == 0) {
      node.object = object;
      return true;
    }
    else if(key.compareTo(node.key) > 0) {
      if(node.right == null) {
        node.right = new Node(object, key);
        return true;
      }
      else
        return insert(node.right, object, key);
    }
    else {
      if(node.left == null) {
        node.left = new Node(object, key);
        return true;
      }
      else
        return insert(node.left, object, key);
    }
  }
  
  public T delete(E key) {
    if(isEmpty())
      return null;
    
    Node deletedNode = delete(root, key);
    if(deletedNode == null)
      return null;
    else
      return deletedNode.object;
  }
  
  private Node delete(Node node, E key) {
    if(node == null) return null;
    
    if(key.compareTo(node.key) > 0)
      node.right = delete(node.right, key);
    else if(key.compareTo(node.key) < 0)
      node.left = delete(node.left, key);
    else {
      if(node.left != null && node.right != null) {
        Node minNode = findMin(node.right);
        node.object = minNode.object;
        node.key = minNode.key;
        node.right = delete(node.right, node.key);
      }
      else
        node = (node.right != null) ? node.right : node.left;
    }
    
    return node;
  }
  
  public T findMinElement() {
    if(isEmpty())
      throw new NullPointerException();
    
    return findMin(root).object;
  }
  
  public Node findMin() {
    if(isEmpty())
      throw new NullPointerException();
    
    return findMin(root);
  }
  
  private Node findMin(Node node) {
    if(node == null)
      throw new NullPointerException();
    if(node.left == null)
      return node;
    else
      return findMin(node.left);
  }
  
  public String toString() {
    MyQueue<Node> queue = new MyQueue<Node>();
    String returnString = "";
    
    queue.enqueue(root);
    while(!queue.isEmpty()) {
      Node node = queue.dequeue();
      System.out.println(node.object);
      returnString += node.object + ", ";
      if(node.left != null)
        queue.enqueue(node.left);
      if (node.right != null)
        queue.enqueue(node.right);
    }
    
    return returnString;
  }
  
  public void printTree()
	{
		if(root == null) 
			System.out.println("");
		else
		{
			System.out.println(root.object);
			
			printTree(root.right, "    ");
			printTree(root.left, "    ");
		}
	}
	
	private void printTree(Node node, String indent)
	{
		if(node == null) {}
		else
		{
			System.out.println(indent + node.object);
			
			printTree(node.right, indent + "    ");
			printTree(node.left, indent + "    ");
		}
	}
  
  public void printTreeWithKey()
	{
		if(root == null) 
			System.out.println("");
		else
		{
			System.out.println(root.object + "[" + root.key + "]");
			
			printTreeWithKey(root.right, "    ");
			printTreeWithKey(root.left, "    ");
		}
	}
	
	private void printTreeWithKey(Node node, String indent)
	{
		if(node == null) {}
		else
		{
			System.out.println(indent + node.object + "[" + node.key + "]");
			
			printTreeWithKey(node.right, indent + "    ");
			printTreeWithKey(node.left, indent + "    ");
		}
	}
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
  }
  
  private class Node implements Comparable<Node>{
    Node left;
    Node right;
    T object;
    E key;
    
    public Node() {
      left = null;
      right = null;
      object = null;
      key = null;
    }
    
    public Node(Node node) {
      left = node.left;
      right = node.right;
      object = node.object;
      key = node.key;
    }
    
    public Node(E key) {
      left = null;
      right = null;
      object = null;
      this.key = key;
    }
    
    public Node(T object, E key) {
			left = null;
      right = null;
      this.object = object;
      this.key = key;
		}
    
    @Override
    public int compareTo(Node other) {
      return this.key.compareTo(other.key);
    }
  }
}