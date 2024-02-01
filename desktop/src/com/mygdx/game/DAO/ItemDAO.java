package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

public class ItemDAO {


    public HashMap<Integer, Texture> readItems(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse("resources/items.json");
        int id = 0;
        HashMap<Integer, Texture> items = new HashMap<>();
        for(JsonValue item: file){
            String itemPath = item.getString("texture");
            Texture texture = new Texture(Gdx.files.internal(itemPath));
            items.put(id++,texture);
        }
        return items;
    }
}
