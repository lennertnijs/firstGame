package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

public final class Player extends Character{

    private Direction direction;


    // add player stats
    public Player(TextureRegion textureRegion, Point position, Dimensions dimensions, String map,
                  String name, AnimationRepository animationRepository, IInventoryManager inventoryManager){
        super(textureRegion, position, dimensions, map,new Vector(5, 6), name ,animationRepository, inventoryManager);
    }

    public void move(double delta){
        Point nextPosition = new Point((int) (getPosition().x() + delta * 100), getPosition().y());
        setPosition(nextPosition);
    }
}
