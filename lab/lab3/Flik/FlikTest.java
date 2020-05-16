import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    /* After the number exceed 127 (= 2*7), Integer object can no longer determine the value */
    @Test
    public void testFilk() {
        int a = 127;
        int b = 127;
        int c = 129;
        int d = 129;


        boolean exp1 = true;
        assertEquals(exp1, Flik.isSameNumber(a, b));
        boolean exp2 = true;
        assertNotEquals(exp2, Flik.isSameNumber(c, d));
    }
}
