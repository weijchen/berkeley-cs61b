package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.imageio.plugins.tiff.TIFFDirectory;
import java.util.Random;
import java.util.Scanner;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 29;
    private static final int HEIGHT = 30;

    private static Random generator;

//    private static long SEED;
//    private static Random RANDOM = new Random(SEED);

    // properties for each hexagon
    private static int minWidth;
    private static int maxWidth;
    private static int maxWhiteSpace;
    private static int size;

    private static int startX;
    private static int startY;

    /* Determine which type of grid to print */
    public static TETile gridType() {
        int type = generator.nextInt(7);
        switch (type) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.PLAYER;
            case 2: return Tileset.FLOWER;
            case 3: return Tileset.WALL;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.FLOOR;
            case 6: return Tileset.SAND;
            default: return Tileset.TREE;
        }
    }

    /* Create a hexagon of size s and determine the area print */
    public static void addHexagon(TETile[][] world) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at lear size 2");
        }

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        int posX = startX;
        int posY = startY;
        int offX = size + 2;
        int offYUp   = size * 2;
        int offYEmpty = size;

        /* Left part of the HexWorld */
        for (int col = 0; col < 3; col++) {
            int numOfHexagon = col + 3;
            int numOfEmptyHexagon = 5 - numOfHexagon;
            for (int i = 0; i < numOfEmptyHexagon; i++) {
                posY += offYEmpty;
            }
            for (int i = 0; i < numOfHexagon; i++) {
                TETile gType = gridType();
                drawHexagon(world, posX, posY, gType);
                posY += offYUp;
            }
            posX += offX;
            posY = startY;
        }

        /* Right part of the HexWorld */
        for (int col = 1; col > -1; col--) {
            int numOfHexagon = col + 3;
            int numOfEmptyHexagon = 5 - numOfHexagon;
            for (int i = 0; i < numOfEmptyHexagon; i++) {
                posY += offYEmpty;
            }
            for (int i = 0; i < numOfHexagon; i++) {
                TETile gType = gridType();
                drawHexagon(world, posX, posY, gType);
                posY += offYUp;
            }
            posX += offX;
            posY = startY;
        }
    }

    /* Draw the actual hexagon */
    public static void drawHexagon(TETile[][] world, int posX, int posY, TETile type) {

        /* TODO: draw a hexagon with size s */
        int numberOfSpace = maxWhiteSpace;
        int numberOfGrid = minWidth;

        for (int y = posY; y < posY + size; y++) {
            printLine(world, posX, y, type, numberOfSpace, numberOfGrid);
            numberOfSpace -= 1;
            numberOfGrid += 2;
        }

        numberOfSpace += 1;
        numberOfGrid -= 2;

        for (int y = posY + size; y < posY + 2*size; y++) {
            printLine(world, posX, y, type, numberOfSpace, numberOfGrid);
            numberOfSpace += 1;
            numberOfGrid -= 2;
        }
    }

    /* Print each line of the hexagon (no need to print the NOTHING grid */
    public static void printLine(TETile[][] world, int posX, int posY, TETile type, int numOfSpace, int numOfGrid) {
        for (int i = numOfSpace; i < numOfSpace + numOfGrid; i++) {
            world[posX + i][posY] = type;
        }
    }

    public static void main (String[]args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the random seed > ");
        generator = new Random(Integer.parseInt(in.nextLine()));

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] worldTETile = new TETile[WIDTH][HEIGHT];

        // Initial size parameter
        size = 3;

        // parameters for building the hexagon world
        minWidth = size;
        maxWidth = minWidth + 2 * (size - 1);
        maxWhiteSpace = (maxWidth - minWidth) / 2;
        startX = 1;
        startY = 0;

        addHexagon(worldTETile);
        ter.renderFrame(worldTETile);
    }
}


/* printLine for the hexagon (Recursively) */
    /* public static void printLine(TETile[][] world, int posX, int posY, TETile type, int posYMax) {
        if (posY == posYMax) {
            return;
        }
        for (int space = 0; space < maxWhiteSpace; space--) {
            world[posX+space][posY] = Tileset.NOTHING;
        }

        printGrid()

        for (int x = posX; x < posX+maxWidth; x++) {
            world[x][posY] = type;
        }
    } */