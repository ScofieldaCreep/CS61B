package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class HexagonDemo {
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

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi+= 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }

        for (int yi = 0; yi < 2 * s; yi++) {
            int thisRowY = p.y + yi;
            int thisRowX = p.x + hexRowOffset(s, yi);
            Position start = new Position(thisRowX, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, start, rowWidth, t);
        }
    }


    public static void main(String[] args) {
        TERenderer demo = new TERenderer();
        demo.initialize(50, 50);

        TETile[][] world = new TETile[50][50];
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                world[x][y] = Tileset.FLOWER;
            }
        }
        Position p = new Position(25, 25);
        addHexagon(world, p, 5, Tileset.WALL);

        demo.renderFrame(world);
    }
}
