package com.mygdx.game.Tree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entity.Position;

import java.util.ArrayList;
import java.util.List;

public class TreeDAO {

    protected TreeDAO(){

    }

    protected List<Breakable> readTrees(){
        JsonReader jsonReader = new JsonReader();
        JsonValue file = jsonReader.parse(Gdx.files.internal("resources/tree.json"));

        List<Breakable> breakables = new ArrayList<>();
        for(JsonValue treeJson : file){
            int x = treeJson.getInt("x");
            int y = treeJson.getInt("y");
            Position position = Position.builder().x(x).y(y).build();
            float health = treeJson.getFloat("health");
            int hardness = treeJson.getInt("hardness");
            breakables.add(Breakable.create(position, health, hardness, BreakableType.TREE));
        }
        return breakables;
    }
}
