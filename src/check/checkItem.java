package check;

public interface checkItem<T> {

    public default String description() {
        return "Hidden";
    }

    public default String progress(T t) {
        return progString(t) + ": " + message(t);
    }

    public default String progString(T t) {
        return description();
    }

    public String message(T t);

    public static checkItem<Object> Null = new checkItem<Object>() {

        @Override
        public String description() {
            return "Null";
        }

        @Override
        public String message(Object t) {
            return description();
        }

    };

}
