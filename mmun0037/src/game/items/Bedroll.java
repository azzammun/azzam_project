package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.DropAction;
import game.actions.Sleepable;

/**
 * Represents a Bedroll item that allows a player to sleep and restore.
 */
public class Bedroll extends Item implements Sleepable {

    /**
     * Constructor for Bedroll.
     * Sets the display character and makes it portable.
     */
    public Bedroll() {
        super("Bedroll", '=', true);
    }

    /**
     * Returns the number of turns a player will sleep.
     * Randomly between 6-10 turns.
     */
    @Override
    public int startSleepingTurns() {
        return 6 + (int)(Math.random() * 5);
    }

    /**
     * Returns the actions allowed for this item.
     * Currently only allows dropping the bedroll.
     */
    @Override
    public ActionList allowableActions(Actor actor, edu.monash.fit2099.engine.positions.GameMap map) {
        ActionList actions = new ActionList();

        // Add drop action
        DropAction drop = getDropAction(actor);
        if (drop != null) actions.add(drop);

        return actions;
    }
}
