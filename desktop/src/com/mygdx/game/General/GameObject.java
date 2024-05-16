package com.mygdx.game.General;

import java.util.Objects;

public class GameObject {

    public final Sprite sprite;

    public GameObject(Sprite sprite){
        this.sprite = Objects.requireNonNull(sprite, "Sprite is null.");
    }
}
