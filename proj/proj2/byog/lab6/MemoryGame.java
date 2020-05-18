package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Scanner;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    private static int randomSeed;

    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        Random rnd = new Random(n);
        String retString = "";
        if (n < 1)
            throw new IllegalArgumentException("Size less than 1, ");
        for (int i = 0; i < n; i++) {
            int rndID = Math.abs(rnd.nextInt() % 26);
            retString += CHARACTERS[rndID];
        }
        return retString;
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.black);
        displayBar();

        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(this.width/(float)2, this.height/(float)2, s);
        StdDraw.show();
    }

    public void drawFrame(String s, int time) {
        StdDraw.clear(Color.black);
        displayBar();

        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(this.width/(float)2, this.height/(float)2, s);
        StdDraw.show();
        StdDraw.pause(time);
    }

    public void displayBar() {
        if (!gameOver) {
            Font smallFont = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(width/(float)2 + 1, height - 1, "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);
        }
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);

        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i+1));   // draw character of letters
            StdDraw.show();
            StdDraw.pause(1000);
            drawFrame(" ");                     // draw empty space
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            input += String.valueOf(key);
            drawFrame(input);
        }
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        // Set any relevant variables before the game starts
        round = 1;

        // Establish Game loop
        while (!gameOver) {
            drawFrame("Round: " + round, 3000);

            String roundString = generateRandomString(round);
            flashSequence(roundString);
            drawFrame("Your turn!", 1000);
            String checkString = solicitNCharsInput(roundString.length());

            if (!checkString.equals(roundString)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round, 3000);
            } else {
                drawFrame("Pass! Next round.", 1500);
                round += 1;
            }
        }
    }
}
