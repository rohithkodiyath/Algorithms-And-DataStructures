package week1.exercise;

import java.util.Arrays;

public class LargestInConnectedComponent {
	
	private int[] elements;
	private int[] sizeOfElements;
	private int numberOfConnectedComponents;
	public int[] maxElement;
	
	public LargestInConnectedComponent(int n) {
		elements = new int[n];
		sizeOfElements = new int[n];
		maxElement = new int[n];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = i;
			sizeOfElements[i] = 1;
			maxElement[i] = i;
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
		if(sizeOfElements[rootP] < sizeOfElements[rootQ]) {
			elements[rootP] = rootQ;
			sizeOfElements[rootQ] += sizeOfElements[rootP]; 
			maxElement[rootQ] = max(maxElement[rootP], maxElement[rootQ]);
			
		}else {
			elements[rootQ] = rootP;
			sizeOfElements[rootP] += sizeOfElements[rootQ];
			maxElement[rootP] = max(maxElement[rootP], maxElement[rootQ]);
		}
		numberOfConnectedComponents --;
		
	}
	
	public int find(int p) {
		return maxElement[root(p)];
	}
	
	private int max(int a,int b) {
		return a > b ? a : b;
	}
	
	public void printElements() {
		System.out.println(Arrays.toString(elements));
	}
	
	public int getNumberOfConnectionsComponents() {
		return numberOfConnectedComponents;
	}
	
	
	
	public static void main(String[] args) {
		int numberOfELements = 10;
		LargestInConnectedComponent unionFind = new LargestInConnectedComponent(numberOfELements);
		unionFind.union(1, 2);
		unionFind.union(5, 2);
		unionFind.union(5, 7);
		unionFind.printElements();
		System.out.println(unionFind.find(5));
		
	}
	

}
