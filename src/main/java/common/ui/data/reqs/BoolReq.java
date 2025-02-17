package common.ui.data.reqs;

import java.util.function.Predicate;

public class BoolReq<T> implements Requirement<T> {

    private final String desc;
    private final Predicate<T> pred;

    public BoolReq(String description, Predicate<T> predicate) {
        desc = description;
        pred = predicate;
    }

    @Override
    public String description() {
        return desc;
    }

    @Override
    public boolean test(T t) {
        return pred.test(t);
    }

    @Override
    public String progress(T t) {
        return desc + (test(t) ? ": passed" : ": failed");
    }

}
