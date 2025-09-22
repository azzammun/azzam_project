package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * Action to consume a consumable item.
 * Executes the item's consume effect without removing it from inventory.
 */
public class ConsumeAction extends Action {

    /** The consumable item to be consumed */
    private final ConsumableItem item;

    /**
     * Constructor.
     * @param item the consumable item
     * @param actor the actor performing the action (not used here, but kept for signature)
     */
    public ConsumeAction(ConsumableItem item, Actor actor) {
        this.item = item;
    }

    /**
     * Performs the consume action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.consume();  // apply the item's effect
        // item remains in inventory; it is never removed
        return item.menuDescription(actor.toString());
    }

    /**
     * Returns a menu description for the actor.
     * @param actor the actor performing the action
     * @return menu description string
     */
    @Override
    public String menuDescription(Actor actor) {
        return item.menuDescription(actor.toString());
    }
}
