package common.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.util.StringUtils;

public abstract class Book extends Page {
    protected int tabIndex;

    private Book(String name, String footer) {
        super(name, footer);
    }

    @Override
    public String header() {
        return name + " - " + currentTab();
    }

    public abstract String[] tabNames();

    public abstract int numTabs();

    public abstract String currentTab();

    public abstract Stream<CharSequence> tabStrings();

    private static String tabString(String tabName, Object content) {
        return "- " + tabName + " -\n" + content;
    }

    public abstract Object getContent(String tabName);

    public abstract Object getContent(int tabIndex);

    public abstract void loadContent(String tabName);

    public void loadContent(int tabIndex) {
        this.tabIndex = tabIndex;
        content = getContent(tabIndex);
    }

    public void loadPrev() {
        loadContent(tabIndex == 0 ? numTabs() - 1 : tabIndex - 1);
    }

    public void loadNext() {
        loadContent(tabIndex == numTabs() - 1 ? 0 : tabIndex + 1);
    }

    @Override
    public String toString() {
        return StringUtils.join(header(), "\n\n", footer, tabStrings());
    }

    public static class Array extends Book {
        private final Object[] contents;

        public Array(String name, String footer, Object... contents) {
            super(name, footer);
            this.contents = contents;
            loadContent(0);
        }

        @Override
        public Object getContent(String tabName) {
            return getContent(Integer.valueOf(tabName));
        }

        @Override
        public Object getContent(int tabIndex) {
            return contents[tabIndex];
        }

        @Override
        public void loadContent(String tabName) {
            loadContent(Integer.valueOf(tabName));
        }

        @Override
        public String currentTab() {
            return String.valueOf(tabIndex);
        }

        @Override
        public String[] tabNames() {
            return IntStream.range(0, contents.length).mapToObj(String::valueOf).toArray(String[]::new);
        }

        @Override
        public int numTabs() {
            return contents.length;
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return IntStream.range(0, contents.length).mapToObj(i -> tabString(String.valueOf(i), contents[i]));
        }
    }

    public static class Mapped extends Book {
        protected final HashMap<String, Object> map;
        private String[] tabs;

        public Mapped(String name, String footer, Map<String, Object> map) {
            super(name, footer);
            this.map = map instanceof HashMap ? (HashMap<String, Object>) map : new HashMap<>(map);
        }

        public Mapped(String name, String footer, Map<String, Object> map, String startingTab) {
            this(name, footer, map);
            loadContent(startingTab);
        }

        @Override
        public Object getContent(String tab) {
            return map.get(tab);
        }

        @Override
        public Object getContent(int tabIndex) {
            return getContent(tabNames()[tabIndex]);
        }

        @Override
        public void loadContent(String tabName) {
            tabIndex = Arrays.asList(tabNames()).indexOf(tabName);
            content = getContent(tabName);
        }

        @Override
        public String[] tabNames() {
            if (tabs == null)
                tabs = map.keySet().stream().toArray(String[]::new);
            return tabs;
        }

        @Override
        public int numTabs() {
            return tabNames().length;
        }

        @Override
        public String currentTab() {
            return tabNames()[tabIndex];
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return map.entrySet().stream().map(e -> tabString(e.getKey(), e.getValue()));
        }

        public Object put(String tab, Object content) {
            tabs = null;
            return map.put(tab, content);
        }

        public Object remove(String tab) {
            tabs = null;
            return map.remove(tab);
        }
    }

}
