package game.grounds;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.items.Apple;
import game.actors.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Wild Apple Tree that periodically drops Apples on neighboring empty locations.
 */
public class WildAppleTree extends Tree {

    private final Player player;          // reference to player for Apple assignment
    private int counter = 0;              // counts turns since last drop
    private static final int DROP_INTERVAL = 3; // drops every 3 turns
    private final Random rand = new Random();

    /**
     * Constructor for WildAppleTree.
     * @param player the player, passed to dropped Apples
     */
    public WildAppleTree(Player player) {
        super('T', "Wild Apple Tree");
        this.player = player;
    }

    /**
     * Called each turn. Drops an Apple on a neighboring empty location every DROP_INTERVAL turns.
     * @param location the location of this tree
     */
    @Override
    public void tick(Location location) {
        counter++;

        // Only drop Apple if interval reached
        if (counter >= DROP_INTERVAL) {

            // Collect neighboring empty locations
            List<Location> neighbors = new ArrayList<>();
            for (Exit exit : location.getExits()) {
                Location loc = exit.getDestination();
                if (loc.getItems().isEmpty()) {
                    neighbors.add(loc);
                }
            }

            // Drop Apple at a random empty neighbor
            if (!neighbors.isEmpty()) {
                Location dropLocation = neighbors.get(rand.nextInt(neighbors.size()));
                dropLocation.addItem(new Apple(player));
            }

            counter = 0; // reset counter
        }
    }

    @Override
    protected Item dropFruit()
    {
        return null;
    }

}
