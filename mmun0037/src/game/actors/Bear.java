package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.*;
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
 * Class representing a Bear actor that can be tamed by a Player.
 * Bears can attack other actors and assist their owner in combat.
 */
public class Bear extends Actor implements Tameable, AssistAttack {

    private final Random rand = new Random();
    private Player owner;
    private static final int DAMAGE = 75;
    private static final double HITRATE = 0.8;
    private Wanderable wanderBehaviour = new WanderAction();

    /**
     * Constructor for Bear
     */
    public Bear() {
        super("Bear", 'B', 200);
        this.owner = null;
    }

    /**
     * Determine the Bear's action each turn.
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
            if (tameTarget != null && isTamed() && tameTarget.isTamed() && tameTarget.getOwner() == this.owner) {
                skip = true;
            }
            if (target == owner) skip = true;

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
     * Set the owner of this bear
     *
     * @param player Player who tames the bear
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Get the owner of this bear
     *
     * @return the Player who owns this bear, or null if untamed
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * Check if this bear is tamed
     *
     * @return true if tamed, false otherwise
     */
    @Override
    public boolean isTamed() {
        return owner != null;
    }

    /**
     * Determine if bear should assist in attacking the given target
     *
     * @param target Actor to potentially assist against
     * @return true if bear should assist, false otherwise
     */
    @Override
    public boolean shouldAssist(Actor target) {
        if (!isTamed() || owner == null) return false;
        Tameable tameTarget = TameHelper.getTameable(target);
        if (tameTarget != null) return !tameTarget.isAlly(this);
        return target != owner;
    }

    /**
     * Return the damage contributed by this bear when assisting an attack
     *
     * @return the assist damage amount
     */
    @Override
    public int assistDamage() {
        return DAMAGE;
    }
}
