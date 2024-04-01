package common.ui;

import java.util.stream.Stream;
import java.util.HashMap;
import java.util.function.Supplier;

import common.util.Predicates;
import common.util.StringUtils;

public abstract class Page<C> {
    private final HashMap<String, Supplier<Page<?>>> buttons = new HashMap<>();
    public final String name;
    protected C content;
    public final String footer;

    public Page(String name, String footer) {
        if (name == null)
            throw new IllegalArgumentException("Page Name cannot be Null");
        this.name = name;
        this.footer = footer;
    }

    public final String baseHeader() {
        return StringUtils.join("[", "|", "]", buttons.keySet().stream()) + " " + name;
    }

    public String pageHeader() {
        return baseHeader();
    }

    public final Object getContent() {
        return content;
    }

    public String pageString() {
        return StringUtils.join("\n\n", Stream.of(pageHeader(), content.toString(), footer).filter(Predicates.NOT_NULL));
    }

    @Override
    public String toString() {
        return pageString();
    }

    // Button methods

    public final void addButton(String buttonText, Supplier<Page<?>> pageSupplier) {
        buttons.put(buttonText, pageSupplier);
    }

    public static String EXIT_STRING = "Exit";
    public static Supplier<Page<?>> EXIT_FUNC = () -> null;

    public final void addExitButton() {
        addButton(EXIT_STRING, EXIT_FUNC);
    }

    public static class Single<C> extends Page<C> {
        public Single(String name, C content, String footer) {
            super(name, footer);
            if (content == null)
                throw new IllegalArgumentException("Page Content cannot be Null");
            this.content = content;
        }
    }

}
