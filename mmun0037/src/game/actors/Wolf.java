package game.actors;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actions.AttackAction;
import game.behaviours.Tameable;
import game.behaviours.AssistAttack;
import game.behaviours.TameHelper;
import game.behaviours.Wanderable;
import game.actions.WanderAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a Wolf actor that can be tamed by a Player.
 * Wolves can attack other actors and assist their owner in combat.
 */
public class Wolf extends Actor implements Tameable, AssistAttack {

    private final Random rand = new Random();
    private Player owner;
    private static final int DAMAGE = 50;
    private static final double HITRATE = 0.5;
    private Wanderable wanderBehaviour = new WanderAction();

    /**
     * Constructor for Wolf
     */
    public Wolf() {
        super("Wolf", 'e', 100);
        this.owner = null;
    }

    /**
     * Determine the Wolf's action each turn.
     * Follow owner if tamed, attack nearby targets, or move randomly.
     *
     * @param actions list of possible actions
     * @param lastAction last action performed by this actor
     * @param map current game map
     * @param display display object to show messages
     * @return the chosen Action for this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location myLocation = map.locationOf(this);

        // Remove wolf if unconscious
        if (!this.isConscious()) {
            map.removeActor(this);
            display.println(this + " has died!");
            return new DoNothingAction();
        }

        // Follow owner if tamed
        if (isTamed() && owner != null) {
            Location ownerLoc = map.locationOf(owner);
            Exit bestExit = null;
            int minDist = Integer.MAX_VALUE;
            for (Exit exit : myLocation.getExits()) {
                Location dest = exit.getDestination();
                int dist = Math.abs(dest.x() - ownerLoc.x()) + Math.abs(dest.y() - ownerLoc.y());
                if (dist < minDist && dest.canActorEnter(this)) {
                    minDist = dist;
                    bestExit = exit;
                }
            }
            if (bestExit != null) {
                return new MoveActorAction(bestExit.getDestination(), bestExit.getName(), bestExit.getHotKey());
            }
        }

        // Attack nearby targets
        List<Actor> targets = new ArrayList<>();
        for (Exit exit : myLocation.getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target == null || !target.isConscious() || target == this) continue;

            boolean skip = false;
            Tameable tameTarget = TameHelper.getTameable(target);
            if (tameTarget != null && isTamed() && tameTarget.isTamed() && tameTarget.getOwner() == owner) {
                skip = true; // don't attack owner's other pets
            }
            if (target == owner) skip = true; // never attack owner

            if (!skip) targets.add(target);
        }

        if (!targets.isEmpty()) {
            Actor target = targets.get(rand.nextInt(targets.size()));
            return new AttackAction(target, DAMAGE, HITRATE);
        }

        // Random move if no targets
        return wanderBehaviour.wander(myLocation, actions, map, rand);
    }

    /**
     * Set the owner of this wolf
     *
     * @param player Player who tames the wolf
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Get the owner of this wolf
     *
     * @return the Player who owns this wolf, or null if untamed
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * Check if this wolf is tamed
     *
     * @return true if tamed, false otherwise
     */
    @Override
    public boolean isTamed() {
        return owner != null;
    }

    /**
     * Determine if wolf should assist in attacking the given target
     *
     * @param target Actor to potentially assist against
     * @return true if wolf should assist, false otherwise
     */
    @Override
    public boolean shouldAssist(Actor target) {
        if (!isTamed() || owner == null) return false;
        Tameable tameTarget = TameHelper.getTameable(target);
        if (tameTarget != null) return !tameTarget.isAlly(this);
        return target != owner;
    }

    /**
     * Return the damage contributed by this wolf when assisting an attack
     *
     * @return the assist damage amount
     */
    @Override
    public int assistDamage() {
        return DAMAGE;
    }
}
