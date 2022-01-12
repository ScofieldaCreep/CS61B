import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {
    static CharacterComparator offByN;

    @Test
    public void testEqualChars() {
        OffByN offByN = new OffByN(5);
        assertFalse(offByN.equalChars('a', 'h'));
        assertFalse(offByN.equalChars('*', 'a'));
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('0', '5'));

    }
}
