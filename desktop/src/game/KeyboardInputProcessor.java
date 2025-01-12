package game;

import com.badlogic.gdx.InputProcessor;
import game.util.Direction;
import game.util.Vec2;

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
            case E -> gameController.interact();
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
        gameController.moveWithClick(new Vec2(screenX, 1080 - screenY));
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
