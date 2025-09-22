package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.ConsumeAction;

/**
 * Abstract base class for all consumable items.
 * Provides default allowable actions: drop + consume.
 * Concrete consumables (like Bottle, Apple) extend this class.
 */
public abstract class ConsumableItem extends Item implements Consumable {

    /**
     * Constructor for consumable item.
     * @param name name of the item
     * @param displayChar display character on map
     * @param portable true if the item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Returns all actions that an actor can perform with this item.
     * default: drop + consume.
     * @param actor actor interacting with the item
     * @param map current game map
     * @return list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();

        // Add drop action if possible
        DropAction drop = getDropAction(actor);
        if (drop != null) actions.add(drop);

        // Add consume action
        actions.add(getConsumeAction(actor));

        return actions;
    }

    /**
     * Returns a ConsumeAction for this item for the given actor.
     * @param actor the actor performing the consume action
     * @return a ConsumeAction object
     */
    public ConsumeAction getConsumeAction(Actor actor) {
        return new ConsumeAction(this, actor);
    }
}
