package common.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import common.util.MapUtils;

public abstract class Book {
    protected final String header;
    protected final String[] tabs;

    private Book(String header, String[] tabs) {
        this.header = header;
        this.tabs = tabs;
    }

    public abstract Tab page(int index);

    public abstract class Tab extends Page {
        private int index;

        public Tab(int index, String header, String[] tabs, Object content, String footer) {
            super(header, tabs, content, footer);
            this.index = index;
        }

        @Override
        public Page prev() {
            return index == 0 ? page(tabs.length - 1) : page(index - 1);
        }

        @Override
        public Page next() {
            return index == tabs.length - 1 ? page(0) : page(index + 1);
        }
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

        public Tab page(int index) {
            return new Tab(index, header, tabs, contents[index], footers == null ? null : footers[index]) {
                @Override
                public Page openTab(String tab) {
                    return page(Integer.valueOf(tab));
                }
            };
        }
    }

    public static class Mapped extends Book {
        private final HashMap<String, PageContent> map;

        public Mapped(String header, Map<String, Page.Content> map) {
            super(header, map.keySet().stream().toArray(String[]::new));
            this.map = MapUtils.mapValues(map, pc -> new PageContent(pc.content, pc.footer), HashMap::new);
        }

        @Override
        public Tab page(int index) {
            String tab = tabs[index];
            return map.get(tab).makeTab(index, tab, map);
        }

        public class PageContent extends Page.Content {

            public PageContent(Object content, String footer) {
                super(content, footer);
            }

            public Tab makeTab(int index, String tab, Map<String, PageContent> map) {
                return new Tab(index, header, tabs, content, footer) {
                    @Override
                    public Page openTab(String tab) {
                        return makeTab(Arrays.asList(tabs).indexOf(tab), tab, map);
                    }
                };
            }
        }
    }

}
