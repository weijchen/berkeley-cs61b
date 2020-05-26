package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Game extends WorldGeneratorFinal {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public static final int border = 3;
    private boolean gameOver = false;
    private boolean gameSave = false;
    private static final int health = 5;
    private static TETile[][] world = new TETile[WIDTH][HEIGHT];
    private static Position[][] worldP = null;
    private static Position player = null;
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public void startGame() throws IOException, ClassNotFoundException {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT+border);
        homepage();

        String choose = readInput();
        if (choose.equals("1")) {
            System.out.println("Start a new game!");
            worldP = addHexagon(world);
            player = fetchPlayer(worldP);

        } else {
            System.out.println("Load previous game!");
            FileInputStream fis = new FileInputStream("out.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            worldP = (Position[][]) ois.readObject();

            ois.close();
            player = fetchPlayer(worldP);

            StdDraw.clear(Color.black);
            Font font = new Font("Monaco", Font.BOLD, 30);
            StdDraw.setFont(font);
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH/(float)2, HEIGHT/(float)2, "Load from saved game!");
            StdDraw.show();
            StdDraw.pause(1500);
        }
        System.out.println(player);
        fetchPositionInfo(worldP, world);
//        player = fetchPlayer(worldP);
        ter.renderFrame(world);
        displayBar();
        readAction();
    }

    public void readAction() throws IOException {
        int x = player.x;
        int y = player.y;


        while (!gameOver) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            int xOffset = 0;
            int yOffset = 0;
            char key = StdDraw.nextKeyTyped();
            if (key == 'w') yOffset = 1;
            else if (key == 'a') xOffset = -1;
            else if (key == 's') yOffset = -1;
            else if (key == 'd') xOffset = 1;
            else if (key == 'q') {
                gameSave = true;
            }

            int xNew = x + xOffset;
            int yNew = y + yOffset;

            if (gameSave) {
                FileOutputStream fos = new FileOutputStream("out.tmp");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(worldP);
                oos.close();
                System.out.println("Exit the game!");
                StdDraw.clear(Color.black);
                Font font = new Font("Monaco", Font.BOLD, 30);
                StdDraw.setFont(font);
                StdDraw.setPenColor(Color.white);
                StdDraw.text(WIDTH/(float)2, HEIGHT/(float)2, "Game Saved!");
                StdDraw.show();
                gameOver = true;
            } else {
                if (xNew >= 0 && xNew < WIDTH && yNew >= 0 && yNew < HEIGHT) {
                    if (worldP[xNew][yNew].getTType() == Tileset.FLOOR) {
                        worldP[xNew][yNew].setTType(Tileset.PLAYER);
                        worldP[x][y].setTType(Tileset.FLOOR);
                        x = xNew;
                        y = yNew;
                        fetchPositionInfo(worldP, world);
                        ter.renderFrame(world);
                    } else if (worldP[xNew][yNew].getTType() == Tileset.LOCKED_DOOR) {
                        StdDraw.clear(Color.black);
                        Font font = new Font("Monaco", Font.BOLD, 30);
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(Color.white);
                        StdDraw.text(WIDTH/(float)2, HEIGHT/(float)2, "Congratulation! You Pass!");
                        StdDraw.show();
                        gameOver = true;
                    } else {
                        System.out.println("Enter direction not passable!");

                    }
                }
            }
        }
    }

    public String readInput() {
        String input = "";
        while (input.length() < 1) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            input += String.valueOf(key);
        }
        return input;
    }

    public void homepage() {
        Font font = new Font("Monaco", Font.BOLD, 36);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH/(float)2 + 1, HEIGHT/(float)2 + 4, "Warrior! Welcome to the RandWorld");

        font = new Font("Monaco", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.red);
        StdDraw.text(WIDTH/(float)2 + 1, HEIGHT/(float)2 - 1, "(1) Start a new game or (2) Load saved game");
        StdDraw.show();
    }

    public void displayBar() {
        Font smallFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.textLeft(1, HEIGHT+border-1, "Health: " + health);
        StdDraw.textRight(WIDTH - 1, HEIGHT+border-1, ENCOURAGEMENT[health % ENCOURAGEMENT.length]);
        StdDraw.line(0, HEIGHT+border-2, WIDTH, HEIGHT+border-2);
        StdDraw.show();
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
