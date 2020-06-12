package week1;

import java.util.Arrays;

public class QuickUnion {
	
	private int[] elements;
	private int[] sizeOfElements;
	private int numberOfConnectedComponents;
	
	public QuickUnion(int n) {
		elements = new int[n];
		sizeOfElements = new int[n];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = i;
			sizeOfElements[i] = 1;
		}
		numberOfConnectedComponents = n;
	}
	
	private int root(int p) {
		while(elements[p] != p) {
			p = elements[p];
		}
		return p;
	}
	
	public boolean connected(int p,int q) {
		return root(p) == root(q);
	}
	
	public void union(int p,int q) {
		int rootP = root(p);
		int rootQ = root(q);
		if(rootP == rootQ)
			return;//connected already
		elements[q] = p;
		numberOfConnectedComponents --;
	}
	
	public void printElements() {
		System.out.println(Arrays.toString(elements));
	}
	
	
	
	public static void main(String[] args) {
		int numberOfELements = 10;
		QuickUnion unionFind = new QuickUnion(numberOfELements);
		unionFind.union(1, 2);
		unionFind.union(2, 3);
		unionFind.union(3, 5);
		unionFind.union(2, 4);
		//System.out.println(unionFind.connected(1, 5));
		unionFind.printElements();
		
	}
	

}
