package util;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class CharSeqOps {

    public CharSequence concat(CharSequence... seqs) {
        StringBuilder str = new StringBuilder();
        for (CharSequence seq : seqs)
            str.append(seq);
        return str;
    }

    private CharSequence reduce(Stream<CharSequence> seqs, BinaryOperator<CharSequence> op) {
        Optional<CharSequence> option = seqs.reduce(op);
        return option.isPresent() ? option.get() : null;
    }

    public CharSequence concat(Stream<CharSequence> seqs, boolean parrallel) {
        if (!parrallel) {
            StringBuilder str = new StringBuilder();
            seqs.sequential().forEach(seq -> str.append(seq));
            return str;
        }
        return reduce(seqs.parallel(), this::concat);
    }

    public CharSequence concat(Stream<CharSequence> seqs, CharSequence delim, boolean parrallel) {
        if (!parrallel) {
            StringBuilder str = new StringBuilder();
            seqs.sequential().forEach(seq -> str.append(seq));
            return str;
        }
        return reduce(seqs, (a, b) -> concat(a, delim, b));
    }

    public CharSequence align(CharSequence a, char fill, CharSequence b, int goal) {
        return concat(a, new FillString(fill, goal - a.length() - b.length()), b);
    }

}
