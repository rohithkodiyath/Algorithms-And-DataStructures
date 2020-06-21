package week2.queue;

public interface Queue <T> extends Iterable<T>{

	
	/**
	 * Add element  to the end of queue
	 * @param data to be added 
	 */
	void enQueue(T data);
	
	
	T deQueue();
	
	boolean isEmpty();
	
	int size();

}
