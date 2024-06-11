package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameController;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardInputProcessor implements InputProcessor {

    private final GameController gameController;

    public KeyboardInputProcessor(GameController gameController){
        this.gameController = gameController;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case UP:
                gameController.getMovementFlags().setFlagUp(true);
                break;
            case RIGHT:
                gameController.getMovementFlags().setFlagRight(true);
                break;
            case DOWN:
                gameController.getMovementFlags().setFlagDown(true);
                break;
            case LEFT:
                gameController.getMovementFlags().setFlagLeft(true);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case UP:
                gameController.getMovementFlags().setFlagUp(false);
                break;
            case RIGHT:
                gameController.getMovementFlags().setFlagRight(false);
                break;
            case DOWN:
                gameController.getMovementFlags().setFlagDown(false);
                break;
            case LEFT:
                gameController.getMovementFlags().setFlagLeft(false);
                break;
        }
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
