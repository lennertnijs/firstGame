package com.mygdx.game.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class GamePlayInputProcessor implements InputProcessor {

    private final PlayerMovementHandler playerMovementHandler;
    private final PlayerInteractHandler playerInteractHandler;


    public GamePlayInputProcessor(PlayerMovementHandler playerMovementHandler, PlayerInteractHandler playerInteractHandler){
        this.playerMovementHandler = playerMovementHandler;
        this.playerInteractHandler = playerInteractHandler;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.Q:
                playerInteractHandler.isMining();
                break;
            case Keys.UP:
                playerMovementHandler.movingUp();
                break;
            case Keys.RIGHT:
                playerMovementHandler.movingRight();
                break;
            case Keys.DOWN:
                playerMovementHandler.movingDown();
                break;
            case Keys.LEFT:
                playerMovementHandler.movingLeft();
                break;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Keys.UP:
                playerMovementHandler.notMovingUp();
                break;
            case Keys.RIGHT:
                playerMovementHandler.notMovingRight();
                break;
            case Keys.DOWN:
                playerMovementHandler.notMovingDown();
                break;
            case Keys.LEFT:
                playerMovementHandler.notMovingLeft();
                break;
            case Keys.Q:
                playerInteractHandler.isNotMining();
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
