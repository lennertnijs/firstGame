package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameController;
import com.mygdx.game.Util.Direction;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardInputProcessor implements InputProcessor {

    private final GameController gameController;

    public KeyboardInputProcessor(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Q -> gameController.playerUseActiveItem();
            case UP -> gameController.addDirection(Direction.UP);
            case RIGHT -> gameController.addDirection(Direction.RIGHT);
            case DOWN -> gameController.addDirection(Direction.DOWN);
            case LEFT -> gameController.addDirection(Direction.LEFT);
            case E -> gameController.interact();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case UP -> gameController.removeDirection(Direction.UP);
            case RIGHT -> gameController.removeDirection(Direction.RIGHT);
            case DOWN -> gameController.removeDirection(Direction.DOWN);
            case LEFT -> gameController.removeDirection(Direction.LEFT);
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
            gameController.playerSetNextActive();
        }
        return false;
    }
}
