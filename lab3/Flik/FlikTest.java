import org.junit.Assert;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int a = 1;
        int b = 1;
        Assert.assertTrue(Flik.isSameNumber(a,b));
        b = 2;
        Assert.assertFalse(Flik.isSameNumber(a,b));
        a = 128;
        b = 128;
        Assert.assertTrue(Flik.isSameNumber(a,b));
    }
}
