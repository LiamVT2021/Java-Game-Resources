package common.ui.data.reqs;

import common.ui.data.adt.Describable;

/**
 * Displayable boolean or int valued Method
 * 
 * @param S the input type of this Method
 * @version 12/23/25
 */
public interface Method<S> extends Describable {

    /**
     * @param subject the Object being input
     * @return Results of this Method
     */
    Results results(S subject);

}
