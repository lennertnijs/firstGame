package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.ClockService;
import com.mygdx.game.Drawer.DrawerGod;
import com.mygdx.game.Player.PlayerController;
import com.mygdx.game.Player.PlayerService;
import com.mygdx.game.Controller.StoneController;
import com.mygdx.game.Clock.ClockController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Input.KeyboardInputController;


public class GameScreen implements Screen {
    final MyGame game;
    Music rainMusic;
    OrthographicCamera camera;

    ClockController clockController;
    KeyboardInputController keyboardInput;
    NPCController npcController;
    PlayerController playerController;
    StoneController stoneController;
    DrawerGod drawerGod;
    ClockService clockService;

    public GameScreen(final MyGame game) {

        this.game = game;

        PlayerService playerService = new PlayerService();
        clockService = new ClockService();
        clockController = new ClockController(clockService);
        npcController = new NPCController(clockController, clockService);
        playerController  = new PlayerController(playerService);
        stoneController = new StoneController();
        drawerGod = new DrawerGod(game, npcController, playerController, stoneController, clockController);


        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        keyboardInput = new KeyboardInputController(stoneController, npcController, playerService);
    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        npcController.updateNPCs();
        clockService.updateClock();

        keyboardInput.handleMovement();
        drawerGod.drawAll();

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
