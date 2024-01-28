package com.mygdx.game.Player;

import com.mygdx.game.Entity.Position;

import java.util.Objects;

public class Player {

    private final String name;
    private final Inventory inventory;
    private Position position;

    public Player(Builder builder){
        this.name = builder.name;
        this.inventory = builder.inventory;
        this.position = builder.position;
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

    public void setPosition(Position position){
        Objects.requireNonNull(position);
        this.position = position;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private Inventory inventory;
        private Position position;

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

        public Player build(){
            Objects.requireNonNull(name, "The name of the player must not be null");
            Objects.requireNonNull(inventory, "The inventory of the player must not be null");
            Objects.requireNonNull(position, "The position of the player must not be null");
            return new Player(this);
        }

    }
}
