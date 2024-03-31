package common.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.util.StringUtils;

public abstract class Book extends Page {

    private Book(String name, String footer) {
        super(name, footer);
    }

    @Override
    public String header() {
        return name + " - " + currentTab();
    }

    abstract public String currentTab();

    abstract public String[] tabNames();

    abstract public Stream<CharSequence> tabStrings();

    private static String tabString(String tabName, Object content) {
        return "- " + tabName + " -\n" + content;
    }

    @Override
    public String toString() {
        return StringUtils.join(header(), "\n\n", footer, tabStrings());
    }

    public static class Array extends Book {
        private final Object[] contents;
        private int index;

        public Array(String name, String footer, Object... contents) {
            super(name, footer);
            this.contents = contents;
            loadContent(0);
        }

        public Object getContent(int index) {
            return contents[index];
        }

        public void loadContent(int index) {
            this.index = index;
            content = getContent(index);
        }

        @Override
        public String currentTab() {
            return String.valueOf(index);
        }

        @Override
        public String[] tabNames() {
            return IntStream.range(0, contents.length).mapToObj(String::valueOf).toArray(String[]::new);
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return IntStream.range(0, contents.length).mapToObj(i -> tabString(String.valueOf(i), contents[i]));
        }
    }

    public static class Mapped extends Book {
        public final HashMap<String, Object> map;
        private String current;

        public Mapped(String name, String footer, Map<String, Object> map) {
            super(name, footer);
            this.map = map instanceof HashMap ? (HashMap<String, Object>) map : new HashMap<>(map);
        }

        public Mapped(String name, String footer, Map<String, Object> map, String startingTab) {
            this(name, footer, map);
            loadContent(startingTab);
        }

        public Object getContent(String tab) {
            return map.get(tab);
        }

        public void loadContent(String tab) {
            current = tab;
            content = getContent(tab);
        }

        @Override
        public String currentTab() {
            return current;
        }

        @Override
        public String[] tabNames() {
            return map.keySet().stream().toArray(String[]::new);
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return map.entrySet().stream().map(e -> tabString(e.getKey(), e.getValue()));
        }

        public Object put(String tab, Object content) {
            return map.put(tab, content);
        }

        public Object remove(String tab) {
            return map.remove(tab);
        }
    }

}
