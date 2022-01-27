package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 949339;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
        private int x;
        private int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, HexWorld.Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi+= 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    public static void addHexagon(TETile[][] world, HexWorld.Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }

        for (int yi = 0; yi < 2 * s; yi++) {
            int thisRowY = p.y + yi;
            int thisRowX = p.x + hexRowOffset(s, yi);
            HexWorld.Position start = new HexWorld.Position(thisRowX, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, start, rowWidth, t);
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position topP, int s, TETile t, int num) {
        for (int col = 1; col <= num; col++) {
            Position p_thisCol = topPositionCalc(topP, col);
            drawSingleCol(world, p_thisCol, s, t, col);
        }
    }

    public static void drawSingleCol(TETile[][] world, Position p, int s, TETile t, int col) {
        int height = 2 * s;
        int absCol = col - 3;
        for (int i = 0; i < 5 - Math.abs(absCol); i++) {
            addHexagon(world, p, s, t);
            p.y -= height;
        }
    }

    public static Position topPositionCalc(Position topP, int col) {
        int absCol = col - 3;
        int x = topP.x + absCol * 5;
        int y = topP.y - Math.abs(absCol) * 3;
        Position res = new Position(x, y);
        return res;
    }


    public static void main(String[] args) {
        TERenderer hexWorld = new TERenderer();
        hexWorld.initialize(50, 50);

        TETile[][] world = new TETile[50][50];
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                world[x][y] = Tileset.FLOWER;
            }
        }
        HexWorld.Position p = new HexWorld.Position(25, 25);
        drawRandomVerticalHexes(world, p, 3, Tileset.WALL, 5);

        hexWorld.renderFrame(world);
    }
}


/*
        Third: Stepping back, I decided to try to find the “nicest” way to try to lay out my hexagons and was ready to
        throw away my bottomRight and topRight methods entirely.
        Then the key AHA moment occurred when I noticed that the world consists of 5 columns of 3, 4, 5, 4, and 3 hexagons, respectively.

        Fourth: I wrote a method called drawRandomVerticalHexes that draws a column of N hexes, each one with a random biome.

        Fifth: I wrote a main method that is a little ugly, but basically calls drawRandomVerticalHexes five times,
        one for each of the five columns of the world, consisting of 3, 4, 5, 4, and 3 hexagons.
        To figure out the starting position for the “top” hex of each column, I used the topRightNeighbor or bottomRightNeighbor
        on the old top, as appropriate.

        Sixth: I added some code to allow for interactivity so you can press a single key to get a new random world and
        enjoyed playing around with it. But interactivity will have to wait until next week’s lab for you guys.
*/
