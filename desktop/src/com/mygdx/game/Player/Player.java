package com.mygdx.game.Player;

public class Player {

    private final String name;
    private final Inventory inventory;

    public Player(Builder builder){
        this.name = builder.name;
        this.inventory = builder.inventory;
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private Inventory inventory;

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

        public Player build(){

            return new Player(this);
        }

    }
}
