package common.ui.data.adt;

/**
 * @version 2/23/25
 */
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

}
