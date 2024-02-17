package com.mygdx.game.Tree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entity.Position;

import java.util.ArrayList;
import java.util.List;

public class BreakableDAO {

    protected BreakableDAO(){

    }

    protected List<Breakable> readBreakables(){
        JsonReader jsonReader = new JsonReader();
        JsonValue fileJson = jsonReader.parse(Gdx.files.internal("resources/breakables.json"));
        List<Breakable> breakables = new ArrayList<>();
        for(JsonValue breakableJson : fileJson){
            int x = breakableJson.getInt("x");
            int y = breakableJson.getInt("y");
            Position position = Position.builder().x(x).y(y).build();
            float health = breakableJson.getFloat("health");
            int hardness = breakableJson.getInt("hardness");
            BreakableType type = BreakableType.valueOf(breakableJson.getString("breakableType"));
            Breakable breakable = Breakable.create(position, health, hardness, type);
            breakables.add(breakable);
        }
        return breakables;
    }
}
