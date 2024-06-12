package common.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @version 3/31/24
 */
public class PageTest {

    @Test
    public void testGetters() {
        String str = "String";
        Page<String> page = new Page.Single<>(str, str, null);
        assertTrue(str == page.getContent());
    }

    @Test
    public void testToString() {
        Page<String> page = new Page.Single<>("Name", "Contents\nOf\nPage", "Footer").withExitButton();
        assertEquals("[Exit] Name\n\nContents\nOf\nPage\n\nFooter", page.toString());
        assertEquals("[] Short\n\nPage\nContent",
                new Page.Single<>("Short", "Page\nContent", null).toString());
    }

    @Test
    public void testThrows() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Page.Single<>(null, null, null));
        assertEquals("Page Name cannot be Null", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class,
                () -> new Page.Single<>("Name", null, null));
        assertEquals("Page Content cannot be Null", thrown.getMessage());
    }

    @Test
    public void testButtons() {
        Page<String> page = new Page.Single<>("Name", "Contents", "Footer");
        page.withNavButton("This", () -> page).withExitButton();
        assertEquals("[This|Exit] Name", page.pageHeader());
        assertNull(page.clickNavButton(Page.EXIT_STRING));
        assertEquals(page, page.clickNavButton("This"));
    }

}
