package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;

import java.util.Random;

/**
 * Abstract base class for all trees that can drop items (fruits) periodically.
 */
public abstract class Tree extends Ground {

    protected int dropRate;                 // number of turns between drops
    protected int turnsSinceLastDrop = 0;   // counter for turns
    protected Random rand = new Random();   // random for selecting drops, if needed

    /**
     * Constructor for Tree.
     * @param displayChar character representing the tree
     * @param name name of the tree
     *
     */
    public Tree(char displayChar, String name) {
        super(displayChar, name);

    }

    /**
     * Called each turn.
     * Drops a fruit if enough turns have passed since last drop.
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        turnsSinceLastDrop++;

        if (turnsSinceLastDrop >= dropRate) {
            Item fruit = dropFruit(); // get fruit to drop
            if (fruit != null) {
                location.addItem(fruit); // add fruit to current location
            }
            turnsSinceLastDrop = 0; // reset counter
        }
    }

    /**
     * Subclasses define which fruit to drop.
     * @return an Item to drop, or null if nothing
     */
    protected abstract Item dropFruit();
}
