package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.DropAction;
import game.actors.Player;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents a Bottle consumable item.
 * Can be consumed multiple times until uses run out.
 * Each use restores hydration to the player.
 */
public class Bottle extends ConsumableItem {

    private int usesLeft = 5;      // remaining sips
    private final Player owner;     // player who owns this bottle

    /**
     * Constructor for Bottle.
     * @param owner the player who owns the bottle
     */
    public Bottle(Player owner) {
        super("Bottle", 'o', true);
        this.owner = owner;
    }

    /**
     * Returns the remaining uses of the bottle.
     * @return number of uses left
     */
    public int getUsesLeft() {
        return usesLeft;
    }

    /** Decrease the uses left by 1. */
    private void decrementUses() {
        if (usesLeft > 0) usesLeft--;
    }

    /**
     * Apply the effect of drinking the bottle.
     * Increases hydration and decreases usesLeft.
     */
    @Override
    public void consume() {
        if (usesLeft > 0) {
            owner.increaseHydration(4); // restore hydration
            decrementUses();
        }
    }

    /**
     * Menu description for drinking the bottle.
     * @param actorName name of the actor consuming
     * @return description string
     */
    @Override
    public String menuDescription(String actorName) {
        return actorName + " drinks from the Bottle (" + usesLeft + " left)";
    }

    /**
     * Returns all actions the actor can do with this item.
     * Only shows consume action if uses are left.
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();

        // Add drop action if possible
        DropAction drop = getDropAction(actor);
        if (drop != null) actions.add(drop);

        // Add consume action only if there are remaining uses
        if (usesLeft > 0) {
            actions.add(getConsumeAction(actor));
        }

        return actions;
    }
}
