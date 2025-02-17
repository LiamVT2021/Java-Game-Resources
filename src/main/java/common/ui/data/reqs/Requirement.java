package common.ui.data.reqs;

import java.util.function.Predicate;

public interface Requirement<T> extends Predicate<T> {

    String description();

    String progress(T t);

    default String full(T t) {
        return progress(t);
    }

}
