package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.WeekSchedule.ActivityType;

public final class Player extends Character{

    private Direction direction;


    // add player stats
    public Player(Point position, Dimensions dimensions, String map,
                  String name, AnimationRepository animationRepository, IInventoryManager inventoryManager){
        super(position, dimensions, map,animationRepository, Direction.RIGHT, null, 0, "xd", inventoryManager);
    }

    @Override
    public ActivityType getActivityType() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public double getAnimationDelta() {
        return 0;
    }

    public void move(double delta){
        Point nextPosition = new Point((int) (getPosition().x() + delta * 100), getPosition().y());
        setPosition(nextPosition);
    }
}
