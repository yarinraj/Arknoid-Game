/**
 * A simple counter class that can be incremented, decremented, and reset.
 */
public class Counter {
    private int count;
    /**
     * Constructor to initialize the counter with a starting value.
     *
     * @param value the initial value of the counter
     */
    public Counter(int value) {
        this.count = value;
    }
    /**
     * Default constructor initializing the counter to zero.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Increases the counter by a specified amount.
     *
     * @param number the amount to increase the counter by
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * Decreases the counter by a specified amount.
     *
     * @param number the amount to decrease the counter by
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * Retrieves the current value of the counter.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
