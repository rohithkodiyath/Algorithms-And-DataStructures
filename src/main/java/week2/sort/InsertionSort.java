/**
 * 
 */
package week2.sort;

import java.util.Arrays;

/**
 * @author rohith
 *
 */
public class InsertionSort <T extends Comparable<T>> {
	
	private T[] sortArray;
	
	InsertionSort(T[] array){
		this.sortArray = array;
	}
	
	public T[] sort() {
		for (int i = 0; i < sortArray.length; i++) {
			for (int j = i; j > 0; j--) {
				if(isLessThan(j, j-1))swap(j, j-1);
				else break;
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
		 array[0] = 5;
		 array[1] = 4;
		 array[2] = 3;
		 array[3] = 2;
		 array[4] = 1;
		 
		 InsertionSort<Integer> sSort = new InsertionSort<Integer>(array);
		 System.out.println(Arrays.toString(sSort.sort()));
		 // System.out.println(Arrays.toString(sSort.sortDescending()));
		 
	}

}
