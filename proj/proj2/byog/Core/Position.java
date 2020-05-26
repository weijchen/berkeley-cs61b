package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Position implements Serializable {
    protected int x;
    protected int y;
    public boolean passable = true;
    private TETile tType = Tileset.NOTHING;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, TETile tType) {
        this(x, y);
        this.tType = tType;
        if (tType == Tileset.WALL) {
            passable = false;
        }
    }

    public void setTType(TETile tType) {
        this.tType = tType;
        if (tType == Tileset.WALL) {
            passable = false;
        }
    }

    public TETile getTType() {
        return tType;
    }
}