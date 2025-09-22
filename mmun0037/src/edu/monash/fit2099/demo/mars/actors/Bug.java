package edu.monash.fit2099.demo.mars.actors;

import edu.monash.fit2099.demo.mars.actions.KickAction;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.*;

public class Bug extends Actor {

    private final Random rand = new Random();
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();

    public Bug() {
        super("Feature", 'x', 1);
    }

    public void addNewBehaviour(Integer priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.generateAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return actions.get(rand.nextInt(actions.size()));
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        list.add(new KickAction(this));
        return list;
    }
}
