package week1.assaignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final int[][] grid;
	private boolean[][] gridStates;
	private final WeightedQuickUnionUF weightedQuickUnionUF;
	private final WeightedQuickUnionUF weightedQuickUnionUFForBackwash;
	private final int gridWith;
	private final int numberOfGridElements;
	private int numberOfOpenElements;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		gridWith = n;
		numberOfGridElements = gridWith * gridWith;
		weightedQuickUnionUF = new WeightedQuickUnionUF(numberOfGridElements + 2);
		weightedQuickUnionUFForBackwash = new WeightedQuickUnionUF(numberOfGridElements + 2);
		grid = new int[gridWith][gridWith];
		gridStates = new boolean[gridWith][gridWith];
		// loop inside the grid and store index of corresponding
		// gridLinearRepresentation index as grid value
		int startGridValue = 1; // first place is reserved for first node
		for (int i = 0; i < gridWith; i++) {
			for (int j = 0; j < gridWith; j++) {
				int gridValue =  startGridValue++;
				grid[i][j] = gridValue;
				gridStates[i][j] = false;
			}
		}

	}

	/*
	 * public void printStatusMatrix() { for (int i = 0; i < gridWith; i++) { for
	 * (int j = 0; j < gridWith; j++) { StdOut.print("| " + (gridStates[i][j] ? "*"
	 * : " ")); } StdOut.println("|\n"); } }
	 */

	// opens the site (row, col) if it is not open already
	public void open(int r, int c) {
		int row = --r;
		int col = --c;
		validate(row, col);
		if (gridStates[row][col])
			return;
		gridStates[row][col] = true;
		if (row != 0 && gridStates[row - 1][col]) {
			joinTwoGrids(grid[row][col], grid[row - 1][col]);
		}
		if (row != gridWith - 1 && gridStates[row + 1][col]) {
			joinTwoGrids(grid[row][col], grid[row + 1][col]);
		}
		if (col != 0 && gridStates[row][col - 1]) {
			joinTwoGrids(grid[row][col], grid[row][col - 1]);
		}
		if (col != gridWith - 1 && gridStates[row][col + 1]) {
			joinTwoGrids(grid[row][col], grid[row][col + 1]);
		}
		joinWithTopAndBottomNode(grid[row][col]);
		numberOfOpenElements++;
	}

	private void joinTwoGrids(int p, int q) {
		weightedQuickUnionUF.union(p, q);
		if (p != (numberOfGridElements + 1) && q != (numberOfGridElements + 1)) {
			weightedQuickUnionUFForBackwash.union(p, q);
		}
	}

	private void joinWithTopAndBottomNode(int p) {
		if (p <= gridWith) {
			joinTwoGrids(0, p);
		}if (p > (numberOfGridElements - gridWith)) {
			joinTwoGrids(numberOfGridElements + 1, p);
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int r, int c) {
		int row = --r;
		int col = --c;
		validate(row, col);
		return gridStates[row][col];
	}

	// is the site (row, col) full?
	public boolean isFull(int r, int c) {
		int row = --r;
		int col = --c;
		validate(row, col);
		return checkForGridConnectivityWithBackWash(0, grid[row][col]);
	}

	private boolean checkForGridConnectivity(int p, int q) {
		return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(q);
	}
	
	private boolean checkForGridConnectivityWithBackWash(int p, int q) {
		return weightedQuickUnionUFForBackwash.find(p) == weightedQuickUnionUFForBackwash.find(q);
	}

	private void validate(int row, int col) {
		if (row < 0 || row > gridWith - 1 || col < 0 || col > gridWith - 1)
			throw new IllegalArgumentException();
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return numberOfOpenElements;
	}

	// does the system percolate?
	public boolean percolates() {
		return checkForGridConnectivity(0, numberOfGridElements + 1);
	}

	// test client (optional)
	public static void main(String[] args) {
		//Percolation percolation = new Percolation(1);
		//percolation.open(1, 1);
		//percolation.open(2, 1);
		// percolation.open(2, 2);
		// percolation.printStatusMatrix();
		//System.out.println(percolation.isFull(1, 1));
		//System.out.println(percolation.percolates());
	}
}
