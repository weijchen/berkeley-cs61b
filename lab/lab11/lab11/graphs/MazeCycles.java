package lab11.graphs;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean cycleFound = false;
    private Maze maze;
    private int cycleStart;
    private int cycleEnd;
    private ArrayList<Integer> cycleGrid = new ArrayList<>();

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        // for every visited vertex v, if there is an adjacent vertex that is already visited and is not parent of v, then there is a cycle in graph
        int startX = 1;
        int startY = 1;
        int targetX = maze.N();
        int targetY = maze.N();

        s = maze.xyTo1D(startX, startY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

        dfsCycle(s);
        drawCycle(cycleStart, cycleEnd);
        announce();
        connStartEnd(cycleStart, cycleEnd);
        announce();
    }

    public void drawCycle(int start, int end) {
        cycleGrid.add(start);
        while (start != end) {
            int curGrid = edgeTo[start];
            cycleGrid.add(curGrid);
            start = curGrid;
        }
        cycleGrid.remove(cycleGrid.size()-1);

        System.out.println(cycleGrid);

        for (int i = 0; i < maze.V(); i += 1) {
            if (!cycleGrid.contains(i)) {
                edgeTo[i] = Integer.MAX_VALUE;
            }
        }
    }

    public void connStartEnd(int start, int end) {
        edgeTo[end] = start;
    }

    // Helper methods go here
    public void dfsCycle(int v) {
        marked[v] = true;
        announce();

        if (v == t) cycleFound = true;
        if (cycleFound) return;

        for (int w : maze.adj(v)) {
            if (marked[w]) {
                if (edgeTo[v] != w) {
                    cycleFound = true;
                    cycleStart = w;
                    cycleEnd = v;
                    return;
                }
            } else {
                distTo[w] = distTo[v] + 1;
                edgeTo[w] = v;  // child's edge is parent
                marked[w] = true;
                announce();
                dfsCycle(w);
            }
        }
    }
}

