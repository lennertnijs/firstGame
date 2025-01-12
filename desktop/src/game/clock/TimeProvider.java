package game.clock;

public interface TimeProvider {

    /**
     * Updates the {@link TimeProvider} and returns the delta.
     * The delta is the time (in millis) passed between the current moment and the last time update() was called.
     */
    double update();
    void reset();
    TimeProvider copy();
}
