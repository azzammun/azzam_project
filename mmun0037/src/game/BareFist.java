package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Represents an intrinsic weapon called a bare fist.
 *
 * This weapon is used by actors who attack without holding an external weapon.
 * It deals fixed damage and has a fixed hit rate.
 *
 * Damage: 25 points
 * Hit chance: 50%
 *
 * @author Adrian
 */
public class BareFist extends IntrinsicWeapon {

    /**
     * Constructor for BareFist.
     * Initializes the damage, verb, hit rate, and weapon name.
     */
    public BareFist() {
        super(25, "punches", 50, "bare fist");
    }

    /**
     * Returns the fixed damage points of this weapon.
     *
     * @return damage points (integer)
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the hit rate as a probability between 0.0 and 1.0.
     *
     * @return hit rate as a decimal
     */
    public double getHitRate() {
        return this.hitRate / 100.0; // convert percentage to probability
    }
}
