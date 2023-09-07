package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {

    private int height;
    private int width;
    private Coor coor;
    public Room(int h, int w, Coor c) {
        height = h;
        width = w;
        coor = c;
    }
    public Coor getRightTop() {
        return new Coor(coor.getX() + width - 1, coor.getY() + height - 1);
    }
    public void print(TETile[][] t) {
        for (int xx = coor.getX(); xx <= coor.getX() + width - 1; xx++) {
            for (int yy = coor.getY(); yy <= coor.getY() + height - 1; yy++) {
                if (xx == coor.getX() || xx == coor.getX() + width - 1) {
                    if (t[xx][yy] == Tileset.NOTHING) {
                        t[xx][yy] = Tileset.WALL;
                    }
                } else {
                    if (yy == coor.getY() || yy == coor.getY() + height - 1) {
                        if (t[xx][yy] == Tileset.NOTHING) {
                            t[xx][yy] = Tileset.WALL;
                        }
                    } else {
                        t[xx][yy] = Tileset.FLOOR;
                    }
                }
            }
        }
    }
    public Coor coor() {
        return coor;
    }
    public Coor getRandomCoor(Random a) {
        int x = coor.getX();
        int y = coor.getY();
        return Coor.randomCoor(x + 1, x + width - 2, y + 1, y + height - 2, a);
    }
}
