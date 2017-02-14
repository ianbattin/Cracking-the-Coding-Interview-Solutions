package StacksAndQueues;

import java.util.*;

public class QueueAsTwoStacks<E extends Comparable<E>> {
  public MyStack<E> enqueueStack;
  public MyStack<E> dequeueStack;
  public boolean enqueuing;
  public int size;
  
  public QueueAsTwoStacks() {
    enqueueStack = new MyStack<E>();
    dequeueStack = new MyStack<E>();
    enqueuing = true;
    size = 0;
  }
  
  public void enqueue(E value) {
    if(!enqueuing) {
      E dequeued = dequeueStack.pop();
      while(dequeued != null) {
        enqueueStack.push(dequeued);
        dequeued = dequeueStack.pop();
      }
      
      enqueuing = true;
    }
    
    enqueueStack.push(value);
  }

  public E dequeue() {
    if(enqueuing) {
      E dequeued = enqueueStack.pop();
      while(dequeued != null) {
        dequeueStack.push(dequeued);
        dequeued = enqueueStack.pop();
      }
      
      enqueuing = false;
    }
    
    return dequeueStack.pop();
  }
  
  public E peek() {
    if(enqueuing) {
      E dequeued = enqueueStack.pop();
      while(dequeued != null) {
        dequeueStack.push(dequeued);
        dequeued = enqueueStack.pop();
      }
      
      enqueuing = false;
    }
    
    return dequeueStack.peek();
  }
  
  public String toString() {
    if(!enqueuing) {
      E dequeued = dequeueStack.pop();
      if(dequeued != null)
        enqueueStack.push(dequeued);
      
      enqueuing = true;
    }
    
    return enqueueStack.toString();
  }
}