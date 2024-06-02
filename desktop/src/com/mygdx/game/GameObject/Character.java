package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Util.*;

import java.util.List;
import java.util.Objects;

public abstract class Character extends Entity {

    private final String name;
    private final Inventory inventory; // todo probably wanna save item textures globally
    private int activeIndex;

    public Character(Point position,
                     Dimensions dimensions,
                     String map,
                     AnimationRepository animationRepository,
                     double delta,
                     Direction direction,
                     List<ActivityType> activityTypeList,
                     String name,
                     Inventory inventory) {
        super(position, dimensions, map, animationRepository, delta, direction, activityTypeList);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.inventory = inventory;
    }

    public String getName(){
        return name;
    }
}
