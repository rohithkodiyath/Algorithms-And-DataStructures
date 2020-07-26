package week4;

import java.util.Arrays;

import javax.xml.transform.Templates;

/**
 * @author rohith
 * This is the implementation which uses binary heap.
 */
public class PriorityQueue <T extends Comparable<T>>{
	
	private T [] array;
	private int n = 0;
	
	
	public PriorityQueue(int length) {
		array = (T[]) new Comparable [ length + 1 ];
	}
	
	public void add (T input) {
		array[ ++ n  ] = input;
		swim( n );
	}
	
	public T[] getArray() {
		return array;
	}
	
	public T getMax() {
		T max =  array [1];
		swap( 1 , n);
		array [n -- ] = null;
		sink(1);
		return max;
	}
	
	private void sink(int index) {
		//property of binary heap such that children will be on index 2i and 2i+1
		int k = 2 * index;
		while ( k <= n ) {
			if ( k+1 <= n && leassThan( array[k] ,array [k + 1])) k = k+1;
			if (leassThan( array[index], array[k]) ) { 
				swap(index, k);
				index = k;
				k = 2 * index;
				continue;
			} 
			break;
		}
	}
	
	public void swim(int index) {
		int parent = index / 2 ;
		//proprty of binary heap parent position will be integer div of index
		while( parent  >= 1  && leassThan( array [parent] , array [index] ) ) {
			swap(parent,index);
			index = parent;
			parent = index / 2 ;
		}
	}
	
	private boolean leassThan( T x , T  y) {
		return x.compareTo(y) < 0 ;
	}
	
	private void swap(int i , int j) {
		T tmep = array [i];
		array[i] = array [j];
		array[j] = tmep;
	}
	
	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue<Integer>(6);
		pq.add(2);
		pq.add(1);
		pq.add(5);
		pq.add(3);
		pq.add(3);
		pq.add(4);
		
		for (int i = 0; i < 6; i++) {
			System.out.println(pq.getMax());
		}
	}
	
	
}
