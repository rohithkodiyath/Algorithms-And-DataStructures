package week2.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T> {

	// place to store the recently added node
	private Node topNode = null;
	private int size;

	public LinkedStack() {
	}

	private class Node {
		T data;
		Node next;
	}

	public void push(T element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = topNode;
		topNode = newNode;
		size++;
	}

	public T pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		T dataToReturn = topNode.data;
		topNode = topNode.next;
		size--;
		return dataToReturn;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return topNode == null;
	}

	// get iterator
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	// iterator container
	private class StackIterator implements Iterator<T> {
		private Node nextElement = topNode;

		public boolean hasNext() {
			return nextElement != null;
		}

		public T next() {
			if (nextElement == null)
				throw new NoSuchElementException();
			T data = nextElement.data;
			nextElement = nextElement.next;
			return data;
		}
	}

	public static void main(String[] args) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		s.push(1);
		s.push(2);
		// s.pop();

		System.out.println("--> size " + s.size());
		for (Integer element : s) {
			System.out.println(element);
		}

	}

}
