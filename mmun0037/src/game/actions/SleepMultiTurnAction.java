package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents a multi-turn sleep action.
 * Keeps track of remaining turns and updates status each turn.
 */
public class SleepMultiTurnAction extends Action {

    /** Number of turns left to sleep */
    private int remainingTurns;

    /**
     * Constructor.
     *
     * @param turns number of turns the actor will sleep
     */
    public SleepMultiTurnAction(int turns) {
        this.remainingTurns = turns;
    }

    /**
     * Executes one turn of sleep.
     * Decrements remaining turns and prints appropriate message.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        remainingTurns--;
        if (remainingTurns <= 0) {
            // Actor has finished sleeping
            return actor + " wakes up refreshed!";
        }
        // Actor is still sleeping
        return actor + " continues sleeping. (" + remainingTurns + " turns left)";
    }

    /**
     * Menu description displayed in the action menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is sleeping";
    }

    /**
     * Returns the next action if sleeping is not finished.
     */
    @Override
    public Action getNextAction() {
        if (remainingTurns > 0) {
            return this; // Continue sleeping next turn
        }
        return null; // Sleep finished
    }
}
