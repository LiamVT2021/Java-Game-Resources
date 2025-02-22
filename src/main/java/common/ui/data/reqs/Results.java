package common.ui.data.reqs;

import common.util.math.RelationalOperator;

public abstract class Results {
    private static final String PASS = "[Y]", FAIL = "[N]";

    public abstract String description();

    public Integer prog() {
        return null;
    }

    public RelationalOperator op() {
        return null;
    }

    public Integer goal() {
        return null;
    }

    public Boolean passed() {
        return null;
    }

    public Results[] children() {
        return null;
    }

    protected String passStr() {
        return passed() ? PASS : FAIL;
    }

    public abstract String line();

    public String toString() {
        return line();
    }

}
