package directory;

import java.util.HashMap;
import java.util.Map;

public class TestNode implements DirNode<TestNode> {

    private Map<String, TestNode> map;
    private String dir;
    private int value;

    public TestNode(String str, int val) {
        map = new HashMap<>();
        dir = str;
        value = val;
    }

    @Override
    public String dir() {
        return dir;
    }

    @Override
    public String entry() {
        return dir + " - " + value;
    }

    @Override
    public Map<String, TestNode> children() {
        return map;
    }

    @Override
    public String dirPage() {
        return dir + "\n" + value;
    }

    @Override
    public String title() {
        return dir;
    }

}
