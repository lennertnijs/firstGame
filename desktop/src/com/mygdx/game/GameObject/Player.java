package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.Arrays;

import static com.mygdx.game.Keys.NPCActivityType.IDLING;

public final class Player extends Character {


    // add player stats
    public Player(Point position, Dimensions dimensions, String map,
                  String name, AnimationRepository animationRepository, Inventory inventory){
        super(position, dimensions, map,animationRepository, 0, Direction.RIGHT, Arrays.asList(IDLING), name, inventory);
    }


    public void move(double delta){
        Point nextPosition = new Point((int) (getPosition().x() + delta * 100), getPosition().y());
        setPosition(nextPosition);
    }
}
