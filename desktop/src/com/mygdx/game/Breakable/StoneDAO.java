package com.mygdx.game.Breakable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entity.Position;

import java.util.ArrayList;
import java.util.List;

public class StoneDAO {

    protected StoneDAO(){
    }

    protected List<Stone> loadStones(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/stone.json"));

        List<Stone> stones = new ArrayList<>();
        for(JsonValue stoneJson: file){
            int x = stoneJson.getInt("x");
            int y = stoneJson.getInt("y");
            Position position = Position.builder().x(x).y(y).build();
            float health = stoneJson.getFloat("health");
            int hardness = stoneJson.getInt("hardness");
            Stone stone = Stone.builder().position(position).healthPoints(health).hardness(hardness).build();
            stones.add(stone);
        }
        return stones;
    }
}
