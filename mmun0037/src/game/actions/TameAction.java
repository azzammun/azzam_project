package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.behaviours.Tameable;

/**
 * Action to tame a Tameable actor.
 * The actor performing this action becomes the owner of the target.
 */
public class TameAction extends Action {

    /** The Tameable actor to be tamed */
    private final Tameable target;

    /**
     * Constructor.
     * @param target the Tameable actor to tame
     */
    public TameAction(Tameable target) {
        this.target = target;
    }

    /**
     * Executes the tame action.
     * Sets the actor as the owner of the target.
     * @param actor the actor performing the action (assumed to be Player)
     * @param map the game map
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.setOwner((Player) actor); // assign actor as owner
        return actor + " tames " + target + " successfully!";
    }

    /**
     * Menu description for this action.
     * @param actor the actor performing the action
     * @return string for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " tames " + target;
    }

    /**
     * Hotkey for this action.
     * @return null to follow menu ordering
     */
    @Override
    public String hotkey() {
        return null;
    }
}
