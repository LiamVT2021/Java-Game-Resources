package common.ui;

public abstract class Page {
    // LAYOUT - Top buttons on either side of header
    public final String header;
    public final String[] tabs;
    public abstract Page prev();
    public final Object content;
    public abstract Page next();
    public final String footer;

    // CODE

    public Page(String header, String[] tabs, Object content, String footer) {
        this.header = header;
        this.tabs = tabs;
        this.content = content;
        this.footer = footer;
    }

    public boolean displayTabs() {
        return false;
    }

    public boolean displayArrows() {
        return true;
    }

    public abstract Page openTab(String tab);

    public static class Single extends Page {

        public Single(String header, Object content, String footer) {
            super(header, null, content, footer);
        }

        @Override
        public final boolean displayTabs() {
            return false;
        }

        @Override
        public final boolean displayArrows() {
            return false;
        }

        @Override
        public final Page prev() {
            return null;
        }

        @Override
        public final Page next() {
            return null;
        }

        @Override
        public final Page openTab(String tab) {
            return null;
        }

    }

}
