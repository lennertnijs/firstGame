package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.ArrayList;
import java.util.List;

public class HouseLoader {

    public static List<GameObject> load(){
        List<GameObject> houses = new ArrayList<>();
        houses.add(new GameObject(new TextureRegion(new Texture(Gdx.files.internal("houses/house1.png"))),
                new Point(500, 500), new Dimensions(500, 500), "Main"));
        houses.add(new GameObject(new TextureRegion(new Texture(Gdx.files.internal("houses/house2.jpg"))),
                new Point(1000, 500), new Dimensions(500, 500), "Main"));
        return houses;
    }
}
