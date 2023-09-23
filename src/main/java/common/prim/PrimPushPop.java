package common.prim;

import java.util.stream.Stream;

import common.pushPop.PushPop;

public interface PrimPushPop<N extends Number> extends PushPop<N, Number>, NumStream<N> {

    @Override
    default Stream<N> stream() {
        return PushPop.super.stream();
    }

}
