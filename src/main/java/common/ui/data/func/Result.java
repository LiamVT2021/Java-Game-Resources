package common.ui.data.func;

import common.ui.data.adt.Describable;

/**
 * Displayable Result of a Method
 * 
 * @version 3/1/25
 */
public abstract class Result<R> implements Describable {

    public abstract String text();

    public String calc() {
        return null;
    }

    public abstract R result();

    public Result<?>[] children() {
        return null;
    }

    public String toString() {
        return fullDescription();
    }

}
