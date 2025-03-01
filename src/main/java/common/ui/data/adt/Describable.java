package common.ui.data.adt;

/**
 * @version 3/1/25
 */
@FunctionalInterface
public interface Describable {

    /**
     * @return one line description of this Object
     */
    String lineDescription();

    /**
     * @return longer description of this Object
     */
    default String fullDescription() {
        return lineDescription();
    }

    static class ADT implements Describable {
        protected final String line, full;

        /**
         * @param description one line description of this Predicate
         * @param predicate
         */
        public ADT(String description) {
            this(description, null);
        }

        /**
         * @param line      one line description of this Predicate
         * @param full      longer description of this Predicate
         * @param predicate
         */
        public ADT(String line, String full) {
            this.line = line;
            this.full = full;
        }

        @Override
        public String lineDescription() {
            return line;
        }

        @Override
        public String fullDescription() {
            return full != null ? full : line;
        }

        @Override
        public String toString() {
            return full == null ? line : line + ":\n" + full;
        }
    }

}
