package common.ui;

public abstract class Page<C> {
    // Top buttons on either side of header
    public abstract String header();

    // Tabs and/or prev next buttons
    public final C content;
    public final String footer;

    public Page(C content, String footer) {
        this.content = content;
        this.footer = footer;
    }

    public static class Single<C> extends Page<C> {
        private final String header;

        public Single(String header, C content, String footer) {
            super(content, footer);
            this.header = header;
        }

        @Override
        public String header() {
            return header;
        }

    }

}
