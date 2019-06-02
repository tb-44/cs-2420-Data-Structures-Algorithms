import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Trent Bennett Test class to check the methods for Vector.java
 */

public class VectorTest {
    private final double[] data1 = { 1.0, 2.0, 3.0, 4.0 };
    private final double[] data2 = { 5.0, 2.0, 4.0, 1.0 };

    private final Vector v1 = new Vector(data1);
    private final Vector v2 = new Vector(data2);
    public final double delta = .001;

    /**
     * This method tests the component (coordinate) at index i
     */
    @Test
    public void testComponentAt() {

        double expected = 1.0;
        int i = 0;
        double actual = v1.componentAt(i);
        assertEquals(expected, actual, delta);
    }

    /**
     * This test method tests whether two vectors are added together correctly
     * (Tests the Plus method).
     */

    @Test
    public void testPlus() {
        double[] expected = { 6.0, 4.0, 7.0, 5.0 };
        Vector sum = v1.plus(v2);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sum.componentAt(i), delta);
        }
    }

    /**
     * This test method checks to see if the Runtime Exception is thrown if the
     * vectors don't match on the plus method.
     * 
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testPlusWithException() throws Exception {

        double[] expected = { 1.0, 2.0 };
        Vector test = new Vector(expected);
        Vector sum = v1.plus(test);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sum.componentAt(i), delta);

        }

    }

    /**
     * This method tests the subtraction of two vectors.
     */
    @Test
    public void testMinus() {
        double[] expected = { -4.0, 0.0, -1.0, 3.0 };
        Vector sub = v1.minus(v2);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sub.componentAt(i), delta);
        }
    }

    /**
     * This test method checks to see that the Runtime Exception is thrown if the
     * vectors do not match.
     * 
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testMinusWithException() throws Exception {
        double[] expected = { 1.0, 2.0 };
        Vector test = new Vector(expected);
        Vector sub = v1.minus(test);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sub.componentAt(i), delta);
        }

    }

    /**
     * This test method checks to see if the vectors are multiplied correctly.
     */
    @Test
    public void testTimes() {
        double[] expected = { 10.0, 20.0, 30.0, 40.0 };
        Vector mult = v1.times(10);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], mult.componentAt(i), delta);
        }
    }

    /**
     * This test method checks on the distanceTo method.
     */
    @Test
    public void testDistanceTo() {
        double expected = 5.099;
        double actual = v1.distanceTo(v2);
        double actual2 = v2.distanceTo(v1);
        assertEquals(expected, actual, delta);
        assertEquals(expected, actual2, delta);
    }

}
