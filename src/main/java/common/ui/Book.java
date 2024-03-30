package common.ui;

import java.util.Map;
import java.util.stream.IntStream;

public abstract class Book {
    protected final String header;
    protected final String[] tabs;

    private Book(String header, String[] tabs) {
        this.header = header;
        this.tabs = tabs;
    }

    public static class Array extends Book {
        private final Object[] contents;
        private final String[] footers;

        public Array(String header, Object[] contents, String[] footers) {
            super(header, IntStream.range(0, contents.length).mapToObj(String::valueOf).toArray(String[]::new));
            if (tabs.length != contents.length || (footers != null && tabs.length != footers.length))
                throw new IndexOutOfBoundsException("Array sizes don't match");
            this.contents = contents;
            this.footers = footers;
        }

        public Array(String header, Object... contents) {
            this(header, contents, null);
        }

        public Page page(int i) {
            return new Page(header, tabs, contents[i], footers == null ? null : footers[i]) {
                @Override
                public Page prev() {
                    return i == 0 ? page(contents.length - 1) : page(i - 1);
                }

                @Override
                public Page next() {
                    return i == contents.length - 1 ? page(0) : page(i + 1);
                }

                @Override
                public Page openTab(String tab) {
                    return page(Integer.valueOf(tab));
                }
            };
        }

    }

    public static class Mapped extends Book {
        private final Map<String, PageContent> map;

        public Mapped(String header, Map<String, PageContent> map) {
            super(header, map.keySet().stream().toArray(String[]::new));
            this.map = map;
        }

        public Page page(String tab) {
            PageContent pc = map.get(tab);
            return new Page(header, tabs, pc.content, pc.footer) {
                @Override
                public Page openTab(String tab) {
                    return page(tab);
                }

                @Override
                public Page prev() {
                    return null;
                }

                @Override
                public Page next() {
                    return null;
                }
            };
        }
    }

    public static class PageContent {
        public final Object content;
        public final String footer;

        public PageContent(Object content, String footer) {
            if (content == null)
                throw new IllegalArgumentException("content cannot be null");
            this.content = content;
            this.footer = footer;
        }
    }

}
