package game.items;

import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

/**
 * Yew Berry is a fatal consumable item.
 * Eating this immediately kills the player.
 */
public class YewBerry extends ConsumableItem {

    private final Player player;
    private final GameMap map;

    /**
     * Constructor for Yew Berry.
     * @param player the player who may consume this berry
     * @param map current game map (needed to remove player upon death)
     */
    public YewBerry(Player player, GameMap map) {
        super("Yew Berry", 'x', true);
        this.player = player;
        this.map = map;
    }

    /**
     * Consuming the Yew Berry kills the player instantly.
     */
    @Override
    public void consume() {
        // Reduce player's HP to 0
        player.hurt(player.getAttribute(BaseAttributes.HEALTH));
        // Remove the player from the map
        map.removeActor(player);
    }

    /**
     * Menu description for action menu.
     * @param actorName name of the actor consuming
     * @return description string
     */
    @Override
    public String menuDescription(String actorName) {
        return actorName + " eats a Yew Berry (poisonous!!!)";
    }
}
