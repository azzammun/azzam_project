package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * Represents an attack action that an Actor can perform on another Actor.
 * This supports hit chance (hitRate) and damage calculation. If the target dies,
 * it is removed from the GameMap and a death message is returned.
 */
public class AttackAction extends Action {

    /** The Actor being attacked */
    private final Actor target;

    /** The amount of damage this attack deals if it hits */
    private final int damage;

    /** Chance to hit the target (e.g., 0.8 = 80% hit chance) */
    private final double hitRate;

    /** Random generator for determining hit success */
    private final Random rand = new Random();

    /**
     * Constructs an AttackAction.
     *
     * @param target the Actor to attack
     * @param damage the amount of damage to deal if the attack hits
     * @param hitRate the probability of hitting the target (0.0 to 1.0)
     */
    public AttackAction(Actor target, int damage, double hitRate) {
        this.target = target;
        this.damage = damage;
        this.hitRate = hitRate;
    }

    /**
     * Executes the attack action.
     * If the target is already dead, a message is returned.
     * If the attack hits, damage is applied and if the target dies, it is removed from the map.
     *
     * @param actor the Actor performing the attack
     * @param map the GameMap where the action occurs
     * @return a String describing the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // Check if target is already dead
        if (!target.isConscious()) {
            return actor + " tries to attack " + target + " but target is already dead!";
        }

        // Determine if the attack hits based on hitRate
        if (rand.nextDouble() < hitRate) {
            target.hurt(damage); // Apply damage

            String result = actor + " attacks " + target + " for " + damage + " damage!";

            // Check if target died from the attack
            if (!target.isConscious()) {
                Location targetLoc = map.locationOf(target); // Get target's location
                map.removeActor(target); // Remove dead actor from the map
                result += "\n" + target + " meets their demise at the hands of " + actor + "!";
            }

            return result;
        } else {
            // Attack missed
            return actor + " attacks " + target + " but misses!";
        }
    }

    /**
     * Provides a description of this action for menu display.
     *
     * @param actor the Actor performing the attack
     * @return a String describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }
}
