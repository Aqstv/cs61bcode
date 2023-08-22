package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void test() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<String>(3);
        arb.enqueue("hello");
        arb.enqueue("the");
        arb.enqueue("world");
        assertEquals(arb.peek(), "hello");
        assertEquals(3, arb.fillCount());
        assertEquals(3, arb.capacity());
        assertEquals(arb.dequeue(), "hello");
        assertEquals(2, arb.fillCount());
        arb.dequeue();
        arb.enqueue("hello");
        arb.enqueue("the");
        assertEquals(arb.dequeue(), "world");
        assertEquals(arb.dequeue(), "hello");
        assertEquals(arb.dequeue(), "the");
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
