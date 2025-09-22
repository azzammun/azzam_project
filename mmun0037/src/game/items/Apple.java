package game.items;

import game.actors.Player;

/**
 * Represents an Apple that can be consumed by the player.
 * Eating an Apple heals HP and increases hydration.
 */
public class Apple extends ConsumableItem {

    private final Player player; // reference to player to apply effects

    /**
     * Constructor for Apple.
     * @param player the player who can consume this Apple
     */
    public Apple(Player player) {
        super("Apple", 'a', true);
        this.player = player;
    }

    /**
     * Apply effects of consuming the Apple.
     * Heals 3 HP and increases hydration by 2.
     */
    @Override
    public void consume() {
        player.heal(3);             // heal player
        player.increaseHydration(2); // increase hydration
    }

    /**
     * Menu description for displaying in the action menu.
     * @param actorName name of the actor consuming
     * @return description string
     */
    @Override
    public String menuDescription(String actorName) {
        return actorName + " eats an Apple";
    }
}
