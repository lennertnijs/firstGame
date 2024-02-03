package com.mygdx.game.Player;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Item.Item;
import com.mygdx.game.Direction;
import com.mygdx.game.NPC.Activity;

import java.util.Objects;

public class Player {

    private final String name;
    private Position position;
    private final Inventory inventory;
    private int currentItemIndex;
    private boolean doingAnimation = false;
    private Activity activity = Activity.IDLING;
    private Direction direction;
    private final CharacterTextureRepository textureRepository;

    public Player(Builder builder){
        this.name = builder.name;
        this.position = builder.position;
        this.inventory = builder.inventory;
        this.currentItemIndex = builder.currentItemIndex;
        this.direction = builder.direction;
        this.textureRepository = builder.textureRepository;

    }

    public String getName(){
        return name;
    }

    public Position getPosition(){
        return position;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Item getCurrentItem(){
        return inventory.getItems()[currentItemIndex];
    }

    public boolean getDoingAnimation(){
        return doingAnimation;
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

    protected void setPosition(Position position){
        Objects.requireNonNull(position, "Cannot set player position to null");
        this.position = position;
    }

    protected void setCurrentItemIndex(int index){
        if(index < 0 || index > inventory.getSize()){
            throw new IllegalArgumentException("Cannot set the active item index to invalid value");
        }
        this.currentItemIndex = index;
    }

    protected void setDoingAnimation(boolean bool){
        doingAnimation = bool;
    }

    protected void setActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot set player activity to null");
        this.activity = activity;
    }

    protected void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Cannot se the player direction to null");
        this.direction = direction;
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private Inventory inventory;
        private Position position;
        private int currentItemIndex;
        private Direction direction;

        private CharacterTextureRepository textureRepository;

        private Builder(){
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

        public Builder currentItemIndex(int currentItemIndex){
            this.currentItemIndex = currentItemIndex;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
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
            if(currentItemIndex > inventory.getSize() || currentItemIndex < 0){
                throw new IllegalArgumentException("The index of the current item is invalid");
            }
            Objects.requireNonNull(textureRepository, "The texture repository must not be null.");
            return new Player(this);
        }
    }
}
