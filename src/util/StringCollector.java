package util;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public abstract class StringCollector implements Collector<CharSequence, StringBuilder, StringBuilder> {

    private final boolean ordered;

    public StringCollector(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (a, b) -> merge(a, b, ordered, accumulator());
    }

    private static StringBuilder merge(StringBuilder a, StringBuilder b, boolean ordered,
            BiConsumer<StringBuilder, CharSequence> append) {
        if (ordered || a.length() >= b.length()) {
            append.accept(a, b);
            return a;
        }
        append.accept(b, a);
        return b;
    }

    private static final Function<StringBuilder, StringBuilder> idFunc = i -> i;

    @Override
    public Function<StringBuilder, StringBuilder> finisher() {
        return idFunc;
    }

    private static final Set<Characteristics> orderChar = Set.of(Collector.Characteristics.IDENTITY_FINISH);
    private static final Set<Characteristics> unorderChar = Set.of(Collector.Characteristics.IDENTITY_FINISH,
            Collector.Characteristics.UNORDERED);

    @Override
    public Set<Characteristics> characteristics() {
        return ordered ? orderChar : unorderChar;
    }

    public CharSequence collect(Stream<CharSequence> stream) {
        stream.filter(seq -> seq != null);
        stream = ordered ? stream.sequential() : stream.unordered();
        return stream.collect(this);
    }

    public String reduce(Stream<CharSequence> stream) {
        return collect(stream).toString();
    }

    //

    public static final StringCollector JOIN = new Func((build, seq) -> build.append(seq), true);
    public static final StringCollector LIST = new Delim(", ", true);
    public static final StringCollector BAG = new Delim(", ", false);
    public static final StringCollector LINES;

    //

    public static class Func extends StringCollector {

        private final BiConsumer<StringBuilder, CharSequence> func;

        public Func(BiConsumer<StringBuilder, CharSequence> func, boolean ordered) {
            super(ordered);
            this.func = func;
        }

        @Override
        public BiConsumer<StringBuilder, CharSequence> accumulator() {
            return func;
        }

    }

    public static class Delim extends StringCollector {

        private final String delim;

        public Delim(CharSequence delim, boolean ordered) {
            super(ordered);
            delim = delim.toString();
        }

        @Override
        public BiConsumer<StringBuilder, CharSequence> accumulator() {
            return (build, seq) -> {
                if (build.length() != 0)
                    build.append(delim);
                build.append(seq);
            };
        }

    }

    public static class Bullet extends StringCollector {
        private final String bullet;
        private final String next;
        private static final String newLine = "\n";

        @Override
        public BiConsumer<StringBuilder, CharSequence> accumulator() {
            return (build, seq) -> {
                build.append(build.length() == 0 ? bullet : next);
                build.append(seq);
            };
        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return (a, b) -> merge(a, b, ordered, (a,b)->{
                if (a)
            });
        }

    }

}
