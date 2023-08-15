import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    static CharacterComparator ofn = new OffByN(5);
    @Test
    public void TestOffByOne() {
        assertTrue(ofn.equalChars('a', 'f'));
        assertTrue(ofn.equalChars('f', 'a'));
        assertFalse(ofn.equalChars('h', 'f'));
    }
}
