package common.ui.data.func;

import common.ui.data.adt.Describable;

/**
 * Displayable Result of a Method
 * 
 * @version 2/23/25
 */
public interface Result<R> extends Describable {

    String text();

    default String calc() {
        return null;
    }

    R result();

    default Result<?>[] children() {
        return null;
    }

}
