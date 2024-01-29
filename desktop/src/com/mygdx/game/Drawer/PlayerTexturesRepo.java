package com.mygdx.game.Drawer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.MovementDirection;

import java.util.HashMap;
import java.util.Objects;

public class PlayerTexturesRepo {

    private final HashMap<MovementDirection, Animation<Texture>> movementAnimations;

    private final HashMap<MovementDirection, Texture> idleAnimation;

    public PlayerTexturesRepo(Builder builder){
        this.movementAnimations = builder.movementAnimations;
        this.idleAnimation = builder.idleAnimation;
    }

    public Texture getIdleTexture(MovementDirection direction){
        return idleAnimation.get(direction);
    }

    public Animation<Texture> getMovementAnimation(MovementDirection direction){
        return movementAnimations.get(direction);
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private HashMap<MovementDirection, Animation<Texture>> movementAnimations;

        private HashMap<MovementDirection, Texture> idleAnimation;
        public Builder(){

        }

        public Builder movementAnimations(HashMap<MovementDirection, Animation<Texture>> movementAnimations){
            this.movementAnimations = movementAnimations;
            return this;
        }

        public Builder idleAnimation(HashMap<MovementDirection, Texture> idleAnimation){
            this.idleAnimation = idleAnimation;
            return this;
        }

        public PlayerTexturesRepo build(){
            Objects.requireNonNull(movementAnimations);
            Objects.requireNonNull(idleAnimation);
            // add more checks
            return new PlayerTexturesRepo(this);
        }
    }
}
