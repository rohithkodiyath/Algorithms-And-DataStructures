package week1.assaignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final double[] fractions;
	private final int numberOfTrials;
	private  double  mean = -1;
	private  double sd = -1;
	private  double confLo = -1;
	private  double confHi = -1;
	// perform independent trials on an n-by-n grid

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();
		numberOfTrials = trials;
		int sizeOfGrid = n * n;
		fractions = new double[numberOfTrials];
		for (int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(n);
			while (!percolation.percolates()) {
				int randomRow = StdRandom.uniform(n) + 1;
				int randomCol = StdRandom.uniform(n) + 1;
				percolation.open(randomRow, randomCol);
			}
			double fraction = ((double) percolation.numberOfOpenSites()) / sizeOfGrid;
			fractions[i] = fraction;

		}
		this.mean = mean();
		this.sd = stddev();
		this.confLo = confidenceLo();
		this.confHi = confidenceHi();
	}

	// sample mean of percolation threshold
	public double mean() {
		return this.mean == -1 ? StdStats.mean(fractions) : this.mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return this.sd == -1 ? StdStats.stddev(fractions) : this.sd;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return  this.confLo == -1 ? ( mean - calculateThreshold() ) : this.confLo;
	}

	private double calculateThreshold() {
		return (1.96 * sd) / Math.sqrt(numberOfTrials);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return  this.confHi == -1 ? ( mean + calculateThreshold() ) : this.confHi;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();
		PercolationStats percolationStats = new PercolationStats(n, trials);
		StdOut.println("mean                     = " + percolationStats.mean());
		StdOut.println("stddev                   = " + percolationStats.stddev());
		StdOut.println("95% confidence interval  = [" + percolationStats.confidenceLo() + ", "
				+ percolationStats.confidenceHi() + "]");
	}
}
