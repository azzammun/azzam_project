package game.behaviours;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;

import java.util.List;

/**
 * Interface for actors that can help collect items and deliver them to their owner/player.
 */
public interface ResourceHelper {

    /**
     * Collects items from the current location and gives them to the specified owner.
     * Default implementation takes the first item in the location's list.
     *
     * @param location current location of the actor
     * @param owner the Player who will receive the item
     * @param display display for printing messages
     */
    default void collectAndDeliver(Location location, Player owner, Display display) {

        // Get all items at the current location
        List<Item> items = location.getItems();

        // If there are items, pick the first one and give to owner
        if (!items.isEmpty()) {
            Item item = items.get(0);  // pick first item; can be modified for strategy
            location.removeItem(item); // remove from location
            owner.addItemToInventory(item); // add to owner's inventory

            // Print message about the action
            display.println(this + " picks up " + item + " and gives it to " + owner + "!");
        }
    }
}
