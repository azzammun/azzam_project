package game.behaviours;

import game.actors.Player;

/**
 * Interface for actors that can be tamed by a Player.
 * Provides methods to get/set owner, check if tamed, and determine allies.
 */
public interface Tameable {

    /**
     * Returns the Player who tamed this actor.
     *
     * @return owner Player, or null if not tamed
     */
    Player getOwner();

    /**
     * Sets the Player as the owner of this actor.
     *
     * @param player the Player who tames this actor
     */
    void setOwner(Player player);

    /**
     * Checks if this actor has been tamed.
     *
     * @return true if tamed, false otherwise
     */
    boolean isTamed();

    /**
     * Default method to check if another Tameable actor is an ally.
     * Two actors are allies if they share the same owner and both are tamed.
     *
     * @param other another Tameable actor
     * @return true if the other actor is an ally, false otherwise
     */
    default boolean isAlly(Tameable other) {
        return this.isTamed() && other != null && this.getOwner() == other.getOwner();
    }
}
