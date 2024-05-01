package com.mygdx.game.V2.Inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Objects;

public final class ItemTemplate {

    private final String name;
    private final String description;
    private final TextureRegion texture;
    private final int maxStackSize;

    public ItemTemplate(String name, String description, TextureRegion texture, int maxStackSize){
        Objects.requireNonNull(name, "Name is null.");
        Objects.requireNonNull(description, "Description is null.");
        Objects.requireNonNull(texture, "Texture is null.");
        if(maxStackSize <= 0)
            throw new IllegalArgumentException("Max stack size is negative or zero.");
        this.name = name;
        this.description = description;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
    }

    public String name(){
        return name;
    }

    public String description(){
        return description;
    }

    public TextureRegion texture(){
        return texture;
    }

    public int maxStackSize(){
        return maxStackSize;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ItemTemplate))
            return false;
        ItemTemplate template = (ItemTemplate) other;
        return name.equals(template.name) && description.equals(template.description) &&
                texture.equals(template.texture) && maxStackSize == template.maxStackSize;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = result * 31 + description.hashCode();
        result = result * 31 + texture.hashCode();
        result = result * 31 + maxStackSize;
        return result;
    }

    @Override
    public String toString(){
        return String.format("ItemTemplate[name=%s, description=%s, texture=%s, maxStackSize=%d]",
                name, description, texture, maxStackSize);
    }
}
