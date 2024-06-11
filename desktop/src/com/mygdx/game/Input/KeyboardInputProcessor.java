package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameController;
import com.mygdx.game.Keys.NPCActivityType;
import com.mygdx.game.Util.Direction;

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
                gameController.getMovementFlags().addDirection(Direction.UP);
                break;
            case RIGHT:
                gameController.getMovementFlags().addDirection(Direction.RIGHT);
                break;
            case DOWN:
                gameController.getMovementFlags().addDirection(Direction.DOWN);
                break;
            case LEFT:
                gameController.getMovementFlags().addDirection(Direction.LEFT);
                break;
            case Q:
                gameController.setActivityType(NPCActivityType.MINING);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case UP:
                gameController.getMovementFlags().removeDirection(Direction.UP);
                break;
            case RIGHT:
                gameController.getMovementFlags().removeDirection(Direction.RIGHT);
                break;
            case DOWN:
                gameController.getMovementFlags().removeDirection(Direction.DOWN);
                break;
            case LEFT:
                gameController.getMovementFlags().removeDirection(Direction.LEFT);
                break;
            case Q:
                gameController.removeActivityType();
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
