package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import game.behaviours.AssistAttack;
import game.behaviours.TameHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Action representing an attack on a target actor that can be assisted
 * by nearby allies who implement AssistAttack.
 * Damage from helpers is added to the base damage. If the target dies,
 * it is removed from the map and a death message is included.
 */
public class AssistedAttackAction extends Action {

    private final Actor target;      // Target actor to attack
    private final int baseDamage;    // Base damage attack
    private final double hitRate;    // Probability to hit target
    private final Random rand = new Random();

    /**
     * Constructor
     *
     * @param target the actor to attack
     * @param baseDamage base damage  attack
     * @param hitRate chance to hit the target
     */
    public AssistedAttackAction(Actor target, int baseDamage, double hitRate) {
        this.target = target;
        this.baseDamage = baseDamage;
        this.hitRate = hitRate;
    }

    /**
     * Executes the attack. Checks for nearby assistants, sums damage,
     * applies it if hit, and returns a descriptive message. If the target dies,
     * it is removed from the map and a death message is added.
     *
     * @param actor the actor performing the attack
     * @param map the game map
     * @return description of the attack result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int totalDamage = baseDamage;
        List<String> helpers = new ArrayList<>();

        // Look for nearby allies who can assist
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor ally = exit.getDestination().getActor();
            AssistAttack assistant = TameHelper.getAssist(ally);
            if (assistant != null && assistant.shouldAssist(target)) {
                totalDamage += assistant.assistDamage();
                helpers.add(ally.toString());
            }
        }

        // Determine if attack hits
        boolean hit = rand.nextDouble() < hitRate;
        if (!hit) {
            return actor + " misses " + target +
                    (helpers.isEmpty() ? "" : " (assisted by " + String.join(", ", helpers) + ")");
        }

        // Apply damage
        target.hurt(totalDamage);

        String result = actor + " attacks " + target +
                (helpers.isEmpty() ? "" : " (assisted by " + String.join(", ", helpers) + ")") +
                " for " + totalDamage + " damage!";

        // Check if target died
        if (!target.isConscious()) {
            map.removeActor(target);
            result += "\n" + target + " meets their demise at the hands of " + actor + "!";
        }

        return result;
    }

    /**
     * Returns the menu description for this action
     *
     * @param actor the actor performing the action
     * @return string shown in action menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }
}
