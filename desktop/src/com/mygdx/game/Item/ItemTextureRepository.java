package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DAO.ItemDAO;

import java.util.HashMap;
import java.util.Objects;

public class ItemTextureRepository {

    private final HashMap<Integer, Texture> itemTextures;

    protected ItemTextureRepository(){
        this.itemTextures = new ItemDAO().readItems();
    }

    protected Texture getItemTexture(int itemId){
        Texture texture = itemTextures.get(itemId);
        Objects.requireNonNull(texture, "No texture was found with the given id");
        return texture;
    }
}
