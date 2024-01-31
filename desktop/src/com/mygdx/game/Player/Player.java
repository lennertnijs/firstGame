package com.mygdx.game.Player;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Direction;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.TextureRepository.CharacterTextureRepository;

import java.util.Objects;

public class Player {

    private final String name;
    private final Inventory inventory;
    private final int currentItemIndex;
    private Position position;
    private boolean doingAnimation = false;
    private Activity activity = Activity.IDLING;
    private final Direction direction = Direction.DOWN;

    private final CharacterTextureRepository textureRepository;

    public Player(Builder builder){
        this.name = builder.name;
        this.inventory = builder.inventory;
        this.position = builder.position;
        this.currentItemIndex = builder.currentItemIndex;
        this.textureRepository = builder.textureRepository;
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Position getPosition(){
        return position;
    }

    public int getCurrentItemIndex(){
        return currentItemIndex;
    }

    public ItemInstance getCurrentItem(){
        return inventory.getItems()[currentItemIndex];
    }

    public void setPosition(Position position){
        Objects.requireNonNull(position);
        this.position = position;
    }

    public boolean getDoingAnimation(){
        return doingAnimation;
    }
    public void setDoingAnimation(boolean bool){
        doingAnimation = bool;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity(){
        return activity;
    }


    public Direction getDirection(){
        return direction;
    }

    public CharacterTextureRepository getTextureRepository(){
        return textureRepository;
    }





    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private Inventory inventory;
        private Position position;
        private int currentItemIndex;

        private CharacterTextureRepository textureRepository;

        public Builder(){

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder inventory(Inventory inventory){
            this.inventory = inventory;
            return this;
        }

        public Builder position(Position position){
            this.position = position;
            return this;
        }

        public Builder textureRepository(CharacterTextureRepository textureRepository){
            this.textureRepository = textureRepository;
            return this;
        }

        public Player build(){
            Objects.requireNonNull(name, "The name of the player must not be null");
            Objects.requireNonNull(inventory, "The inventory of the player must not be null");
            Objects.requireNonNull(position, "The position of the player must not be null");
            if(currentItemIndex > inventory.getSize()){
                throw new IllegalArgumentException("error");
            }
            return new Player(this);
        }

    }
}
