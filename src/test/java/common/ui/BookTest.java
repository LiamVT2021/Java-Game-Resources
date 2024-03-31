package common.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {
    private Book.Array<String> arrBook;
    private Book.Mapped<String> mapBook;

    @BeforeEach
    public void setUp() {
        arrBook = new Book.Array<>("Array", null, "Page 1", "Page 2", "Page 3");
        mapBook = new Book.Mapped<>("Food Map", "Eat Healthy", "Meats", Map.of(
                "Fruits", "Apple, Orange, Bannana, Kiwi",
                "Veggies", "Letuce, Kale, Celery, Carrot",
                "Meats", "Chicken, Beef, Pork"));
    }

    @Test
    public void testStrings() {
        assertEquals("Array - 1\n\nPage 1", arrBook.pageString());
        assertEquals("Food Map - Meats\n\nChicken, Beef, Pork\n\nEat Healthy", mapBook.pageString());
        assertEquals("Array\n\n"
                + "- 1 -\nPage 1\n\n"
                + "- 2 -\nPage 2\n\n"
                + "- 3 -\nPage 3",
                arrBook.toString());
        String[] tabs = mapBook.tabNames();
        Map<String, String> map = Map.of(
                "Fruits", "- Fruits -\nApple, Orange, Bannana, Kiwi\n\n",
                "Veggies", "- Veggies -\nLetuce, Kale, Celery, Carrot\n\n",
                "Meats", "- Meats -\nChicken, Beef, Pork\n\n");
        assertEquals("Food Map\n\n"
                + map.get(tabs[0])
                + map.get(tabs[1])
                + map.get(tabs[2])
                + "Eat Healthy",
                mapBook.toString());
    }

    @Test
    public void testNav() {
        // Start
        assertEquals("Page 1", arrBook.getContent());
        assertEquals("Chicken, Beef, Pork", mapBook.getContent());
        // Next
        assertEquals("Page 2", arrBook.loadNext());
        assertEquals("Page 3", arrBook.loadNext());
        assertEquals("Page 1", arrBook.loadNext());
        // Prev
        assertEquals("Page 3", arrBook.loadPrev());
        assertEquals("Page 2", arrBook.loadPrev());
        assertEquals("Page 1", arrBook.loadPrev());
        // Search
        assertEquals("Page 3", arrBook.loadContent(3));
        assertEquals("Page 3", arrBook.loadContent("3"));
        assertEquals("Apple, Orange, Bannana, Kiwi", mapBook.loadContent("Fruits"));
    }

}
