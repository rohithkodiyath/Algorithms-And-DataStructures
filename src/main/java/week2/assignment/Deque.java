package week2.assignment;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> startNode;
	private Node<Item> endNode;
	private int size = 0;

	private class Node<T> {
		T data;
		Node<T> next;
		Node<T> previous;
	}

	// construct an empty deque
	public Deque() {

	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of Ts on the deque
	public int size() {
		return size;

	}

	// add the T to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Can not add null element");
		Node<Item> newNode = new Node<Item>();
		newNode.data = item;
		if (isEmpty()) {
			startNode = endNode = newNode;
		} else {
			startNode.previous = newNode;
			newNode.next = startNode;
			startNode = newNode;
		}
		size++;
	}

	// add the T to the back
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Can not add null element");
		Node<Item> newNode = new Node<Item>();
		newNode.data = item;
		if (isEmpty()) {
			startNode = endNode = newNode;
		} else {
			endNode.next = newNode;
			newNode.previous = endNode;
			endNode = newNode;
		}
		size++;
	}

	// remove and return the T from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		Node<Item> tempNode = startNode;
		startNode = tempNode.next;
		if(startNode != null) startNode.previous = null;
		tempNode.next = null;
		size--;
		return tempNode.data;
	}

	// remove and return the T from the back
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		Node<Item> tempNode = endNode;
		endNode = tempNode.previous;
		tempNode.previous = null;
		if(endNode != null)
			endNode.next = null;
		size--;
		return tempNode.data;

	}

	private class DequeIterator<T> implements Iterator<T> {

		Node<T> nextNode;

		DequeIterator(Node<T> sNode) {
			nextNode = sNode;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException("No element");
			T dataToReturn = nextNode.data;
			nextNode = nextNode.next;
			return dataToReturn;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Iterator<Item> iterator() {
		return new DequeIterator<Item>(startNode);

	}

	public static void main(String[] args) {

		Deque<Integer> deque = new Deque<Integer>();
		         StdOut.println( deque.isEmpty()) ;//        ==> true
		         StdOut.println(deque.isEmpty()) ;//        ==> true
		         deque.addFirst(3) ; //
		         StdOut.println(deque.removeFirst()); //
	}

}
