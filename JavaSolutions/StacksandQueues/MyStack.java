package StacksAndQueues;

public class MyStack<E extends Comparable<E>> {
  public Node top;
  public int size;
  
  public MyStack() {
    top = null;
    size = 0;
  }
  
  public void push(E value) {
    Node newTop = new Node(value);
    newTop.next = top;
    top = newTop;
    size++;
  }
  
  public E pop() {
    if(top == null)
      return null;
    
    E temp = top.value;
    top = top.next;
    size--;
    return temp;
  }
  
  public E peek() {
    if(top == null)
      return null;
    
    return top.value;
  }
  
  public boolean isEmpty() {
    return (peek() == null);
  }
  
  public String toString() {
    if(this.isEmpty())
      return "{}";
    
    Node node = top;
    String returnString = "{";
    while(node.next != null) {
      returnString += node.value + ", ";
      node = node.next;
    }
    
    returnString += node.value + "}";
    return returnString;
  }
    
  public class Node {
		public E value;
		public Node next;
		
		public Node() {
			value = null;
			next = null;
		}
		
		public Node(E value) {
			this.value = value;
			this.next = null;
		}
		
		public Node(Node node) {
			this.value = node.value;
			this.next = node.next;
		}
	}
}