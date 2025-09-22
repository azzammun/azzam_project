package game.grounds;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.items.Hazelnut;
import game.actors.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Hazelnut Tree that periodically drops Hazelnuts on neighboring empty locations.
 */
public class HazelnutTree extends Tree {

    private final Player player;         // reference to the player, passed to Hazelnuts
    private int counter = 0;             // tick counter
    private static final int DROP_INTERVAL = 10; // ticks between drops
    private final Random rand = new Random();

    /**
     * Constructor for HazelnutTree.
     * @param player the player, needed to assign to Hazelnuts
     */
    public HazelnutTree(Player player) {
        super('A', "Hazelnut Tree");
        this.player = player;
    }

    /**
     * Called every turn. Drops a Hazelnut on a neighboring empty location every DROP_INTERVAL ticks.
     * @param location the location of this tree
     */
    @Override
    public void tick(Location location) {
        counter++;

        // Only drop Hazelnut if interval reached
        if (counter >= DROP_INTERVAL) {
            List<Location> neighbors = new ArrayList<>();

            // Collect neighboring empty locations
            for (Exit exit : location.getExits()) {
                Location loc = exit.getDestination();
                if (loc.getItems().isEmpty()) {
                    neighbors.add(loc);
                }
            }

            // Drop Hazelnut at a random empty neighbor
            if (!neighbors.isEmpty()) {
                Location dropLocation = neighbors.get(rand.nextInt(neighbors.size()));
                dropLocation.addItem(new Hazelnut(player));
            }

            counter = 0; // reset counter after dropping
        }
    }
    @Override
    protected Item dropFruit()
    {
        return null;
    }

}
