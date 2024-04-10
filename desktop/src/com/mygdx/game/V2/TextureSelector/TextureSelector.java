package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

import java.util.Objects;

public final class TextureSelector implements ITextureSelector{

    private ActivityType activityType;
    private Direction direction;
    private final IAnimationRepository<Texture> animationRepo;
    private LoopAnimation<Texture> activeAnimation;
    private final IDeltaTime deltaTime;

    public TextureSelector(Builder builder){
        this.activityType = builder.activityType;
        this.direction = builder.direction;
        this.animationRepo = builder.animationRepo;
        this.deltaTime = builder.deltaTime;
        updateActiveAnimation();
    }

    /**
     * Sets the ActivityType.
     * If the new ActivityType is different from the old one, updates the active Animation.
     */
    public void setActivityType(ActivityType activityType){
        Objects.requireNonNull(activityType, "Cannot set the ActivityType to null.");
        if(this.activityType == activityType){
            return;
        }
        this.activityType = activityType;
        updateActiveAnimation();
    }

    /**
     * Sets the Direction.
     * If the new Direction is different from the old one, updates the active Animation.
     */
    public void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Cannot set the Direction to null.");
        if(this.direction == direction){
            return;
        }
        this.direction = direction;
        updateActiveAnimation();
    }

    private void updateActiveAnimation(){
        IAnimation<Texture> animation = animationRepo.getAnimation(AnimationKey.create(activityType, direction));
        this.activeAnimation = new InfiniteLoopAnimation<>(animation);
        this.deltaTime.reset();
    }

    public Texture getTexture(){
        return activeAnimation.getFrame(deltaTime.getDelta());
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private ActivityType activityType;
        private Direction direction;
        private IAnimationRepository<Texture> animationRepo;
        private IDeltaTime deltaTime;

        private Builder(){
        }

        public Builder activityType(ActivityType activityType){
            this.activityType = activityType;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
            return this;
        }

        public Builder animationRepository(IAnimationRepository<Texture> animationRepo){
            this.animationRepo = animationRepo;
            return this;
        }

        public Builder deltaTime(IDeltaTime deltaTime){
            this.deltaTime = deltaTime;
            return this;
        }

        public TextureSelector build(){
            Objects.requireNonNull(activityType, "Cannot create a TextureSelector with a null ActivityType.");
            Objects.requireNonNull(direction, "Cannot create a TextureSelector with a null Direction.");
            Objects.requireNonNull(animationRepo, "Cannot create a TextureSelector with a null IAnimationRepository.");
            Objects.requireNonNull(deltaTime, "Cannot create a TextureSelector with a null DeltaTime.");
            return new TextureSelector(this);
        }
    }
}
