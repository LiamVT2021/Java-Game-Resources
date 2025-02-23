package common.ui.data.reqs;

import common.ui.data.adt.Describable;
import common.util.math.RelationalOperator;

/**
 * Displayable results of a boolean or int valued Method
 * 
 * @version 2/23/25
 */
public abstract class Results implements Describable {
    private static final String PASS = "[Y]", FAIL = "[N]";

    public abstract String str();

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

    public String toString() {
        return fullDescription();
    }

}
