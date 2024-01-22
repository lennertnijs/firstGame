package com.mygdx.game.NPC;

public class Activity {

    final int x;
    final int y;
    final String time;
    final String animation;
    final String screen;

    /**
     * The {@code Activity} constructor
     * Builds a valid {@code Activity} with a {@code Builder}
     */
    public Activity(Builder builder){
        this.x = builder.x;
        this.y = builder.y;
        this.time = builder.time;
        this.animation = builder.animation;
        this.screen = builder.screen;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getTime(){
        return this.time;
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

        public int x;
        public int y;
        public String time;
        public String animation;
        public String screen;

        public Builder(){

        }
        public Builder x(int x){
            this.x = x;
            return this;
        }

        public Builder y(int y){
            this.y = y;
            return this;
        }

        public Builder time(String time){
            this.time = time;
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
            if(screen == null || animation == null || time == null){
                throw new IllegalArgumentException("No screen or animation can be null.");
            }
            return new Activity(this);
        }
    }
}
