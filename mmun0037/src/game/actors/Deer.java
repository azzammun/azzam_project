package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TameAction;
import game.behaviours.Tameable;
import game.behaviours.ResourceHelper;
import game.behaviours.Wanderable;
import game.actions.WanderAction;
import game.Abilities;
import java.util.Random;

/**
 * Deer actor in the game
 * Can be tamed by a Player, follow its owner, and help collect items
 */
public class Deer extends Actor implements Tameable, ResourceHelper {

    private final Random rand = new Random();
    private Actor owner;
    private Wanderable wanderBehaviour = new WanderAction();

    /** Constructor for Deer */
    public Deer() {
        super("Deer", 'd', 50);
        this.owner = null;
    }

    /**
     * Determines the deer's action each turn
     * Deer can follow its owner and help collect items, allow tame action, or move randomly
     *
     * @param actions list of available actions
     * @param lastAction last action performed by this actor
     * @param map current game map
     * @param display display object for messages
     * @return chosen Action for this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location myLocation = map.locationOf(this);

        // Remove deer if unconscious
        if (!this.isConscious()) {
            map.removeActor(this);
            display.println(this + " meets its demise!");
            return new DoNothingAction();
        }

        // Follow owner if tamed and help collect items
        if (isTamed() && owner != null) {
            collectAndDeliver(myLocation, (Player) owner, display);

            Location ownerLoc = map.locationOf(owner);
            Exit bestExit = null;
            int minDist = Integer.MAX_VALUE;

            // Find nearest exit towards owner
            for (Exit exit : myLocation.getExits()) {
                Location dest = exit.getDestination();
                int dist = Math.abs(dest.x() - ownerLoc.x()) + Math.abs(dest.y() - ownerLoc.y());
                if (dist < minDist && dest.canActorEnter(this)) {
                    minDist = dist;
                    bestExit = exit;
                }
            }

            if (bestExit != null) {
                return new MoveActorAction(
                        bestExit.getDestination(), bestExit.getName(), bestExit.getHotKey());
            }
        }

        // Add tame action if nearby actor has TAME ability and deer is untamed
        if (!isTamed()) {
            for (Exit exit : myLocation.getExits()) {
                Location dest = exit.getDestination();
                if (dest.containsAnActor()) {
                    Actor nearby = dest.getActor();
                    if (nearby.hasAbility(Abilities.TAME)) {
                        actions.add(new TameAction(this));
                    }
                }
            }
        }

        // Random move or do nothing if no other actions
        return wanderBehaviour.wander(myLocation, actions, map, rand);
    }

    // Tameable interface
    /**
     * Sets the owner of this deer
     *
     * @param player the Player who tames this deer
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Gets the owner of this deer
     *
     * @return the Player who owns this deer, or null if untamed
     */
    @Override
    public Player getOwner() {
        return (Player) owner;
    }

    /**
     * Checks if the deer is tamed
     *
     * @return true if tamed, false otherwise
     */
    @Override
    public boolean isTamed() {
        return owner != null;
    }
}
