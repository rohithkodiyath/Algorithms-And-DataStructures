package week2.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

	private int latestUpdatedIndex = -1;
	private T[] elements = (T[]) new Object[1];

	public void push(T element) {
		validateAndReSize();
		elements[++latestUpdatedIndex] = element;
	}

	private void validateAndReSize() {
		int lengthOfElementsArray = elements.length;
		if (lengthOfElementsArray - 1 == latestUpdatedIndex) {
			T[] newElementsArray = (T[]) new Object[2 * lengthOfElementsArray];
			for (int i = 0; i < elements.length; i++) {
				newElementsArray[i] = elements[i];
			}
			elements = newElementsArray;
		}
	}

	public T pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		return elements[latestUpdatedIndex--];
	}

	public int size() {
		return latestUpdatedIndex + 1;
	}

	public boolean isEmpty() {
		return latestUpdatedIndex == -1;
	}

	public Iterator<T> iterator() {
		return new StackIterator();

	}

	private class StackIterator implements Iterator<T> {

		int nextElement = latestUpdatedIndex;

		public boolean hasNext() {
			return nextElement != -1;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return elements[nextElement--];
		}

	}

	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		stack.push("e");
		
		while(!stack.isEmpty())
			System.out.println(stack.pop());
		
		
	}

}
