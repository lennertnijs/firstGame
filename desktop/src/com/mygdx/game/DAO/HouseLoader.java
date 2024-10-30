package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Renderer.StaticTexture;
import com.mygdx.game.Util.Point;

import java.util.ArrayList;
import java.util.List;

public class HouseLoader {

    public static List<GameObject> load(){
        List<GameObject> houses = new ArrayList<>();
        Renderer renderer = new StaticTexture(new TextureRegion(new Texture(Gdx.files.internal("houses/house1.png"))));
        houses.add(new GameObject(renderer, new Point(500, 500), "Main"));
        return houses;
    }
}
