package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Player.IdlePlayerState;
import com.mygdx.game.Player.PlayerWalkState;
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
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new PlayerWalkState(playerController.getPlayer()));
                }
                playerController.getMovementFlags().addDirection(Direction.UP);
                break;
            case RIGHT:
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new PlayerWalkState(playerController.getPlayer()));
                }
                playerController.getMovementFlags().addDirection(Direction.RIGHT);
                break;
            case DOWN:
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new PlayerWalkState(playerController.getPlayer()));
                }
                playerController.getMovementFlags().addDirection(Direction.DOWN);
                break;
            case LEFT:
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new PlayerWalkState(playerController.getPlayer()));
                }
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
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new IdlePlayerState(playerController.getPlayer()));
                }
                break;
            case RIGHT:
                playerController.getMovementFlags().removeDirection(Direction.RIGHT);
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new IdlePlayerState(playerController.getPlayer()));
                }
                break;
            case DOWN:
                playerController.getMovementFlags().removeDirection(Direction.DOWN);
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new IdlePlayerState(playerController.getPlayer()));
                }
                break;
            case LEFT:
                playerController.getMovementFlags().removeDirection(Direction.LEFT);
                if(playerController.getMovementFlags().getCurrentDirection() == null){
                    playerController.changePlayerState(new IdlePlayerState(playerController.getPlayer()));
                }
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
