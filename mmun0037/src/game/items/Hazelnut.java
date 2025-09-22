package game.items;

import game.actors.Player;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;

/**
 * Hazelnut item that can be consumed by the player.
 * Increases player's maximum health by 1 when eaten.
 */
public class Hazelnut extends ConsumableItem {

    private final Player player;

    /**
     * Constructor for Hazelnut.
     * @param player the player who will consume this item
     */
    public Hazelnut(Player player) {
        super("Hazelnut", 'n', true);
        this.player = player;
    }

    /**
     * Consume the hazelnut and increase player's max health by 1.
     */
    @Override
    public void consume() {
        player.modifyStatsMaximum(BaseAttributes.HEALTH, ActorAttributeOperation.INCREASE, 1);
    }

    /**
     * Menu description for action menu.
     * @param actorName name of the actor consuming
     * @return description string
     */
    @Override
    public String menuDescription(String actorName) {
        return actorName + " eats a Hazelnut";
    }
}
