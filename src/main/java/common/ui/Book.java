package common.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.util.Predicates;
import common.util.StreamUtils;
import common.util.StringUtils;

public abstract class Book<C> extends Page<C> {
    protected int tabIndex = 1;

    private Book(String name, String footer) {
        super(name, footer);
    }

    @Override
    public String pageHeader() {
        return baseHeader() + " - " + currentTab() + "\n" + StringUtils.join("[ ", " | ", " ]", Stream.of(tabNames()));
    }

    public abstract String[] tabNames();

    public abstract int numTabs();

    public abstract String currentTab();

    public abstract Stream<CharSequence> tabStrings();

    private static String tabString(String tabName, Object content) {
        return "- " + tabName + " -\n" + content;
    }

    @Override
    public Object getContent() {
        return content == null ? loadContent(1) : content;
    }

    public abstract C loadContent(int tabIndex);

    public abstract C loadContent(String tabName);

    public C loadPrev() {
        return loadContent(tabIndex == 1 ? numTabs() : tabIndex - 1);
    }

    public C loadNext() {
        return loadContent(tabIndex == numTabs() ? 1 : tabIndex + 1);
    }

    @Override
    public String toString() {
        return StringUtils.join("\n\n",
                StreamUtils.wrap(baseHeader(), tabStrings(), footer).filter(Predicates.NOT_NULL));
    }

    public static class Array<C> extends Book<C> {
        private final C[] contents;

        public Array(String name, String footer, @SuppressWarnings("unchecked") C... contents) {
            super(name, footer);
            this.contents = contents;
            loadContent(1);
        }

        public C loadContent(int tabIndex) {
            this.tabIndex = tabIndex;
            content = contents[tabIndex - 1];
            return content;
        }

        @Override
        public C loadContent(String tabName) {
            return loadContent(Integer.valueOf(tabName));
        }

        @Override
        public String currentTab() {
            return String.valueOf(tabIndex);
        }

        @Override
        public String[] tabNames() {
            return IntStream.range(1, contents.length + 1).mapToObj(String::valueOf).toArray(String[]::new);
        }

        @Override
        public int numTabs() {
            return contents.length;
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return IntStream.range(1, contents.length + 1).mapToObj(i -> tabString(String.valueOf(i), contents[i - 1]));
        }
    }

    public static class Mapped<C> extends Book<C> {
        protected final HashMap<String, C> map = new HashMap<>();
        private String[] tabs;

        public Mapped(String name, String footer) {
            super(name, footer);
        }

        // Build

        public Mapped<C> withTab(String tabName, C content) {
            if (tabName == null || content == null)
                throw new IllegalArgumentException("tabName and content cannot be null");
            if (!map.keySet().contains(tabName))
                tabs = null;
            map.put(tabName, content);
            return this;
        }

        //use null to reset order
        public Mapped<C> withTabOrder(String... tabOrder) {
            if (tabOrder != null && !Set.of(tabOrder).equals(map.keySet()))
                throw new IllegalArgumentException("tabOrder does not match set of tabs");
            tabs = tabOrder;
            return this;
        }

        // Book

        @Override
        public C loadContent(int tabIndex) {
            this.tabIndex = tabIndex;
            return setContet(tabNames()[tabIndex - 1]);
        }

        @Override
        public C loadContent(String tabName) {
            tabIndex = Arrays.asList(tabNames()).indexOf(tabName) + 1;
            return setContet(tabName);
        }

        private C setContet(String tabName) {
            content = map.get(tabName);
            return content;
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
            return tabNames()[tabIndex - 1];
        }

        @Override
        public Stream<CharSequence> tabStrings() {
            return Stream.of(tabNames()).map(tab -> tabString(tab, map.get(tab)));
        }
    }

}
