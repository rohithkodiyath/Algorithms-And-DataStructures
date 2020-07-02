package week2.assignment;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] rQueueElements;
	private int lastIndex = -1;

	// construct an empty randomized queue
	public RandomizedQueue() {
		rQueueElements = (Item[]) new Object[1];
	};

	// is the randomized queue empty?
	public boolean isEmpty() {
		return lastIndex == -1;
	}

	// return the number of items on the randomized queue
	public int size() {
		return lastIndex + 1;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Can not add null element");
		validateAndResize();
		rQueueElements[++lastIndex] = item;
	};

	// remove and return a random item
	public Item dequeue() {
		if (size() == 0)
			throw new NoSuchElementException("Empty queue");
		int randomIndexChoosen = StdRandom.uniform(0, lastIndex + 1);// include the last element
		Item retElement = rQueueElements[randomIndexChoosen];
		rQueueElements[randomIndexChoosen] = rQueueElements[lastIndex];
		rQueueElements[lastIndex] = null;
		lastIndex--;
		return retElement;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (size() == 0)
			throw new NoSuchElementException("Empty queue");
		int randomIndexChoosen = StdRandom.uniform(0, lastIndex + 1);// include the last element
		return rQueueElements[randomIndexChoosen];
	}

	// TODO remove unwanted elements
	private void validateAndResize() {
		if (!isEmpty() && lastIndex == (rQueueElements.length - 1)) {
			Item[] copyArray = (Item[]) new Object[2 * size()];
			for (int i = 0; i <= lastIndex; i++) {
				copyArray[i] = rQueueElements[i];
			}
			rQueueElements = copyArray;
		}
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private Item[] copiedElements;
		private int lastIdx;

		RandomQueueIterator(int size) {
			copiedElements = (Item[]) new Object[size];
			lastIdx = size - 1;
			for (int i = 0; i < size; i++) {
				copiedElements[i] = rQueueElements[i];
			}
		}

		public boolean hasNext() {
			return lastIdx != -1;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			int randomIndexChoosen = StdRandom.uniform(0, lastIdx + 1);// include the last element
			Item ementChoosen = copiedElements[randomIndexChoosen];
			copiedElements[randomIndexChoosen] = copiedElements[lastIdx];
			// copiedElements[lastIdx] = null;
			lastIdx--;
			return ementChoosen;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomQueueIterator(size());
	};

	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
		q.dequeue();
		q.dequeue();

		StdOut.println(q.sample());
		StdOut.println(q.size());

		for (String s : q) {
			StdOut.println(s);
		}

	}
}
