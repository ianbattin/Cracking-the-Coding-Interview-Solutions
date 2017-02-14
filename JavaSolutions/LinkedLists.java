import java.util.*;
import LinkedLists.*;

public class LinkedLists
{
	public static void main(String []args)
	{
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("a");
		list.add("a");
		list.add("g");
		list.add("b");
		list.add("c");
		list.add("b");
		list.add("g");
		list.add("f");
		list.add("a");
		
		//System.out.println(removeDupesLL(list) + "\n"); //Needs fixing
		//System.out.println(removeDupesWithBufferLL(list) + "\n"); //Needs fixing
		System.out.println(removeNode(list, 4) + "\n");
		System.out.println(removeKthToLastElement(list, 3) + "\n");
		System.out.println(partitionList(list, "c") + "\n");
		
		MyLinkedList<Integer> num1 = new MyLinkedList<Integer>();
		num1.add(7);
		num1.add(1);
		num1.add(6);
		
		MyLinkedList<Integer> num2 = new MyLinkedList<Integer>();
		num2.add(5);
		num2.add(9);
		num2.add(2);
		
		System.out.println(addTwoNumbersLL(num1, num2) + "\n");
		System.out.println(reverseLLLinear(list) + "\n");
		
		MyLinkedList<String> circularList = new MyLinkedList<String>();
		circularList.add("a");
		circularList.add("b");
		circularList.add("c");
		circularList.add("d");
		circularList.add("e");
		System.out.println(checkLoop(circularList) + "\n");
		
		System.out.println(checkPalindromeLL(list) + "\n");
	}
	
	//2.1 - Part I
	public static String removeDupesLL(MyLinkedList list) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		return returnList.removeDupesLL().toString();
	}
	
	//2.1 - Part II
	public static String removeDupesWithBufferLL(MyLinkedList list) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		return returnList.removeDupesWithBufferLL().toString();
	}
	
	//2.2
	public static String removeKthToLastElement(MyLinkedList list, int k) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		returnList.removeKthToLastElement(k);
		return returnList.toString();
	}
	
	//2.3
	public static String removeNode(MyLinkedList list, int index) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		returnList.removeNode(returnList.get(index));
		return returnList.toString();
	}
	
	//2.4
	public static String partitionList(MyLinkedList list, String x) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		return returnList.partitionList(x).toString();
	}
	
	//2.5
	public static String addTwoNumbersLL(MyLinkedList num1, MyLinkedList num2) {
		MyLinkedList<Integer> returnList = new MyLinkedList<Integer>();
		returnList = returnList.addTwoNumbersLL(num1, num2);
		return returnList.toString();
	}
	
	//Interview Cake
	public static String reverseLLLinear(MyLinkedList list) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		returnList.reverseLinear();
		return returnList.toString();
	}
	
	//2.6
	public static String checkLoop(MyLinkedList list) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		returnList.get(4).next = returnList.get(2);
		return returnList.checkLoop();
	}
	
	//2.7
	public static boolean checkPalindromeLL(MyLinkedList list) {
		MyLinkedList<String> returnList = new MyLinkedList<String>(list);
		return returnList.checkPalindromeLL();
	}
}