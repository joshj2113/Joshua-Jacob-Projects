//Joshua Jacob
//115732401
//Recitation 3

package hw3;

import java.util.Stack;

/**
 * this class contains methods which also help with throwing the exceptions
 */

public class PackageStack {
	
    private final int CAPACITY = 7;
    
    private Stack<Package> stack = new Stack<Package>();


    /**
     * pushes element onto the top of the stack
     * @param x for the element that is being pushed onto the stack
     * @throws FullStackException for when the stack reaches the CAPACITY
     */
    public void push(Package x) throws FullStackException {
       
    	if (stack.size() >= CAPACITY) {
            throw new FullStackException("");
        }
        stack.push(x);
    }

    /**
     * this method is used to remove the top most element in the stack
     * @return removed element
     * @throws EmptyStackException for when there is nothing in the stack
     */
    public Package pop() throws EmptyStackException {
        
    	if (stack.size() <= 0) {
            throw new EmptyStackException("");
        }
        return stack.pop();
    }

    /**
     * this method is used to peek at the top element of a stack
     * @return top element of the stack
     * @throws EmptyStackException for when there is nothing in the stack
     */
    public Package peek() throws EmptyStackException {
        if (stack.size() <= 0) {
            throw new EmptyStackException("");
        }
        return stack.peek();
    }
    
    /**
     * this method is used to check if the stack has reached the capacity
     * @return true or false based off of if the stack has reached the CAPACITY
     */
    public boolean isFull() {
    	
    	if (stack.size() >= CAPACITY) {
    		return true;
    	} else
    		return false;
    }

    /**
     * this method is used to check if the stack has no elements in it
     * @return true or false based off of if the stack has 0 elements
     */
    public boolean isEmpty() {
    
        return stack.isEmpty();
    }
   
}

