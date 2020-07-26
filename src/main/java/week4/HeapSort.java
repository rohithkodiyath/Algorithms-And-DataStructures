package week4;

import java.util.Arrays;

import javax.xml.transform.Templates;

/**
 * @author rohith
 * This is the implementation which uses binary heap.
 */
public class HeapSort {
	
	private int n = 0;	
	
	public static Comparable []  sort(Comparable [] inputArray) {
		Comparable [] array = inputArray;
		int N = array.length;
		if(N == 0 || N == 1) return array;
		for (int i = (N-1)/2 ; i >= 0; i--) {
			sink(i,N,array);
		}
		int lastIndex = N-1;
		while(lastIndex > 0) {
			swap(1, lastIndex+1, array);
			sink(0, lastIndex , array);
			lastIndex -- ;
		}
		return array;
	}

	
	
	
	private static void sink(int index,int lastIndex, Comparable[] array) {
		index ++ ; int N = lastIndex + 1; //convert  index 1 based array
		int dummyIndex = index * 2 ; // for heap sort index starts at 1
		while ( dummyIndex <= N-1 ) {
			if( dummyIndex+1 < N && lessThan(dummyIndex,dummyIndex+1, array) ) dummyIndex = dummyIndex + 1; 
			if( lessThan(dummyIndex, index, array) )
				break;
			swap(index , dummyIndex ,array);
			index = dummyIndex;
			dummyIndex = index * 2;
		}
	}
	
	
	private static boolean lessThan(int a, int b, Comparable[] array) {
		a -- ; b --;
		return array[a].compareTo(array[b]) < 0;
	}
	
	
	
	private static  void swap(int i , int j , Comparable[] array) {
		i --;j --;
		Comparable tmep = array [i];
		array[i] = array [j];
		array[j] = tmep;
	}
	
	public static void main(String[] args) {
		Integer[] arr  = {1,2,3,5,8,6,15,16,20,18,19,100,1,3,4};
		
		for (Comparable<Integer> integer : HeapSort.sort(arr)) {
			System.out.println(integer);
		}
	}
	
	
}
