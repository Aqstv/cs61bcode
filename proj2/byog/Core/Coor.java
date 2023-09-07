package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class Coor {
    private int x;
    private int y;

    public Coor(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public Coor(Coor c) {
        x = c.x;
        y = c.y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Coor getCoor(int dx, int dy) {
        return new Coor(x + dx, y + dy);
    }
    public boolean in(TETile[][] t) {
        return (x >= 0 && x < t.length && y >= 0 && y < t[0].length);
    }
    public static Coor randomCoor(int minX, int maxX, int minY, int maxY, Random rd) {
        return new Coor(rd.nextInt(maxX + 1 - minX) + minX, rd.nextInt(maxY + 1 - minY) + minY);
    }
}
