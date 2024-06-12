package com.mygdx.game.ItemTemplate;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;


public class ItemTemplate {

    private final String name;
    private final String description;
    private final Texture texture;
    private final int stackSize;

    public ItemTemplate(String name, String description, Texture texture, int stackSize){
        Objects.requireNonNull(name, "Name is null.");
        Objects.requireNonNull(description, "Description is null.");
        //Objects.requireNonNull(texture, "Texture is null.");
        if(stackSize <= 0)
            throw new IllegalArgumentException("Stack size is negative or zero.");
        this.name = name;
        this.description = description;
        this.texture = texture;
        this.stackSize = stackSize;
    }

    public String name(){
        return name;
    }

    public String description(){
        return description;
    }

    public Texture texture(){
        return texture;
    }

    public int stackSize(){
        return stackSize;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ItemTemplate))
            return false;
        ItemTemplate template = (ItemTemplate) other;
        return name.equals(template.name) && description.equals(template.description) &&
                texture.equals(template.texture) && stackSize == template.stackSize;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = result * 31 + description.hashCode();
        result = result * 31 + texture.hashCode();
        result = result * 31 + stackSize;
        return result;
    }

    @Override
    public String toString(){
        return String.format("ItemTemplate[name=%s, description=%s, texture=%s, stackSize=%d]",
                name, description, texture, stackSize);
    }
}
