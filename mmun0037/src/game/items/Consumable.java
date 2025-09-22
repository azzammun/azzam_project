package game.items;



/**
 * Interface for all consumable items.
 * Defines methods to consume the item and show menu description.
 * Supports optional check if the item is fully consumed.
 */
public interface Consumable {

    /**
     * Apply the effect of consuming this item.
     * Implementation depends on the item type.
     */
    void consume();

    /**
     * Menu description to show in the action menu.
     * @param actorName name of the actor consuming
     * @return description string
     */
    String menuDescription(String actorName);


}
