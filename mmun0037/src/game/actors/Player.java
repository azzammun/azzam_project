package game.actors;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.*;
import game.actions.AssistedAttackAction;
import game.actions.SleepAction;
import game.actions.Sleepable;
import game.actions.TameAction;
import game.behaviours.Tameable;
import game.behaviours.TameHelper;
import game.BareFist;
import game.Abilities;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Player actor in the game.
 * Tracks hydration and warmth, can perform assisted attacks, tame nearby actors,
 * sleep, and interact with items.
 */
public class Player extends Actor implements Sleepable {

    private int hydration = 20;  // Player's hydration level
    private int warmth = 30;     // Player's warmth level
    private EnumSet<Abilities> abilities = EnumSet.noneOf(Abilities.class);

    /**
     * Constructor for Player.
     *
     * @param name display name
     * @param displayChar character representing player on map
     * @param hitPoints initial health points
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addAbility(Abilities.SLEEP);
        this.addAbility(Abilities.ATTACK);
    }

    public EnumSet<Abilities> getAbilities() { return abilities; }
    public void addAbility(Abilities ability) { abilities.add(ability); }
    public void removeAbility(Abilities ability) { abilities.remove(ability); }

    /**
     * Returns random number of turns for sleeping (6-10 turns)
     *
     * @return number of turns player sleeps
     */
    @Override
    public int startSleepingTurns() {
        return 6 + (int)(Math.random() * 5); // random 6-10
    }

    /** Getter for hydration */
    public int getHydration() { return hydration; }

    /** Getter for warmth */
    public int getWarmth() { return warmth; }

    /** Increase hydration by a given amount */
    public void increaseHydration(int amount) { hydration += amount; }

    /** Decrease hydration by a given amount */
    public void decreaseHydration(int amount) { hydration -= amount; }

    /** Decrease warmth by a given amount */
    public void decreaseWarmth(int amount) { warmth -= amount; }

    /**
     * Determines the player's action each turn.
     * Handles: death check, hydration/warmth decrease, menu display,
     * inventory actions, assisted attacks, and tame actions.
     *
     * @param actions default actions available
     * @param lastAction last executed action
     * @param map current game map
     * @param display display for printing messages
     * @return the Action chosen by the player
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Check if player is dead
        if (!this.isConscious()) {
            map.removeActor(this);
            display.println(this + " has died!");
            return new DoNothingAction();
        }

        // Continue multi-turn actions if applicable
        if (lastAction != null && lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Decrease hydration & warmth each turn
        decreaseHydration(1);
        decreaseWarmth(1);
        if (hydration <= 0 || warmth <= 0) {
            display.println(this + " has collapsed!");
            System.exit(0);
        }

        // Display player status
        display.println(this + "\nHYDRATION: " + hydration + "\nWARMTH: " + warmth);

        ActionList availableActions = new ActionList();
        Set<String> addedActions = new HashSet<>();  // avoid duplicates

        Location here = map.locationOf(this);

        // Add allowable actions from items in inventory
        for (Item item : getItemInventory()) {
            for (Action a : item.allowableActions(this, map)) {
                String id = a.menuDescription(this);
                if (!addedActions.contains(id)) {
                    availableActions.add(a);
                    addedActions.add(id);
                }
            }
        }

        // Add SleepAction from an item on the ground
        for (Item item : here.getItems()) {
            item.asCapability(Sleepable.class).ifPresent(sleepable -> {
                // Only if the player has the SLEEP ability
                if (abilities.contains(Abilities.SLEEP)) {
                    SleepAction sleepAction = new SleepAction(this, sleepable);
                    String id = sleepAction.menuDescription(this);
                    if (!addedActions.contains(id)) {
                        availableActions.add(sleepAction);
                        addedActions.add(id);
                    }
                }
            });
        }


        // Add default actions from input
        for (Action a : actions) {
            String id = a.menuDescription(this);
            if (!addedActions.contains(id)) {
                availableActions.add(a);
                addedActions.add(id);
            }
        }

        // default attack with BareFist only if player has ATTACK ability
        if (abilities.contains(Abilities.ATTACK)) {
            BareFist bareFist = new BareFist();

            // Add assisted attacks for all nearby actors
            for (Exit exit : here.getExits()) {
                Actor target = exit.getDestination().getActor();
                if (target != null && target != this) {
                    AssistedAttackAction assistedAttack = new AssistedAttackAction(target, bareFist.getDamage(), bareFist.getHitRate());
                    String id = assistedAttack.menuDescription(this);
                    if (!addedActions.contains(id)) {
                        availableActions.add(assistedAttack);
                        addedActions.add(id);
                    }
                }
            }
        }


        // Add tame actions for nearby tames
        for (Exit exit : here.getExits()) {
            Actor a = exit.getDestination().getActor();
            if (a != null) {
                Tameable tameTarget = TameHelper.getTameable(a);
                if (tameTarget != null && !tameTarget.isTamed()) {
                    TameAction tame = new TameAction(tameTarget);
                    String id = tame.menuDescription(this);
                    if (!addedActions.contains(id)) {
                        availableActions.add(tame);
                        addedActions.add(id);
                    }
                }
            }
        }

        // Show menu to player and return selected action
        Menu menu = new Menu(availableActions);
        return menu.showMenu(this, display);
    }

}
