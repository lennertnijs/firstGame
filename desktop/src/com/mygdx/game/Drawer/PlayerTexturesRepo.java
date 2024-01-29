package com.mygdx.game.Drawer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;

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
        return Objects.requireNonNull(idleTextures.get(direction), "No idle Texture was found for the Player");
    }

    public Animation<Texture> getMovementAnimation(Direction direction){
        return Objects.requireNonNull(movementAnimations.get(direction), "No movement Animation was found for the Player");
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Map<Direction, Texture> idleTextures;
        private Map<Direction, Animation<Texture>> movementAnimations;

        private Builder(){
        }

        public Builder idleTextures(Map<Direction, Texture> idleTextures){
            this.idleTextures = idleTextures;
            return this;
        }

        public Builder movementAnimations(Map<Direction, Animation<Texture>> movementAnimations){
            this.movementAnimations = movementAnimations;
            return this;
        }

        public PlayerTexturesRepo build(){
            checkValidMap(idleTextures);
            checkValidMap(movementAnimations);
            return new PlayerTexturesRepo(this);

        }

        private void checkValidMap(Map<Direction, ?> map){
            int amountOfDirections = Direction.values().length;
            String invalidMapMessage = "Couldn't build the playerTexture repository because the map is invalid";
            Objects.requireNonNull(map, invalidMapMessage);
            if(map.size() != amountOfDirections){
                throw new IllegalArgumentException(invalidMapMessage);
            }
            for(Direction direction : map.keySet()){
                Objects.requireNonNull(direction, invalidMapMessage);
                Objects.requireNonNull(map.get(direction), invalidMapMessage);
            }
        }
    }
}
