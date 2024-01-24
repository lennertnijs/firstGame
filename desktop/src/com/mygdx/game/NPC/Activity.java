package com.mygdx.game.NPC;

public class Activity {

    final Position2D location;
    final int timeInMinutes;
    final String animation;
    final String screen;

    /**
     * The {@code Activity} constructor
     * Builds a valid {@code Activity} with a {@code Builder}
     */
    public Activity(Builder builder){
        this.location = builder.location;
        this.timeInMinutes = builder.timeInMinutes;
        this.animation = builder.animation;
        this.screen = builder.screen;
    }
    public Position2D getLocation(){
        return this.location;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public String getAnimation(){
        return this.animation;
    }

    public String getScreen(){
        return this.screen;
    }

    /**
     * @return A static {@code Builder} for an {@code Activity}
     */
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position2D location;
        public int timeInMinutes;
        public String animation;
        public String screen;

        public Builder(){

        }

        public Builder location(Position2D location){
            this.location = location;
            return this;
        }

        public Builder timeInMinutes(int timeInMinutes){
            this.timeInMinutes = timeInMinutes;
            return this;
        }

        public Builder animation(String animation){
            this.animation = animation;
            return this;
        }

        public Builder screen(String screen){
            this.screen = screen;
            return this;
        }

        /**
         * Builds a valid {@code Activity} instance.
         *
         * @return A valid {@code Activity} instance.
         */
        public Activity build(){
            if(screen == null || animation == null){
                throw new IllegalArgumentException("No screen or animation can be null.");
            }
            return new Activity(this);
        }
    }
}
