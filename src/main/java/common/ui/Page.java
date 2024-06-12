package common.ui;

import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

import common.util.StringUtils;

/**
 * Displays content to the user
 * 
 * @param <C> the type of content displayed
 * @version 3/31/24
 */
public abstract class Page<C> {
    private final HashMap<String, Supplier<Page<?>>> navButtons = new HashMap<>();
    public final String name;
    protected C content;
    public final String footer;

    protected Page(String name, String footer) {
        if (name == null)
            throw new IllegalArgumentException("Page Name cannot be Null");
        this.name = name;
        this.footer = footer;
    }

    /**
     * @return a header with the format "[A|B] Name"
     */
    public final String baseHeader() {
        return StringUtils.join("[", "|", "]", navButtons.keySet().stream()) + " " + name;
    }

    /**
     * @return the header displayed above the content in pageString
     */
    public String pageHeader() {
        return baseHeader();
    }

    /**
     * @return the content in the center of this page
     */
    public Object getContent() {
        return content;
    }

    /**
     * @return a String reprenentation of what is displayed to the user
     */
    public String pageString() {
        return StringUtils.join("\n\n",
                Stream.of(pageHeader(), getContent().toString(), footer).filter(Objects::nonNull));
    }

    /**
     * @return a String reprenentation of what is displayed to the user
     */
    @Override
    public String toString() {
        return pageString();
    }

    // Button methods

    /**
     * adds a navigation button to the top left of the page
     * 
     * @param pageSupplier navigates to new page
     * @return this page
     */
    public final Page<C> withNavButton(String buttonText, Supplier<Page<?>> pageSupplier) {
        navButtons.put(buttonText, pageSupplier);
        return this;
    }

    /**
     * @return the Page supplied by clicking the button
     */
    public final Page<?> clickNavButton(String button) {
        return navButtons.get(button).get();
    }

    public static String EXIT_STRING = "Exit";
    public static Supplier<Page<?>> EXIT_FUNC = () -> null;

    /**
     * adds a exit button to the top left of the page
     * 
     * @return this Page
     */
    public final Page<C> withExitButton() {
        return withNavButton(EXIT_STRING, EXIT_FUNC);
    }

    public static class Single<C> extends Page<C> {
        /**
         * @param name    cannot be null
         * @param content cannot be null
         * @param footer
         */
        public Single(String name, C content, String footer) {
            super(name, footer);
            if (content == null)
                throw new IllegalArgumentException("Page Content cannot be Null");
            this.content = content;
        }
    }

}
