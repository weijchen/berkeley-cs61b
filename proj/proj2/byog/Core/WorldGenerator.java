package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;
    private static ArrayList<Room> rooms = new ArrayList<Room>();
    public static int numRoom = 0;
    private static final int DIFFICULTY = 300;

    /* Initial code caller */
    public static void addHexagon(TETile[][] world) {
        Position[][] worldP = initPosition(world);
        genRoom(worldP);
        genPath(worldP);
        fetchPositionInfo(worldP, world);
    }

    /* Generate the Room */
    public static void genRoom(Position[][] posMap) {
        initRoomEdge(posMap);
        expandRoomEdge(posMap);
    }

    /* Return whether current tile is edge (since PLAYER occur at the end, using PLAYER instead) */
    public static boolean findRoomEdge(TETile tile) {
        return tile == Tileset.PLAYER;
    }

    /* Create room for each initial edge point */
    public static void expandRoomEdge(Position[][] posMap) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (findRoomEdge(posMap[x][y].getTType())) {
                    int edgeWidth = 2 + (int)(Math.random() * ((15 - 2) + 1));
                    int edgeLength = 2 + (int)(Math.random() * ((15 - 2) + 1));
                    castExpand(posMap, x, y, edgeWidth, edgeLength);
                }
            }
        }
    }

    /* Expand the edge counter clock-wise, then erase unusable points */
    public static void castExpand(Position[][] posMap, int posX, int posY, int w, int l) {
        expand(posMap, posX, posY, -w, l);
        expand(posMap, posX, posY, w, l);
        expand(posMap, posX, posY, -w, -l);
        expand(posMap, posX, posY, w, -l);
        if (posMap[posX][posY].getTType() == Tileset.PLAYER) {
            posMap[posX][posY].setTType(Tileset.NOTHING);
        }
    }

    /* Expand function */
    public static void expand(Position[][] posMap, int posX, int posY, int w, int l) {
        int startX = Math.min(posX + w, posX);
        int endX   = Math.max(posX + w, posX);
        int startY = Math.min(posY + l, posY);
        int endY   = Math.max(posY + l, posY);
        if (startX == 0) startX += 1;
        if (endX == 0) endX += 1;
        if (startY == 0) startY += 1;
        if (endY == 0) endY += 1;
        if (startX == WIDTH) startX -= 1;
        if (endX == WIDTH) endX -= 1;
        if (startY == HEIGHT) startY -= 1;
        if (endY == HEIGHT) endY -= 1;
        boolean pass = notOverlap(posMap, startX, startY, endX, endY);

        if (pass) {
            Room newRoom = new Room(startX, endX, startY, endY);
            for (int x = startX; x <= endX; x++) {
                posMap[x][startY].setTType(Tileset.WALL);
                posMap[x][endY].setTType(Tileset.WALL);
                newRoom.addEdge(new Position(x, startY));
                newRoom.addEdge(new Position(x, endY));
            }
            for (int y = startY; y <= endY; y++) {
                posMap[startX][y].setTType(Tileset.WALL);
                posMap[endX][y].setTType(Tileset.WALL);
                newRoom.addEdge(new Position(startX, y));
                newRoom.addEdge(new Position(endX, y));
            }
            for (int x = startX + 1; x < endX; x++) {
                for (int y = startY + 1; y < endY; y++) {
                    posMap[x][y].setTType(Tileset.FLOOR);
                }
            }
            rooms.add(newRoom);
            numRoom += 1;
        }
    }

    /* Check the condition of overlapping */
    public static boolean notOverlap(Position[][] posMap, int startX, int startY, int endX, int endY) {
        if ((startX < 0) || (startX >= WIDTH) || (startY < 0) || (startY >= HEIGHT) || (endX < 0) || (endX >= WIDTH) || (endY < 0) || (endY >= HEIGHT))
            return false;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (posMap[x][y].getTType() == Tileset.WALL || posMap[x][y].getTType() == Tileset.PLAYER) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Create room edge randomly */
    public static void initRoomEdge(Position[][] posMap) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int room = (int) (Math.random()*DIFFICULTY);
                TETile tType = tranTTileToPLAYER(room);
                posMap[x][y].setTType(tType);
            }
        }
    }

    public static TETile tranTTileToPLAYER(int x) {
        switch (x) {
            case 0: return Tileset.PLAYER;
            default: return Tileset.NOTHING;
        }
    }

    /* Generate the path */
    public static void genPath(Position[][] world) {
        for (Room room : rooms) {
            ArrayList<Room> pairRoomList = new ArrayList<Room>();
            for (int i = 0; i < 4; i++) {
                Room pairRoom = roomPair(room, pairRoomList);
                if (pairRoom != null) {
                    pairRoom.addConn();
                    drawPath(world, room, pairRoom);
                    pairRoomList.add(pairRoom);
                }
            }
        }
        buildWall(world);
        buildGate(world);
    }

    /* Build the wall in the world */
    public static void buildWall(Position[][] world) {
        for (Position[] pRow : world) {
            for (Position p : pRow) {
                if (p.getTType() == Tileset.FLOOR) {
                    ArrayList<Position> neighbor = getNeighbor(world, p);
                    addWall(neighbor);
                }
            }
        }
    }

    /* Randomly select a wall and initiate as the gate */
    public static void buildGate(Position[][] world) {
        ArrayList<Position> candidate = new ArrayList<>();
        for (Position[] pRow : world) {
            for (Position p : pRow) {
                if (p.getTType() == Tileset.WALL) {
                    ArrayList<Position> neighbor = getFourNeighbor(world, p);
                    for (Position n : neighbor) {
                        if (n.getTType() == Tileset.FLOOR) {
                            System.out.println("here");
                            candidate.add(p);
                        }
                    }
                }
            }
        }
        int canID = (int) (Math.random()*candidate.size());
        candidate.get(canID).setTType(Tileset.LOCKED_DOOR);
    }

    /* Assign the pair room for making connection */
    public static Room roomPair(Room curRoom, ArrayList<Room> curRoomList) {
        Room retRoom = null;
        double retDis = 10000.0;
        for (Room room : rooms) {
            if (!curRoomList.contains(room) && room.getConRoom() < 2) {
                double disTemp = roomDistance(curRoom, room);
                if (disTemp < retDis) {
                    retRoom = room;
                    retDis = disTemp;
                }
            }
        }
        return retRoom;
    }

    /* Get full neighborhood */
    public static ArrayList<Position> getNeighbor(Position[][] world, Position p) {
        ArrayList<Position> nei = new ArrayList<Position>();
        int xPos = p.x;
        int yPos = p.y;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (positionIsValid(xPos+x, yPos+y)) {
                    nei.add(world[xPos+x][yPos+y]);
                }
            }
        }
        return nei;
    }

    /* Get four direction neighborhood */
    public static ArrayList<Position> getFourNeighbor(Position[][] world, Position p) {
        ArrayList<Position> nei = new ArrayList<Position>();
        int xPos = p.x;
        int yPos = p.y;
        if (positionIsValid(xPos+1, yPos+1)) nei.add(world[xPos+1][yPos+1]);
        if (positionIsValid(xPos+1, yPos)) nei.add(world[xPos+1][yPos]);
        if (positionIsValid(xPos, yPos+1)) nei.add(world[xPos][yPos+1]);
        if (positionIsValid(xPos, yPos)) nei.add(world[xPos][yPos]);

        return nei;
    }

    /* Whether given position is available */
    public static boolean positionIsValid(int x, int y) { return ((x) < WIDTH && (y) < HEIGHT && (x) >= 0 && (y) >= 0); }

    /* Add wall tile on the map */
    public static void addWall(ArrayList<Position> neighborList) {
        for (Position nei : neighborList) {
            if (nei.getTType() != Tileset.FLOOR) {
                nei.setTType(Tileset.WALL);
            }
        }
    }

    /* Draw path between two rooms */
    public static void drawPath(Position[][] world, Room startRoom, Room endRoom) {
        Position startP = startRoom.randomEdge();
        Position endP = endRoom.randomEdge();
        int startX = Math.min(startP.x, endP.x);
        int endX = Math.max(startP.x, endP.x);
        int startY = Math.min(startP.y, endP.y);
        int endY = Math.max(startP.y, endP.y);
        int pathOption = (int) (Math.random()*2);
        drawPathCast(world, startX, endX, startY, endY, pathOption);
        startRoom.setConnected();
    }

    /* Actually draw the path */
    public static void drawPathCast(Position[][] world, int startX, int endX, int startY, int endY, int option) {
        if (option == 0) {
            /* v -> h */
            vPath(world, startX, startY, endY);
            vPath(world, endX, startY, endY);
            hPath(world, startY, startX, endX);
            hPath(world, endY, startX, endX);
        } else {
            /* h -> v */
            hPath(world, startY, startX, endX);
            hPath(world, endY, startX, endX);
            vPath(world, startX, startY, endY);
            vPath(world, endX, startY, endY);
        }
    }

    /* path for xDisMin == 0 */
    public static void vPath(Position[][] world, int x, int startY, int endY) {
        for (int y = startY; y <= endY; y++) {
            world[x][y].setTType(Tileset.FLOOR);
        }
    }

    /* path for yDisMin == 0 */
    public static void hPath(Position[][] world, int y, int startX, int endX) {
        for (int x = startX; x <= endX; x++) {
            world[x][y].setTType(Tileset.FLOOR);
        }
    }

    /* Calculate the distance between two room */
    public static double roomDistance(Room start, Room end) {
        ArrayList<Position> startEdges = start.getEdges();
        ArrayList<Position> endEdges = end.getEdges();
        double retDis = 10000.0;

        for (Position p1 : startEdges) {
            for (Position p2 : endEdges) {
                double tempDis = l2distance(p1, p2);
                if (tempDis < retDis) {
                    retDis = tempDis;
                }
            }
        }
        return retDis;
    }

    /* Calculate the l2 distance between two position */
    public static double l2distance(Position p1, Position p2) {
        double x_sig = (double) (p1.x - p2.x);
        double y_sig = (double) (p1.y - p2.y);

        return Math.pow((x_sig * x_sig + y_sig * y_sig), 0.5);
    }

    /* Return position info of the world */
    public static Position[][] initPosition(TETile[][] world) {
        Position[][] returnWorld = new Position[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                returnWorld[x][y] = new Position(x, y, Tileset.NOTHING);
            }
        }
        return returnWorld;
    }

    /* Fetch detailed world information of the world */
    public static void fetchPositionInfo(Position[][] worldP, TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = worldP[x][y].getTType();
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        addHexagon(world);
        ter.renderFrame(world);
    }
}
