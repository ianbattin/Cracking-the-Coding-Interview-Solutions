package StacksAndQueues;

import java.util.*;

public class SetOfStacks<E extends Comparable<E>> {
  public int threshold;
  public int size;
  public ArrayList<MyStack> stacks;
  public int currentStack;
  
  public SetOfStacks() {
    threshold = 20;
    size = 0;
    currentStack = 0;
    stacks = new ArrayList<MyStack>();
  }
  
  public SetOfStacks(int threshold) {
    this.threshold = threshold;
    size = 0;
    currentStack = 0;
    stacks = new ArrayList<MyStack>();
  }
  
  public void push(E value) {
    if(stacks.isEmpty()) {
      stacks.add(new MyStack());
    }
    
    if(stacks.get(currentStack).size == threshold) {
      currentStack++;
      stacks.add(new MyStack());
    }
    
    stacks.get(currentStack).push(value);
    size++;
  }
  
  public E pop() {
    if(stacks.get(0) == null)
      return null;
    
    if(stacks.get(currentStack).size == 0) {
      stacks.set(currentStack, null);
      currentStack--;
    }
    
    size--;
    return (E)stacks.get(currentStack).pop();
  }
  
  public void print() {
    for(int i = 0; i < stacks.size(); i++) {
      System.out.println(stacks.get(i).toString());
    }
  }
}