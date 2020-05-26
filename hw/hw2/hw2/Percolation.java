package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static int perimeter;
    private WeightedQuickUnionUF uf_open;
    private WeightedQuickUnionUF uf_full;
    private int[] isOpenList;
    private int[] isFullList;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Entered N is less than 0!");
        perimeter = N;
        uf_open = new WeightedQuickUnionUF(N*N);
        uf_full = new WeightedQuickUnionUF(N*N);
        isOpenList = new int[N*N];
        isFullList = new int[N*N];
        for (int i = 0; i < isOpenList.length; i++) {
            isOpenList[i] = 0;
            isFullList[i] = 0;
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (outOfBound(row, col)) throw new IndexOutOfBoundsException("Entered row or col is not valid!");
        int gridID = matrixTo1D(row, col);

        if (isOpenList[gridID] == 1) {
            return;
        }
        isOpenList[gridID] = 1;
        if (row == 0)
            isFullList[gridID] = 1;

        // Connect the grid to neighborhood
        if (checkOpen(row-1, col)) {
            int neighGridID = matrixTo1D(row-1, col);
            uf_open.union(gridID, neighGridID);
            castWater();
        }
        if (checkOpen(row+1, col)) {
            int neighGridID = matrixTo1D(row+1, col);
            uf_open.union(gridID, neighGridID);
            castWater();
        }
        if (checkOpen(row, col-1)) {
            int neighGridID = matrixTo1D(row, col-1);
            uf_open.union(gridID, neighGridID);
            castWater();
        }
        if (checkOpen(row, col+1)) {
            int neighGridID = matrixTo1D(row, col+1);
            uf_open.union(gridID, neighGridID);
            castWater();
        }
    }

    public void castWater() {
        for (int i = 0; i < perimeter; i++)
            for (int j = perimeter; j < perimeter*perimeter; j++)
                if (uf_open.connected(j, i)) isFullList[j] = 1;
    }

    public boolean checkOpen(int row, int col) {
        if (!outOfBound(row, col)) {
            return isOpen(row, col);
        }
        return false;
    }

    public boolean checkFull(int row, int col) {
        if (!outOfBound(row, col)) {
            return isFull(row, col);
        }
        return false;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (outOfBound(row, col)) throw new IndexOutOfBoundsException("Entered row or col is not valid!");
        int gridID = matrixTo1D(row, col);
        return isOpenList[gridID] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (outOfBound(row, col)) throw new IndexOutOfBoundsException("Entered row or col is not valid!");
        int gridID = matrixTo1D(row, col);
        return isFullList[gridID] == 1;
    }

    // number of open sites
    public int numberOfOpenSites() {
        int retVal = 0;
        for (int i = 0; i < isOpenList.length; i++) {
            if (isOpenList[i] == 1) retVal += 1;
        }
        return retVal;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < perimeter; i++) {
            for (int j = (perimeter-1)*perimeter; j < perimeter*perimeter; j++) {
                if(uf_open.connected(i, j)) return true;
            }
        }
        return false;
    }

    // checker function for the outOfBound situation
    public boolean outOfBound(int row, int col) {
        return row < 0 || row > (perimeter - 1) || col < 0 || col > (perimeter - 1);
    }

    public int matrixTo1D(int row, int col) {
        return row * perimeter + col;
    }

    public int vectorTo2DRow(int id) {
        return (int) (id / perimeter);
    }

    public int vectorTo2DCol(int id) {
        return id % perimeter;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
