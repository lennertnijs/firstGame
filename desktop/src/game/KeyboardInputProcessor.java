package game;

import com.badlogic.gdx.InputProcessor;
import game.util.Direction;

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
            case W -> gameController.addDirection(Direction.UP);
            case D -> gameController.addDirection(Direction.RIGHT);
            case S -> gameController.addDirection(Direction.DOWN);
            case A -> gameController.addDirection(Direction.LEFT);
            case E -> gameController.interact();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case W -> gameController.removeDirection(Direction.UP);
            case D -> gameController.removeDirection(Direction.RIGHT);
            case S -> gameController.removeDirection(Direction.DOWN);
            case A -> gameController.removeDirection(Direction.LEFT);
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
            gameController.scrollPlayerActiveItemIndex(Direction.DOWN);
        }
        if(amountY == 1){
            gameController.scrollPlayerActiveItemIndex(Direction.UP);
        }
        return false;
    }
}
