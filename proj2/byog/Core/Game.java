package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.TERenderer;
import byog.TileEngine.Tileset;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 81;
    public static final int HEIGHT = 31;
    public int roomNum;
    public int hallwayNum;
    private Queue<Room> roomlist;
    private TETile[][] worldFrame;
    private Random rd;
    public Game() {
        roomlist = new PriorityQueue<>(cmp);
        worldFrame = new TETile[WIDTH][HEIGHT];
    }
    static Comparator<Room> cmp = new Comparator<Room>() {
        public int compare(Room e1, Room e2) {
            return e1.coor().getX() - e2.coor().getX();
        }
    };
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
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        long seed = getSeed(input);
        rd = new Random(seed);
        ter.initialize(WIDTH, HEIGHT);
        generateWorld();
        ter.renderFrame(worldFrame);
        return worldFrame;
    }
    public long getSeed(String input) {
        try {
            String pattern = "N(\\d+)S";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (matcher.find()) {
                return Long.parseLong(matcher.group(1));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        return 0;
    }
    public void generateWorld() {
        init();
        roomlist = generateRoomList();
        roomPrint();
        generateHallways();
        generateDoor();
    }
    public void generateHallways() {
        int len = roomlist.size();
        Queue<Room> origin = new PriorityQueue<>(cmp);
        Room preRoom = roomlist.poll();
        Room curRoom;
        origin.add(preRoom);
        while (!roomlist.isEmpty()) {
            curRoom = roomlist.poll();
            connectRooms(curRoom, preRoom);
            origin.add(curRoom);
            preRoom = curRoom;
        }
        roomlist = origin;

    }
    public void generateDoor() {
        for (int i = 1; i < worldFrame.length - 1; i++) {
            for (int j = 1; j < worldFrame[0].length - 1; j++) {
                if (worldFrame[i + 1][j] == Tileset.FLOOR && worldFrame[i - 1][j] == Tileset.FLOOR
                        && worldFrame[i][j + 1] == Tileset.WALL && worldFrame[i][j - 1] == Tileset.WALL) {
                    worldFrame[i][j] = Tileset.LOCKED_DOOR;
                    return;
                }
                if (worldFrame[i + 1][j] == Tileset.WALL && worldFrame[i - 1][j] == Tileset.WALL
                        && worldFrame[i][j + 1] == Tileset.FLOOR && worldFrame[i][j - 1] == Tileset.FLOOR) {
                    worldFrame[i][j] = Tileset.LOCKED_DOOR;
                    return;
                }
            }
        }
    }
    public void connectRooms(Room a, Room b) {
        TETile[][] t = worldFrame;
        Coor ca = a.getRandomCoor(rd), cb = b.getRandomCoor(rd);
        while (ca.getX() == cb.getX() || ca.getY() == cb.getY()) {
            ca = a.getRandomCoor(rd);
            cb = b.getRandomCoor(rd);
        }
        if (ca.getX() > cb.getX()) {
            Coor tmp = ca;
            ca = cb;
            cb = tmp;
        }
        int y = ca.getY();
        int x;
        for (x = ca.getX(); x <= cb.getX(); x++) {
            t[x][y] = Tileset.FLOOR;
            if (t[x][y + 1] == Tileset.NOTHING) {
                t[x][y + 1] = Tileset.WALL;
            }
            if (t[x][y - 1] == Tileset.NOTHING) {
                t[x][y - 1] = Tileset.WALL;
            }
        }
        Coor corner = new Coor(x, y);
        for (y = Integer.min(ca.getY(), cb.getY()); y <= Integer.max(ca.getY(), cb.getY()); y++) {
            t[x][y] = Tileset.FLOOR;
            if (t[x + 1][y] == Tileset.NOTHING) {
                t[x + 1][y] = Tileset.WALL;
            }
            if (t[x - 1][y] == Tileset.NOTHING) {
                t[x - 1][y] = Tileset.WALL;
            }
        }
        x = corner.getX();
        y = corner.getY();
        if (ca.getY() > cb.getY()) {
            t[x - 1][y] = Tileset.FLOOR;
            t[x][y - 1] = Tileset.FLOOR;
            if (t[x + 1][y + 1] == Tileset.NOTHING) {
                t[x + 1][y + 1] = Tileset.WALL;
            }
            if (t[x][y + 1] == Tileset.NOTHING) {
                t[x][y + 1] = Tileset.WALL;
            }
        } else {
            t[x - 1][y] = Tileset.FLOOR;
            t[x][y + 1] = Tileset.FLOOR;
            if (t[x + 1][y - 1] == Tileset.NOTHING) {
                t[x + 1][y - 1] = Tileset.WALL;
            }
            if (t[x][y - 1] == Tileset.NOTHING) {
                t[x][y - 1] = Tileset.WALL;
            }
        }
    }
    public Queue<Room> generateRoomList() {
        roomNum = rd.nextInt(5) + 10;
        for (int i = 1; i <= roomNum; i++) {
            Room rm = randomRoom();
            roomlist.add(rm);
        }
        return roomlist;
    }

    //print the room to the TETile[][]
    public void roomPrint() {
        for (Room room : roomlist) {
            room.print(worldFrame);
        }
    }

    //init the TETile[][]
    public void init() {
        for (int i = 0; i < worldFrame.length; i++) {
            for (int j = 0; j < worldFrame[0].length; j++) {
                worldFrame[i][j] = Tileset.NOTHING;
            }
        }
    }

    public Room randomRoom() {
        int width = rd.nextInt(10) + 10;
        int height = rd.nextInt(7) + 6;
        int x = rd.nextInt(WIDTH - width + 1);
        int y = rd.nextInt(HEIGHT - height + 1);
        return new Room(height, width, new Coor(x, y));
    }

    public TETile getFromCoor(Coor c) {
        return worldFrame[c.getX()][c.getY()];
    }
}
