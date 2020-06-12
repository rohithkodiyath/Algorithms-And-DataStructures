package week1;

import java.util.Arrays;

public class WeightedQuickUnion {
	
	private int[] elements;
	private int[] sizeOfElements;
	private int numberOfConnectedComponents;
	
	public WeightedQuickUnion(int n) {
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
		if(sizeOfElements[rootP] < sizeOfElements[rootQ]) {
			elements[rootP] = rootQ;
			sizeOfElements[rootQ] += sizeOfElements[rootP]; 
		}else {
			elements[rootQ] = rootP;
			sizeOfElements[rootP] += sizeOfElements[rootQ];
		}
		numberOfConnectedComponents --;
	}
	
	public void printElements() {
		System.out.println(Arrays.toString(elements));
	}
	
	public int getNumberOfConnectionsComponents() {
		return numberOfConnectedComponents;
	}
	
	
	
	public static void main(String[] args) {
		int numberOfELements = 10;
		WeightedQuickUnion unionFind = new WeightedQuickUnion(numberOfELements);
		unionFind.union(1, 2);
		unionFind.union(2, 3);
		unionFind.union(3, 5);
		unionFind.union(2, 4);
		unionFind.connected(1, 2);
		unionFind.printElements();
		
	}
	

}
