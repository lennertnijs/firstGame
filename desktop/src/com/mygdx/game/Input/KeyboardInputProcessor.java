package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.PlayerController;
import com.mygdx.game.Util.Direction;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardInputProcessor implements InputProcessor {

    private final PlayerController playerController;

    public KeyboardInputProcessor(PlayerController playerController){
        this.playerController = playerController;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Q:
                playerController.useActiveItem();
                break;
            case UP:
                playerController.getMovementFlags().addDirection(Direction.UP);
                break;
            case RIGHT:
                playerController.getMovementFlags().addDirection(Direction.RIGHT);
                break;
            case DOWN:
                playerController.getMovementFlags().addDirection(Direction.DOWN);
                break;
            case LEFT:
                playerController.getMovementFlags().addDirection(Direction.LEFT);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case UP:
                playerController.getMovementFlags().removeDirection(Direction.UP);
                break;
            case RIGHT:
                playerController.getMovementFlags().removeDirection(Direction.RIGHT);
                break;
            case DOWN:
                playerController.getMovementFlags().removeDirection(Direction.DOWN);
                break;
            case LEFT:
                playerController.getMovementFlags().removeDirection(Direction.LEFT);
                break;
            case Q:
                playerController.removeCurrentActivity();
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
        if(amountY == -1){
            playerController.setNextActive();
        }
        return false;
    }
}
