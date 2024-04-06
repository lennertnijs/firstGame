package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.ActivityType;

public final class TextureSelector {

    private ActivityType type;
    private final DeltaTime deltaTime;
    private final AnimationRepo<Texture> animationRepo;

    private IAnimation<Texture> animation;

    private TextureSelector(ActivityType type, DeltaTime deltaTime, AnimationRepo<Texture> animationRepo){
        this.type = type;
        this.deltaTime = deltaTime;
        this.animationRepo = animationRepo;
    }

    public static TextureSelector create(ActivityType type, DeltaTime deltaTime, AnimationRepo<Texture> animationRepo){
        return new TextureSelector(type, deltaTime, animationRepo);
    }

    public Animation<Texture> getAnimation(Direction direction){
        // if the animation is finished
        return animationRepo.getAnimation(type, direction);
    }
}
