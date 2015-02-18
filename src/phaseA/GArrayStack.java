package phaseA;
import java.util.EmptyStackException;
import providedCode.*;
//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
//This class called GArrayStack is a stack implementation
//that using an array, and implements GStack interface.
//The code is similar to ArrayStack but is generic. 

public class GArrayStack<T> implements GStack<T> {
	
	private static final int RESIZE_FACTOR = 2;
	private static final int INITIAL_SIZE = 10;
	private T[] data;
	private int index;
	// Initialize the original empty ArrayStack
	@SuppressWarnings("unchecked")
	public GArrayStack() {		
		data = (T[])new Object[INITIAL_SIZE];
		index = -1;
	}
	// Check the ArrayStack is empty or not.
	// Returns true if it's empty,
	// Returns false otherwise
	@Override
	public boolean isEmpty() {
		return index<0;
	}

	// Put the given element to the top of the stack
	// If the stack is full, increase the size of array twice as before.
	@Override
	public void push(T newElement) {
		index++;
		if(index >= data.length){
			@SuppressWarnings("unchecked")
			T[] newData = (T[])new Object[RESIZE_FACTOR*data.length];
			for(int i=0; i<data.length; i++){
				newData[i] = data[i];
			}
			data = newData;
		}
		data[index] = newElement;
	}

	// Removing the top element of stack
	// If the stack is empty, throw an exception
	// Returns the value of removed element
	@Override
	public T pop() {
		checkEmpty();
		T removed = data[index];
        index--;
		return removed;
	}

	// Take a look at the top element of stack without removing it
	// Returns the top element
	// If the stack is empty, throw an exception
	@Override
	public T peek() {
		checkEmpty();
		return data[index];
	}
	
	// Helper method used to throw an exception is the stack is empty
	public void checkEmpty(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
	}
}