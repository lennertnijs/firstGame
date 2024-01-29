package com.mygdx.game.Drawer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerTexturesRepo {

    private final Map<Direction, Texture> idleTextures;
    private final Map<Direction, Animation<Texture>> movementAnimations;

    public PlayerTexturesRepo(Builder builder){
        this.movementAnimations = builder.movementAnimations;
        this.idleTextures = builder.idleTextures;
    }

    public Texture getIdleTexture(Direction direction){
        Objects.requireNonNull("Cannot return the idle Texture because the Direction is null");
        return Objects.requireNonNull(idleTextures.get(direction));
    }

    public Animation<Texture> getMovementAnimation(Direction direction){
        return movementAnimations.get(direction);
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private HashMap<Direction, Animation<Texture>> movementAnimations;

        private HashMap<Direction, Texture> idleTextures;
        public Builder(){

        }

        public Builder movementAnimations(HashMap<Direction, Animation<Texture>> movementAnimations){
            this.movementAnimations = movementAnimations;
            return this;
        }

        public Builder idleTextures(HashMap<Direction, Texture> idleTextures){
            this.idleTextures = idleTextures;
            return this;
        }

        public PlayerTexturesRepo build(){
            Objects.requireNonNull(movementAnimations);
            Objects.requireNonNull(idleTextures);
            // add more checks
            return new PlayerTexturesRepo(this);
        }
    }
}
