package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Controller.PlayerController;
import com.mygdx.game.Drawer.DrawerController;
import com.mygdx.game.Stone.StoneController;
import com.mygdx.game.Controller.ClockController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Input.KeyboardInputController;
import com.mygdx.game.Interactive.InteractiveController;



public class GameScreen implements Screen {
    final MyGame game;
    Music rainMusic;
    OrthographicCamera camera;

    ClockController clockController;
    KeyboardInputController keyboardInput;
    InteractiveController interactiveController;
    NPCController npcController;
    PlayerController playerController;
    StoneController stoneController;
    DrawerController drawerController;

    public GameScreen(final MyGame game) {

        this.game = game;

        clockController = new ClockController();
        npcController = new NPCController(clockController);
        playerController  = new PlayerController();
        stoneController = new StoneController();
        interactiveController = new InteractiveController(npcController, stoneController);
        drawerController = new DrawerController(game, npcController, playerController, stoneController);


        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        keyboardInput = new KeyboardInputController(stoneController, npcController, interactiveController, playerController);
    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        npcController.updateNPCs();
        clockController.updateClock();

        keyboardInput.handleMovement();
        drawerController.drawAll();

        camera.position.set(playerController.getPlayer().getPosition().getX(), playerController.getPlayer().getPosition().getY(), 0);
        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // use for textures etc.
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        rainMusic.dispose();
    }
}
