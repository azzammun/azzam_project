package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Wanderable;

import java.util.Random;

public class WanderAction implements Wanderable
{
    @Override
    public Action wander(Location currentLocation, ActionList actions, GameMap map, Random rand) {
        if (actions.size() > 0) {
            return actions.get(rand.nextInt(actions.size()));
        }
        return new DoNothingAction();
    }
}
