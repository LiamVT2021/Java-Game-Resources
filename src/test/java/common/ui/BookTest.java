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
        mapBook = new Book.Mapped<>("Food Map", "Eat Healthy", Map.of(
                "Fruits", "Apple, Orange, Bannana, Kiwi",
                "Veggies", "Letuce, Kale, Celery, Carrot",
                "Meats", "Chicken, Beef, Pork"),
                "Meats", "Veggies", "Fruits");
        mapBook.addExitButton();
    }

    @Test
    public void testPageString() {
        assertEquals("[] Array - 1\n[ 1 | 2 | 3 ]\n\nPage 1", arrBook.pageString());
        assertEquals("[Exit] Food Map - Meats\n[ Meats | Veggies | Fruits ]\n\nChicken, Beef, Pork\n\nEat Healthy",
                mapBook.pageString());
    }

    @Test
    public void testToString() {
        assertEquals("[] Array\n\n"
                + "- 1 -\nPage 1\n\n"
                + "- 2 -\nPage 2\n\n"
                + "- 3 -\nPage 3",
                arrBook.toString());
        assertEquals("[Exit] Food Map\n\n"
                + "- Meats -\nChicken, Beef, Pork\n\n"
                + "- Veggies -\nLetuce, Kale, Celery, Carrot\n\n"
                + "- Fruits -\nApple, Orange, Bannana, Kiwi\n\n"
                + "Eat Healthy",
                mapBook.toString());
    }

    @Test
    public void testGetContent() {
        assertEquals("Page 1", arrBook.getContent());
        assertEquals("Chicken, Beef, Pork", mapBook.getContent());
    }

    @Test
    public void testNext() {
        assertEquals("Page 2", arrBook.loadNext());
        assertEquals("Page 3", arrBook.loadNext());
        assertEquals("Page 1", arrBook.loadNext());
        assertEquals("Letuce, Kale, Celery, Carrot", mapBook.loadNext());
        assertEquals("Apple, Orange, Bannana, Kiwi", mapBook.loadNext());
        assertEquals("Chicken, Beef, Pork", mapBook.loadNext());
    }

    @Test
    public void testPrev() {
        assertEquals("Page 3", arrBook.loadPrev());
        assertEquals("Page 2", arrBook.loadPrev());
        assertEquals("Page 1", arrBook.loadPrev());
        assertEquals("Apple, Orange, Bannana, Kiwi", mapBook.loadPrev());
        assertEquals("Letuce, Kale, Celery, Carrot", mapBook.loadPrev());
        assertEquals("Chicken, Beef, Pork", mapBook.loadPrev());
    }

    @Test
    public void testLoadIndex() {
        assertEquals("Page 3", arrBook.loadContent(3));
        assertEquals("Letuce, Kale, Celery, Carrot", mapBook.loadContent(2));
    }

    @Test
    public void testLoadName() {
        assertEquals("Page 2", arrBook.loadContent("2"));
        assertEquals("Apple, Orange, Bannana, Kiwi", mapBook.loadContent("Fruits"));
    }

    // @Test
    // public void testAdd() {
    // mapBook = new Book.Mapped<>("Builder", null);
    // // TODO build
    // }

}
