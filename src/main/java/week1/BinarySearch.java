package week1;

import java.util.Arrays;

public class BinarySearch {

	private int[] elements;

	BinarySearch(int[] elements) {
		this.elements = elements;
		Arrays.sort(elements);
	}

	public int binarySearch(int x) {

		int lowIndex = 0, hiIndex = this.elements.length - 1;
		int midIndex = (lowIndex + hiIndex) / 2;

		while (lowIndex <= hiIndex) {
			int midElemenet = elements[midIndex];
			if (x == midElemenet)
				return midIndex;
			if (x > midElemenet) {
				lowIndex = midIndex + 1;
			} else {
				hiIndex = midIndex - 1;
			}
			midIndex = (lowIndex + hiIndex) / 2;
		}
		return -1;
	}

	public static void main(String[] args) {
		 int[] elements = {0};
		 BinarySearch bs = new BinarySearch(elements);
		 System.out.println(bs.binarySearch(0));

	}

}
