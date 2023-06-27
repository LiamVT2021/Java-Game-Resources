package old.enums;

import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    public void test(){
        for (Attribute att: Attribute.Type.MENTAL.getMembers())
        System.out.println(att.name(true));
    }
    
}
