package com.mygdx.game.Stone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entity.Position;

import java.util.ArrayList;
import java.util.List;

public class TreeDAO {

    protected TreeDAO(){

    }

    protected List<Tree> readTrees(){
        JsonReader jsonReader = new JsonReader();
        JsonValue file = jsonReader.parse(Gdx.files.internal("resources/tree.json"));

        List<Tree> trees = new ArrayList<>();
        for(JsonValue treeJson : file){
            int x = treeJson.getInt("x");
            int y = treeJson.getInt("y");
            Position position = Position.builder().x(x).y(y).build();
            float health = treeJson.getFloat("health");
            int hardness = treeJson.getInt("hardness");
            trees.add(Tree.create(position, health, hardness));
        }
        return trees;
    }
}
