package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Service.NPCService;

public class KeyboardInputController {

    private final OrthographicCamera camera;
    private final Rectangle rectangle;
    private final NPCController npcController;

    public KeyboardInputController(OrthographicCamera camera, Rectangle rectangle, NPCController npcController){
        this.camera = camera;
        this.rectangle = rectangle;
        this.npcController = npcController;

    }

    public void handleMovement(){
        int movement = (int)(200 * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        };
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Position position = Position.builder().x((int) (rectangle.x - movement)).y((int) rectangle.y).build();
            boolean collides = npcController.checkCollision(position);
            if(!collides){
                rectangle.x -= 200 * Gdx.graphics.getDeltaTime();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Position position = Position.builder().x((int) (rectangle.x + movement)).y((int) rectangle.y).build();
            boolean collides = npcController.checkCollision(position);
            if(!collides){
                rectangle.x += 200 * Gdx.graphics.getDeltaTime();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            Position position = Position.builder().x((int) (rectangle.x)).y((int) rectangle.y - movement).build();
            boolean collides = npcController.checkCollision(position);
            if(!collides){
                rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            Position position = Position.builder().x((int) (rectangle.x)).y((int) rectangle.y + movement).build();
            boolean collides = npcController.checkCollision(position);
            if(!collides){
                rectangle.y += 200 * Gdx.graphics.getDeltaTime();
            }
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
