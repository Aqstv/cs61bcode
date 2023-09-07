package byog.Core;

import byog.TileEngine.TETile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class TestGame {
    Game game = new Game();
    @Test
    public void testGetSeed() {
        String s = "N123567SDASAS";
        long expected = 123567;
        long actual = game.getSeed(s);
        Assert.assertEquals(expected, actual);
    }
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] worldState = game.playWithInputString("N136534S");
        System.out.println(TETile.toString(worldState));
    }
}
