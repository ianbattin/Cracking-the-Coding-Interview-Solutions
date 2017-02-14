package StacksAndQueues;

public class MyQueue<E extends Comparable<E>> {
  public Node front;
  public Node back;
  public int size;
  
  public MyQueue() {
    front = null;
    back = null;
    size = 0;
  }
  
  public void enqueue(E value) {
    if(front == null) {
      front = new Node(value);
      back = front;
    }
    else {
      Node newNode = new Node(value);
      newNode.next = back;
      back.prev = newNode;
      back = newNode;
    }
    size++;
  }
  
  public E dequeue() {
    if(front == null)
      return null;
    
    Node temp = front;
    front = front.prev;
    size--;
    
    return temp.value;
  }
  
  public String toString() {
    String returnString = "";
    Node currentNode = front;
    while(currentNode != null) {
      returnString += currentNode.value;
      currentNode = currentNode.prev;
    }
    
    return returnString;
  }
  
  public boolean isEmpty() {
    return front == null;
  }
  
  public class Node {
		public E value;
		public Node next;
    public Node prev;
		
		public Node() {
			value = null;
			next = null;
      prev = null;
		}
		
		public Node(E value) {
			this.value = value;
			this.next = null;
      this.prev = null;
		}
		
		public Node(Node node) {
			this.value = node.value;
			this.next = node.next;
      this.prev = node.prev;
		}
	}
}