import java.util.*;
import StacksAndQueues.*;

public class StacksAndQueues {
  public static void main(String[] args) {
    //stackTest();
    //createAndPrintSetOfStacks(45);
    /*createAndTestQueueAsTwoStacks();
    System.out.println();
    
    MyStack<Integer> stackToBeSorted = new MyStack<Integer>();
    for(int i = 0; i < 10; i++) {
      stackToBeSorted.push((int)(Math.random() * 10));
    }
    System.out.println(stackToBeSorted.toString());
    sortStack(stackToBeSorted);
    System.out.println(stackToBeSorted.toString());*/
    
    queueTest();
  }
  
  //StackTest
  public static void stackTest() {
    MyStack<Integer> stack = new MyStack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    System.out.println(stack.toString());
    System.out.print(stack.pop());
    System.out.print(stack.pop());
    System.out.println(stack.pop());
    stack.push(6);
    stack.push(7);
    System.out.println(stack.toString());
  }
  
  //QueueTest
  public static void queueTest() {
    MyQueue<Integer> queue = new MyQueue<Integer>();
    System.out.println("Empty: " + queue.isEmpty());
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    System.out.println(queue.toString());
    System.out.print(queue.dequeue());
    System.out.print(queue.dequeue());
    System.out.println(queue.dequeue());
    queue.enqueue(6);
    queue.enqueue(7);
    System.out.println(queue.toString());
    System.out.println("Empty: " + queue.isEmpty());
  }
  //3.1 
  
  //3.2
  
  //3.3 SetOfStacks object
  public static void createAndPrintSetOfStacks(int elements) {
    SetOfStacks<Integer> stacks = new SetOfStacks<Integer>();
    for(int i = 0; i < elements; i++)
      stacks.push((int)(Math.random() * 10));
    
    stacks.print();
    
    for(int i = 0; i < elements/2; i++)
      stacks.pop();
    
    stacks.print();
  }
  
  //3.4
  
  //3.5 Queue implements with Two Stacks
  public static void createAndTestQueueAsTwoStacks() {
    QueueAsTwoStacks<Integer> queueStacks = new QueueAsTwoStacks<Integer>();
    queueStacks.enqueue(1);
    queueStacks.enqueue(2);
    queueStacks.enqueue(3);
    queueStacks.enqueue(4);
    queueStacks.enqueue(5);
    queueStacks.enqueue(6);
    queueStacks.enqueue(7);
    System.out.println(queueStacks.toString());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    queueStacks.enqueue(8);
    queueStacks.enqueue(9);
    queueStacks.enqueue(10);
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
    System.out.print(queueStacks.dequeue());
  }
  
  // 3.6 Sort Stack
  public static <E extends Comparable<E>> void sortStack(MyStack<E> stack) {
    MyStack<E> bufferStack = new MyStack<E>();
    E current;
    E bufferValue;
    int count = 0;
    
    //check if stack is empty
    if(stack.isEmpty())
      return;
    
    current = stack.pop();
    
    //check if stack is of size 1
    if(stack.peek() == null) {
      stack.push(current);
      return;
    }
    
    bufferStack.push(current);
    current = stack.pop();
    
    while(current != null) {
      while(bufferStack.peek() != null && current.compareTo(bufferStack.peek()) > 0) {
        bufferValue = bufferStack.pop();
        stack.push(bufferValue);
        count++;
      }

      bufferStack.push(current);
      for(int i = 0; i < count; i++) {
        bufferValue = stack.pop();
        bufferStack.push(bufferValue);
      }
      count = 0;
      
      current = stack.pop();
    }
    
    //move all the values currently sorted in descending order on bufferStack
    //over to the original stack, now sorted in ascending order
    while(!bufferStack.isEmpty()) {
      bufferValue = bufferStack.pop();
      stack.push(bufferValue);
    }
  }
}