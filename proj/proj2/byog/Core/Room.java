package byog.Core;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Room {
    private ArrayList<Position> edges = new ArrayList<Position>();
    private boolean connected = false;
    private int conRoom = 0;
    private int edgeSize = 0;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    public Room() {

    }

    public Room(int startX, int endX, int startY, int endY) {
        xMin = startX;
        xMax = endX;
        yMin = startY;
        yMax = endY;
    }

    public boolean isConnected() {
        return connected;
    }

    public void addConn() {
        conRoom += 1;
    }

    public int getConRoom() {
        return conRoom;
    }

    public void setConnected() {
        connected = true;
    }

    public void addEdge(Position p) {
        edges.add(p);
        addEdgeSize();
    }

    public void addEdgeSize() {
        edgeSize += 1;
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public ArrayList<Position> getEdges() {
        return edges;
    }

    public Position randomEdge() {
        ArrayList<Position> edgeList = getEdges();
        int edgeID = (int)(Math.random() * (edgeSize));
        return edgeList.get(edgeID);
    }
}
