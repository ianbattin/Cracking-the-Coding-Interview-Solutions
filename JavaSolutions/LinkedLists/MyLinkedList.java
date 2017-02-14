package LinkedLists;

import java.util.*;

public class MyLinkedList<E extends Comparable<E>> {
	public Node root;
	public int size;
	
	public MyLinkedList() {
		root = null;
		size = 0;
	}
	
	public MyLinkedList(Node root, int size) {
		this.root = root;
		this.size = size;
	}
	
	public MyLinkedList(MyLinkedList<E> listToCopy) {
		size = listToCopy.size;

		Node pointerNode = listToCopy.root;
		while(pointerNode != null) {
			this.add(pointerNode.value);
			pointerNode = pointerNode.next;
		}
	}
	
	public void add(E value) {
		if(root == null) {
			root = new Node(value);
			return;
		}
		
		Node currentNode = root;
		while(currentNode.next != null) {
			currentNode = currentNode.next;
		}
		
		size++;
		currentNode.next = new Node(value);
	}
	
	public Node get(int index) {
		Node currentNode = root;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		
		return currentNode;
	}
	
	public String toString() {
		String returnString = "{";
		
		Node currentNode = root;
		
		while(currentNode.next != null) {
			returnString += currentNode.value + "->";
			currentNode = currentNode.next;
		}
		
		returnString += currentNode.value + "}";
		
		return returnString;
	}
	
	public MyLinkedList removeDupesLL() {
		if(this.size < 2)
			return this;
		
		Node outerNode = this.root;
		Node innerNode;
		
		while(outerNode.next != null) {
			innerNode = outerNode;
			while(innerNode.next != null) {
				if(outerNode.value == innerNode.next.value)
					innerNode.next = innerNode.next.next;
				
				innerNode = innerNode.next;
			}
			
			outerNode = outerNode.next;
		}
		
		return this;
	}
	
	public MyLinkedList removeDupesWithBufferLL() {
		if(this.size < 2)
			return this;
		
		HashMap<Integer, E> hash = new HashMap<Integer, E>();
		
		Node currentNode = root;
		hash.put(root.value.hashCode(), root.value);
		
		while(currentNode.next != null) {
			if(hash.containsValue(currentNode.next.value)) {
				Node tempNode = currentNode.next;
				
				while(hash.containsValue(tempNode.value)) {
					tempNode = tempNode.next;
				}
				
				currentNode.next = tempNode;
				hash.put(currentNode.next.value.hashCode(), currentNode.next.value);
			}
			else {
				hash.put(currentNode.next.value.hashCode(), currentNode.next.value);
			}
			
			currentNode = currentNode.next;
		}
		
		return this;
	}
	
	public void removeNode(Node node) {
		Node currentNode = node;
		while(currentNode.next.next != null) {
			currentNode.value = currentNode.next.value;
			currentNode = currentNode.next;
		}
		
		currentNode.value = currentNode.next.value;
		currentNode.next = null;
	}
	
	public void removeKthToLastElement(int k) {
		Node leadNode = root;
		Node tailNode = root;
		
		for(int i = 0; i < k - 1; i++) {
			leadNode = leadNode.next;
		}
		
		while(leadNode.next.next != null) {
			leadNode = leadNode.next;
			tailNode = tailNode.next;
		}
		
		tailNode.next = tailNode.next.next;	
	}
	
	public MyLinkedList partitionList(E x) {
		Node lessRoot = null;
		Node moreRoot = null;
		Node lessThanNode = null;
		Node moreThanNode = null;
		Node currentNode = root;
		
		while(currentNode != null) {
			if(currentNode.value.compareTo(x) < 0) {
				if(lessThanNode == null) {
					lessRoot = new Node(currentNode.value);
					lessThanNode = lessRoot;
				}
				else {
					lessThanNode.next = new Node(currentNode.value);
					lessThanNode = lessThanNode.next;
				}
			}
			else {
				if(moreThanNode == null) {
					moreRoot = new Node(currentNode.value);
					moreThanNode = moreRoot;
				}
				else {
					moreThanNode.next = new Node(currentNode.value);
					moreThanNode = moreThanNode.next;
				}
			}
			
			currentNode = currentNode.next;
		}
		
		lessThanNode.next = moreRoot;
		return new MyLinkedList(lessRoot, size);
	}
	
	public MyLinkedList addTwoNumbersLL(MyLinkedList list1, MyLinkedList list2) {
		int num1 = 0;
		int num2 = 0;
		Node list1Node = list1.root;
		Node list2Node = list2.root;
		int decimalPlace = 1;
		
		while(list1Node != null) {
			num1 += ((Integer)list1Node.value).intValue() * decimalPlace;
			list1Node = list1Node.next;
			decimalPlace *= 10;
		}
		
		decimalPlace = 1;
		
		while(list2Node != null) {
			num2 += ((Integer)list2Node.value).intValue() * decimalPlace;
			list2Node = list2Node.next;
			decimalPlace *= 10;
		}
		
		MyLinkedList<Integer> returnList = new MyLinkedList<Integer>();
		int result = num1 + num2;

		decimalPlace = 1;
		while(result / decimalPlace > 0) {
			returnList.add(new Integer((result/decimalPlace) % 10));
			decimalPlace *= 10;
		}
		
		return returnList;
	}
	
	public void reverseLinear() {
		if(this.size > 1) {
			Node nextNode = null;
			Node previousNode = null;
			Node currentNode = root;
			
			while(currentNode != null) {
				nextNode = currentNode.next;
				currentNode.next = previousNode;
				previousNode = currentNode;
				currentNode = nextNode;
			}

			root = previousNode;
		}
	}
	
	public MyLinkedList reverseOut() {
		MyLinkedList newList = new MyLinkedList();
		Node currentNode = root;
		Node previousNode = null;
		Node newNode = null;
		while(currentNode.next != null) {
			if(newNode == null) {
				newNode = new Node(currentNode.value);
				previousNode = newNode;
			}
			else {
				newNode = new Node(currentNode.next.value);
				newNode.next = previousNode;
				previousNode = newNode;
				currentNode = currentNode.next;
			}
		}
		
		newList.root = previousNode;
		
		return newList;
	}
	
	public String checkLoop() {
		Node currentNode = root;
		HashMap<Integer, E> hash = new HashMap<Integer, E>();
		
		while(!hash.containsValue(currentNode.value)) {
			hash.put(currentNode.value.hashCode(), currentNode.value);
			currentNode = currentNode.next;
		}
		
		return currentNode.value.toString();
	}
	
	public boolean checkPalindromeLL() {
		MyLinkedList reversed = this.reverseOut();
		
		Node currentNode = root;
		Node currentRNode = reversed.root;
		while(currentNode != null) {
			if(currentNode.value.compareTo(currentRNode.value) != 0)
				return false;
			
			currentNode = currentNode.next;
			currentRNode = currentRNode.next;
		}
		
		return true;
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