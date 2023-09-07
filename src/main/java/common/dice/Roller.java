package common.dice;

@FunctionalInterface
public interface Roller {

    int rand();

    default int mod() {
        return 0;
    }

    default int roll() {
        return rand() + mod();
    }

    default Integer rangeMin() {
        return null;
    }

    default Integer rangeMax() {
        return null;
    }

}
