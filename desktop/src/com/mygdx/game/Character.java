package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.AnimationRepository.Key;
import com.mygdx.game.General.AnimatedGameObject;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.ActivityType;

public abstract class Character extends AnimatedGameObject {

    private final String name;
    private final AnimationRepository animationRepository;
    private final IInventoryManager inventoryManager;


    public Character(TextureRegion textureRegion, Point position, Dimensions dimensions, String map,
                     Vector translation,
                    String name, AnimationRepository animationRepository, IInventoryManager inventoryManager) {
        super(textureRegion, position, dimensions, map, translation);
        this.name = name;
        this.animationRepository = animationRepository;
        this.inventoryManager = inventoryManager;
    }

    public String getName(){
        return name;
    }

    public void updateTexture(ActivityType activityType, Direction direction, double deltaInMillis){
        Key key = new Key(activityType, direction);
        Frame frame = animationRepository.get(key).getFrame(deltaInMillis);
        setTexture(frame.textureRegion());
        setDimensions(frame.dimensions());
        setPosition(getPosition());
    }
}
