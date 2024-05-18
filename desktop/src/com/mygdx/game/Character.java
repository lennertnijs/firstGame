package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.General.AnimatedGameObject;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.ActivityType;

import java.util.Objects;

public abstract class Character extends AnimatedGameObject {

    private final String name;
    private final AnimationRepository animationRepository;
    private final IInventoryManager inventoryManager;
    // stats


    public Character(TextureRegion textureRegion, Point position, Dimensions dimensions, String map,
                     Vector translation,
                    String name, AnimationRepository animationRepository, IInventoryManager inventoryManager) {
        super(textureRegion, position, dimensions, map, translation);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.animationRepository = Objects.requireNonNull(animationRepository, "Animation repository is null.");
        this.inventoryManager = inventoryManager;
    }

    public String getName(){
        return name;
    }

    public void updateTexture(ActivityType activityType, Direction direction, double deltaInMillis){
        Frame frame = animationRepository.get(activityType, direction).getFrame(deltaInMillis);
        super.setTexture(frame.textureRegion());
        super.setDimensions(frame.dimensions());
        super.setPosition(getPosition());
    }
}
