package common.ui.data.adt;

/**
 * @version 3/8/25
 */
@FunctionalInterface
public interface HoverOver {

    /**
     * runs when the user hovers the cursor over this Object
     * 
     * @return an Object to display when this is hovered over
     */
    Object hoverOver();

}
