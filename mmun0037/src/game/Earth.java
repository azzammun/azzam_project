// Earth.java
package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bear;
import game.actors.Deer;
import game.actors.Player;
import game.actors.Wolf;
import game.grounds.WildAppleTree;
import game.grounds.HazelnutTree;
import game.grounds.YewBerryTree;
import game.items.Bedroll;
import game.items.Bottle;

import java.util.Arrays;
import java.util.List;

public class Earth extends World {

    public Earth(Display display) {
        super(display);
    }

    public void constructWorld() throws Exception {

        // Create ground types
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);

        // Map layout
        List<String> mapLayout = Arrays.asList(
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................"
        );

        // Create GameMap
        GameMap gameMap = new GameMap("Forest", groundCreator, mapLayout);
        this.addGameMap(gameMap);

        // Player setup
        Player player = new Player("Explorer", 'ඞ', 100);
        player.addItemToInventory(new Bedroll());
        player.addItemToInventory(new Bottle(player));
        this.addPlayer(player, gameMap.at(1, 1));

        // NPC setup
        Bear bear = new Bear();
        Wolf wolf = new Wolf();
        Deer deer = new Deer();

        // Spawn NPCs at specific locations
        gameMap.at(3, 1).addActor(bear);
        gameMap.at(3, 3).addActor(wolf);
        gameMap.at(1, 3).addActor(deer);

        // Spawn trees as ground
        gameMap.at(6, 0).setGround(new WildAppleTree(player));
        gameMap.at(6, 2).setGround(new HazelnutTree(player));
        gameMap.at(2, 2).setGround(new YewBerryTree(player, gameMap));
    }
}
