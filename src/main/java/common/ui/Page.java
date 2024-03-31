package common.ui;

import java.util.stream.Stream;

import common.util.StringUtils;

public abstract class Page {
    public final String name;
    protected Object content;
    public final String footer;

    public Page(String name, String footer) {
        if (name == null)
          throw new IllegalArgumentException("Page Name cannot be Null");
        this.name = name;
        this.footer = footer;
    }

    public String header() {
        return name;
    }

    public final Object getContent() {
        return content;
    }

    public String pageString() {
        Stream<CharSequence> stream = Stream.of(header(), content.toString(), footer);
        return StringUtils.join("\n\n", stream.filter(str -> str != null));
    }

    @Override
    public String toString() {
        return pageString();
    }

    public static class Single extends Page {
        public Single(String name, Object content, String footer) {
            super(name, footer);
            if (content == null)
              throw new IllegalArgumentException("Page Content cannot be Null");
            this.content = content;
        }
    }

}
