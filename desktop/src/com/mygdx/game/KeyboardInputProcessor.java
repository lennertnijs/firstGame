package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.UP;

public class KeyboardInputProcessor implements InputProcessor {

    private final GameController gameController;
    public KeyboardInputProcessor(GameController gameController){
        this.gameController = gameController;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case UP: gameController.movePlayer(50d);
            case RIGHT: gameController.movePlayer(50d);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
