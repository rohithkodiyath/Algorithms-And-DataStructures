package week3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort <T extends Comparable<T>>{
	
	private void sortRecursively(T[] array, T[] aux,int low, int high ) {
		if(low == high) return;
		int mid =  ( low + high ) / 2;
		sortRecursively(array,aux,low,mid);
		sortRecursively(array,aux,mid+1,high);
		merge(array,aux,low,high,mid);
		
	}
	
	public void sortWIthIteration(T[] array) {
		if (array == null || array.length == 0)
			return;
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) Array.newInstance(array[0].getClass(), array.length);
		for (int i = 0; i < aux.length; i++) {
			aux[i] = array[i];
		}
		//System.out.println(Arrays.toString(array));
		for (int size = 1; size < array.length; size *= 2) {
			for (int i = 0; i < aux.length; i += (size *2)) {
				int highElement = Math.min((i + (size * 2) - 1), array.length - 1);
				int midElement = i + size - 1;
				merge(array, aux, i, highElement, midElement);
			}
			//System.out.println(Arrays.toString(array));
			//System.exit(1);
		}
	}
	
	public  void sortWIthRecursion(T[] array) {
		if(array == null || array.length == 0) return ;
		@SuppressWarnings("unchecked")
        T[] aux = (T[])Array.newInstance(array[0].getClass(), array.length);
		for (int i = 0; i < aux.length; i++) {
			aux[i] = array[i];
		}
		sortRecursively(array,aux,0,array.length - 1);
	}

	private void  merge(T[] array,T[]aux, int low,int high ,int mid) {
		for (int i = low; i <= high; i++) {
			aux[i] = array[i];
		}
		int j = mid+1;int i = low;
		for (int k = low; k <= high; k++) {
			if(i > mid) array[k] = aux[j++];
			else if(j>high) array[k] = aux[i++];
			else if(lessThan(aux[i], aux[j])) array[k] = aux[i++];
			else array[k] = aux[j ++];
		}
	}
	
	private boolean lessThan(T a,T b) {
		return a.compareTo(b) < 0;
	}
	
	public static void main(String[] args) {
		Integer[] ar = {6,7,5,5,4,3,2,3,4,1};
		MergeSort<Integer> mergeSort = new MergeSort<Integer>();
		mergeSort.sortWIthIteration(ar);
		System.out.println(Arrays.toString(ar));
	}
}
