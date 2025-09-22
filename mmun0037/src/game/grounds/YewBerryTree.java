package game.grounds;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.items.YewBerry;
import game.actors.Player;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Yew Berry Tree that periodically drops Yew Berries on neighboring empty locations.
 * Yew Berries are fatal if consumed by the player.
 */
public class YewBerryTree extends Tree {

    private final Player player;          // reference to player for YewBerry
    private final GameMap map;            // map reference for removing player on consumption
    private int counter = 0;              // counts turns since last drop
    private static final int DROP_INTERVAL = 5; // drops every 5 turns
    private final Random rand = new Random();

    /**
     * Constructor for YewBerryTree.
     * @param player the player, passed to YewBerries
     * @param map the game map, needed by YewBerries
     */
    public YewBerryTree(Player player, GameMap map) {
        super('Y', "Yew Berry Tree");
        this.player = player;
        this.map = map;
    }

    /**
     * Called each turn. Drops a Yew Berry on a neighboring empty location every DROP_INTERVAL turns.
     * @param location the location of this tree
     */
    @Override
    public void tick(Location location) {
        counter++;

        // Only drop YewBerry if interval reached
        if (counter >= DROP_INTERVAL) {

            // Collect neighboring empty locations
            List<Location> neighbors = new ArrayList<>();
            for (Exit exit : location.getExits()) {
                Location loc = exit.getDestination();
                if (loc.getItems().isEmpty()) {
                    neighbors.add(loc);
                }
            }

            // Drop YewBerry at a random empty neighbor
            if (!neighbors.isEmpty()) {
                Location dropLocation = neighbors.get(rand.nextInt(neighbors.size()));
                dropLocation.addItem(new YewBerry(player, map));
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
