package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

public final class Player extends Character {


    // add player stats
    public Player(Point position, Dimensions dimensions, String map,
                  String name, AnimationRepository animationRepository, IInventoryManager inventoryManager){
        super(position, dimensions, map,animationRepository, 0, Direction.RIGHT, null, name, inventoryManager);
    }


    public void move(double delta){
        Point nextPosition = new Point((int) (getPosition().x() + delta * 100), getPosition().y());
        setPosition(nextPosition);
    }
}
