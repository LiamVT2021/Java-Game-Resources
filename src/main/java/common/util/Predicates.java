package common.util;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {

    public static Predicate<Object> NOT_NULL = Objects::nonNull;
    
}
