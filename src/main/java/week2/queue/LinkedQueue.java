package week2.queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * 
             +                          +
	             |                          |
	             |StartNode                 | EndNode
	           + v                          v
	           +-+-+----+----+---+----+----++---+
	+------->      |    |    |   |    |    |      +------>
	enQueue        |    |    |   |    |    |      DeQueue
	           +---+----+----+---+----+----+----+
	                   LinkedList
                   
 */

public class LinkedQueue<T> implements Queue<T> {

	//newly added nodes
	private Node<T> startNode;
	
	//recently added nodes are stored at this end
	private Node<T> endNode;
	private int size = 0;
	
	public LinkedQueue() {
	}
 
	
	private class Node<T>{
		private T data;
		private Node<T> next;
	}
	
	//ass new nodes at the end of linked list
	public void enQueue(T data) {
		Node<T> newNode = new Node<T>();
		newNode.data = data;
		if(isEmpty()) {
			startNode  = endNode = newNode;
		}else {
			endNode.next = newNode;
			endNode = newNode;
		}
		size++;
	}

	public T deQueue() {
		if(isEmpty()) throw new NoSuchElementException();
		T data = startNode.data;
		startNode = startNode.next;
		size --;
		return data;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Iterator<T> iterator() {
		return new QueueIterator<T>(startNode);
	}
	
	private class QueueIterator<T> implements Iterator<T>{
		private Node<T>nextNode;
		
		public QueueIterator(Node<T> startNode) {
			nextNode = startNode;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}

		public T next() {
			T t = nextNode.data;
			//System.out.println(nextNode.data);
			nextNode = nextNode.next;
			return t;
		}
		
	}
	
	public static void main(String[] args) {
		Queue<String> q = new LinkedQueue<String>();
		q.enQueue("A");
		q.enQueue("A");
		q.enQueue("C");
		q.enQueue("D");
		
		q.deQueue();
		q.deQueue();
		q.deQueue();
		q.deQueue();
		q.enQueue("C");
		q.enQueue("D");
		//System.out.println(q.size());
		
		for (String string : q) {
			System.out.println(string);
		}
		
		
		
		
		
		
	}

}
