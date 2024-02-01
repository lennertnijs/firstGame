package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

public class ItemDAO {


    public HashMap<Integer, Texture> readItems(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/items.json"));
        int id = 0;
        HashMap<Integer, Texture> items = new HashMap<>();
        for(JsonValue item: file){
            Texture texture = new Texture(item.getString("texture"));
            items.put(id++,texture);
        }
        return items;
    }
}
