package directory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 
 * 
 * @author Liam
 * @date 4/3/2022
 */
public class DirectoryTest {

    private Directory<TestNode> directory;

    @BeforeEach
    public void setUp() {
        directory = new Directory<TestNode>(new TestNode("root", 0));
        directory.root.put(new TestNode("test", 1));
        directory.root.put(new TestNode("Test", 2));
    }

    @Test
    public void testDump() {
        System.out.println(directory.dump());
    }

}
