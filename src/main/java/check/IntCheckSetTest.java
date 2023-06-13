package check;

public class IntCheckSetTest {

    public void testCount(){
        IntCheckSet<String> stringChecks = new IntCheckSet.Max<String>(str-> str.length());
    }
    
}
