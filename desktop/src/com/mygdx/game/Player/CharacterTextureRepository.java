package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Direction;

import java.util.Map;
import java.util.Objects;

public class CharacterTextureRepository {

    private final Map<Direction, TextureRegion> idleTextures;
    private final Map<Direction, Animation<TextureRegion>> movementAnimations;
    private final Map<Direction, Animation<TextureRegion>> miningAnimations;

    private float timeElapsed = 0;
    private boolean inAnimation = false;

    protected CharacterTextureRepository(Builder builder){
        this.movementAnimations = builder.movementAnimations;
        this.idleTextures = builder.idleTextures;
        this.miningAnimations = builder.miningAnimations;
    }

    /**
     * Returns the idle texture of the player for the given direction.
     */
    public TextureRegion getIdleTexture(Direction direction){
        return Objects.requireNonNull(idleTextures.get(direction), "No idle Texture was found for the Player");
    }

    /**
     * Returns the moving animation textures of the player for the given direction.
     */
    public Animation<TextureRegion> getMovingAnimation(Direction direction){
        Objects.requireNonNull(direction, "Cannot fetch moving textures for null direction");
        return movementAnimations.get(direction);
    }


    public Animation<TextureRegion> getMiningAnimation(Direction direction){
        return miningAnimations.get(direction);
    }


    public float getTimeElapsed(){
        return timeElapsed;
    }

    public boolean getInAnimation(){
        return inAnimation;
    }

    public void setTimeElapsed(float timeElapsed){
        this.timeElapsed = timeElapsed;
    }

    public void setInAnimation(boolean inAnimation){
        this.inAnimation = inAnimation;
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Map<Direction, TextureRegion> idleTextures;
        private Map<Direction, Animation<TextureRegion>> movementAnimations;
        private Map<Direction, Animation<TextureRegion>> miningAnimations;

        private Builder(){
        }

        public Builder idleTextures(Map<Direction, TextureRegion> idleTextures){
            this.idleTextures = idleTextures;
            return this;
        }

        public Builder movementAnimations(Map<Direction, Animation<TextureRegion>> movementAnimations){
            this.movementAnimations = movementAnimations;
            return this;
        }

        public Builder miningAnimations(Map<Direction, Animation<TextureRegion>> miningAnimations){
            this.miningAnimations = miningAnimations;
            return this;
        }

        public CharacterTextureRepository build(){
            checkValidMap(idleTextures);
            checkValidMap(movementAnimations);
            return new CharacterTextureRepository(this);

        }

        /**
         * Validates the given map.
         * The map cannot be null and cannot contain a null key or value.
         * The map should also have one mapping for each {@code Direction}
         */
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
