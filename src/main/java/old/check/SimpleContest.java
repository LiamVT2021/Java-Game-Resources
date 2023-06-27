package old.check;

import java.util.function.ToIntFunction;

/**
 * Functional Interface representing a contest between two objects of the same
 * class who share the same evaluatoin function
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <C> the class of the objects competing
 */
@FunctionalInterface
public interface SimpleContest<C>
		extends ContestedCheck<C, C>, ToIntFunction<C> {

	public default ToIntFunction<? super C> aFunc() {
		return this;
	}

	public default ToIntFunction<? super C> bFunc() {
		return this;
	}

	public default int aVal(C a) {
		return applyAsInt(a);
	}

	public default int bVal(C b) {
		return applyAsInt(b);
	}

	@Override
	public default boolean test(C first, C second) {
		return applyAsInt(first) >= applyAsInt(second);
	}

}
