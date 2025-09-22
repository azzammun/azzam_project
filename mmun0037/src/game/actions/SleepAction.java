package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

/**
 * Represents a single-turn action that initiates sleep.
 * Determines the sleep duration and transitions to a multi-turn sleep action.
 */
public class SleepAction extends Action {

    /** The player who will sleep */
    private final Player player;

    /** The Sleepable object (e.g., Bedroll) being used */
    private final Sleepable sleepable;

    /** Number of turns to sleep, determined when action is created */
    private final int turns;

    /**
     * Constructor.
     *
     * @param player    the player actor
     * @param sleepable the sleepable item or object
     */
    public SleepAction(Player player, Sleepable sleepable) {
        this.player = player;
        this.sleepable = sleepable;
        this.turns = sleepable.startSleepingTurns(); // Determine sleep duration immediately
    }

    /**
     * Executes the action: actor lies down and prepares for multi-turn sleep.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " lies down to sleep. (" + turns + " turns)";
    }

    /**
     * Menu description for this action, showing number of turns to sleep.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sleeps on the bedroll (" + turns + " turns)";
    }

    /**
     * Returns the next action, a SleepMultiTurnAction, to handle sleeping over multiple turns.
     */
    @Override
    public Action getNextAction() {
        return new SleepMultiTurnAction(turns);
    }
}
