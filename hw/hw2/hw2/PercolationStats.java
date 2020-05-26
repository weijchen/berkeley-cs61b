package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.nio.channels.ScatteringByteChannel;

public class PercolationStats {
    public int perimeter;
    public int numberTest;
    public PercolationFactory pf;
    public Percolation pc;
    public int[] percolatePoint;
    private final long seed = 20;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("Range of N and T is/are invalid.");
        else {
            perimeter = N;
            numberTest = T;
            this.pf = pf;
            pc = this.pf.make(N);
        }
    }

    // execution of the percolation simulation
    public int[] simulationPercolation() {
        StdRandom.setSeed(seed);
        percolatePoint = new int[numberTest];
        for (int t = 0; t < numberTest; t++) {
            int counter = 0;
            boolean isPercolate = false;
            while (!isPercolate) {
                int openGridID = StdRandom.uniform(perimeter*perimeter);
                int row = pc.vectorTo2DRow(openGridID);
                int col = pc.vectorTo2DCol(openGridID);

                if (!pc.isOpen(row, col)) {
                    counter += 1;
                    pc.open(row, col);
                    if (pc.percolates()) {
                        percolatePoint[t] = counter;
                        isPercolate = true;
                    }
                }
            }
            if (!pc.percolates()) System.out.println("no percolate!");
            pc = this.pf.make(perimeter);
        }
        return percolatePoint;
    }

    // sample mean of percolation threshold
    public double mean(int[] a) {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev(int[] a) {
        return StdStats.stddev(a);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(double mean, double std, int T) {
        return mean + 1.96 * std / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(double mean, double std, int T) {
        return mean - 1.96 * std / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = 20;
        int T = 10;
        PercolationStats ps = new PercolationStats(N, T, new PercolationFactory());
        int[] point = ps.simulationPercolation();
        double mean = ps.mean(point);
        double dev = ps.stddev(point);

        for (int p : point) {
            System.out.println(p);
        }

        System.out.println(mean);
        System.out.println(dev);
        System.out.println(ps.confidenceHigh(mean, dev, T));
        System.out.println(ps.confidenceLow(mean, dev, T));
    }
}
