package directory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DirNodeTest {

    private TestNode node;

    @BeforeEach
    public void setUp() {
        node = new TestNode("root", 0);
    }

    @Test
    public void testPut() {
        TestNode newNode = new TestNode("test", 3);
        node.put(newNode);
        assertEquals(newNode, node.children().get("test"));
    }

    @Test
    public void testIsLeaf() {
        assertTrue(node.isLeaf());
        node.put(new TestNode("test", 3));
        assertFalse(node.isLeaf());
    }

    @Test
    public void testDump(){
        System.out.println(node.dump());
    }

}
