package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;

public interface AssistAttack {
    /**
     * Check if this actor should assist an attack on a target.
     * @param target the actor being attacked
     * @return true if this actor should attack in the same turn
     */
    boolean shouldAssist(Actor target);

    /**
     * Return the attack damage this actor deals when assisting
     */
    int assistDamage();
}
