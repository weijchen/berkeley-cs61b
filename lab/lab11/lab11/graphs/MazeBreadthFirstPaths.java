package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
//    private int[] disTo;
//    private int[] edgeTo;
//    private boolean[] marked;
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        if (s == t) targetFound = true;

        // mark the starting point
        bfsQueue.add(s);
        marked[s] = true;
        announce();

        // start the traverse
        while (!bfsQueue.isEmpty()) {
            int v = bfsQueue.remove();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    bfsQueue.add(w);
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    marked[w] = true;
                    announce();
                    // end the traverse once target point is marked
                    if (marked[t]) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
         bfs();
    }
}

