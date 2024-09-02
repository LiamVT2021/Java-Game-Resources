package rand.dice;

public class Constant implements Rollable {

    private final int con;

    public Constant(int constant) {
        con = constant;
    }

    @Override
    public int roll() {
        return con;
    }

    @Override
    public int min() {
        return con;
    }

    @Override
    public int max() {
        return con;
    }

    @Override
    public String diceStr() {
        return String.valueOf(con);
    }

}
