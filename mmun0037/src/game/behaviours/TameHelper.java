package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;

public class TameHelper {

    /** Return actor as Tameable if possible, else null */
    public static Tameable getTameable(Actor actor) {
        try {
            return (Tameable) actor;
        } catch (ClassCastException e) {
            return null;
        }
    }

    /** Return actor as AssistAttack if possible, else null */
    public static AssistAttack getAssist(Actor actor) {
        try {
            return (AssistAttack) actor;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
