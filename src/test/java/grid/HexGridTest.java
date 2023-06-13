package grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HexGridTest {

    HexGrid grid = new HexGrid(5, 5, 20);

    @Test
    public void testVector() {
        assertEquals("0", grid.vector(0, 0));
        assertEquals("5x", grid.vector(5, 0));
        assertEquals("5y", grid.vector(0, -5));
        assertEquals("5z", grid.vector(-5, 5));

        assertEquals("3x - 2y", grid.vector(3, 2));
        assertEquals("3x - 2z", grid.vector(5, -2));
        assertEquals("3y - 2x", grid.vector(-2, -3));
        assertEquals("3y - 2z", grid.vector(2, -5));
        assertEquals("3z - 2x", grid.vector(-5, 3));
        assertEquals("3z - 2y", grid.vector(-3, 5));
    }

    @Test
    public void testDistance() {
        assertEquals(0, grid.distance(0, 0));
        assertEquals(5, grid.distance(5, 0));
        assertEquals(5, grid.distance(0, -5));
        assertEquals(5, grid.distance(-5, 5));

        assertEquals(5, grid.distance(3, 2));
        assertEquals(5, grid.distance(5, -2));
        assertEquals(5, grid.distance(-2, -3));
        assertEquals(5, grid.distance(2, -5));
        assertEquals(5, grid.distance(-5, 3));
        assertEquals(5, grid.distance(-3, 5));
    }

}
