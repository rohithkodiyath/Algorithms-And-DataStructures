/**
 * 
 */
package week2.sort;

import java.util.Arrays;

/**
 * @author rohith
 *
 */
public class SelectionSort <T extends Comparable<T>> {
	
	private T[] sortArray;
	
	SelectionSort(T[] array){
		this.sortArray = array;
	}
	
	public T[] sortAscending() {
		for (int i = 0; i < (sortArray.length-1); i++) {
			for (int j = i+1; j < sortArray.length; j++) {
				if(isLessThan(j, i)) swap(i, j);
			}
		}
		return sortArray;
	}
	
	public T[] sortDescending() {
		for (int i = 0; i < (sortArray.length-1); i++) {
			for (int j = i+1; j < sortArray.length; j++) {
				if(isLessThan(i, j)) swap(i, j);
			}
		}
		return sortArray;
	}
	
	 
	private void swap(int i,int j) {
		T temp = sortArray[i];
		sortArray[i] = sortArray[j];
		sortArray[j] = temp;
	}
	
	private boolean isLessThan(int i,int j) {
		T a = sortArray[i];
		T b = sortArray[j];
		return a.compareTo(b) < 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Integer[] array = new Integer[5];
		 array[0] = 1;
		 array[1] = 2;
		 array[2] = 3;
		 array[3] = 4;
		 array[4] = 5;
		 
		 SelectionSort<Integer> sSort = new SelectionSort<Integer>(array);
		 System.out.println(Arrays.toString(sSort.sortAscending()));
		 System.out.println(Arrays.toString(sSort.sortDescending()));
		 
	}

}
