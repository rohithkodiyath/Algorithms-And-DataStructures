/**
 * 
 */
package week2.stack;

import java.util.NoSuchElementException;

/**
 * @author rohith
 *
 */
public interface Stack <T> extends Iterable<T>{
	
	
	/**
	 * Push element to the stack
	 * @param element
	 */
	public void push(T element);
	
	/**
	 * pop element from the stack
	 * @return Top most element from the stack
	 * @throws NoSuchElementException is the stack is empty
	 */
	public T pop();
	
	/**
	 * @return the size of the stack
	 */
	public int size();
	
	/**
	 * @return true is size is 0
	 */
	public boolean isEmpty();
	
}
