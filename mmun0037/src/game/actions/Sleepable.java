package game.actions;

/**
 * Interface for objects that can be slept on.
 * Provides a method to determine the number of turns an actor will sleep.
 */
public interface Sleepable {

    /**
     * Determines how many turns the actor should sleep.
     *
     * @return the number of turns for sleeping
     */
    int startSleepingTurns();
}
