package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGame;

public class KeyboardInputController {

    private final OrthographicCamera camera;
    private final Rectangle rectangle;

    public KeyboardInputController(OrthographicCamera camera, Rectangle rectangle){
        this.camera = camera;
        this.rectangle = rectangle;

    }

    public void handleMovement(){
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rectangle.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rectangle.x += 200 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            rectangle.y += 200 * Gdx.graphics.getDeltaTime();
        }

        if (rectangle.x < 0) {
            rectangle.x = 0;
        }
        if (rectangle.x > 5000) {
            rectangle.x = 5000;
        }
        if (rectangle.y < 0) {
            rectangle.y = 0;
        }
        if (rectangle.y > 2500) {
            rectangle.y = 2500;
        }
        camera.position.set(rectangle.x, rectangle.y, 0);
    }
}
