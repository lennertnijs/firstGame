package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.StaticTexture;
import com.mygdx.game.GameObject.Transform;
import com.mygdx.game.Util.Vec2;

import java.util.ArrayList;
import java.util.List;

public class HouseLoader {

    public static List<GameObject> load(){
        List<GameObject> houses = new ArrayList<>();
        Renderer renderer = new StaticTexture(new TextureRegion(new Texture(Gdx.files.internal("houses/house1.png"))));
        Transform transform = new Transform(new Vec2(500, 500), 0);
        houses.add(new GameObject(transform, renderer, "Main"));
        return houses;
    }
}
