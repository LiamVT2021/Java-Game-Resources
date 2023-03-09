package common.dice;

public interface Die extends Dice {

    default int roll(int count) {
        int i = Math.abs(count), ret = 0;
        for (; i > 0; i--)
            ret += roll();
        return count >= 0 ? ret : -ret;
    }

    default Dice make(int count) {
        return new Dice() {

            @Override
            public int roll() {
                return Die.this.roll(count);
            }

            @Override
            public String toString() {
                return String.valueOf(count) + Die.this;
            }

        };
    }

}
