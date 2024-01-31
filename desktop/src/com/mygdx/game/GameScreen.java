package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Controller.PlayerController;
import com.mygdx.game.Drawer.DrawerController;
import com.mygdx.game.Stone.StoneController;
import com.mygdx.game.Clock.*;
import com.mygdx.game.Controller.ClockController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Input.KeyboardInputController;
import com.mygdx.game.Interactive.InteractiveController;
import com.mygdx.game.Service.ClockService;
import com.mygdx.game.Service.NPCService;



public class GameScreen implements Screen {
    final MyGame game;
    Music rainMusic;
    OrthographicCamera camera;

    Clock clock;
    ClockController clockController;
    NPCController npcController;

    NPCService npcService;
    ClockService clockService;

    KeyboardInputController keyboardInput;

    InteractiveController interactiveController;
    StoneController stoneController;
    PlayerController playerController;
    DrawerController drawerController;



    /* Loads the game screen. Only is executed upon screen load */
    public GameScreen(final MyGame game) {

        // Build player drawer, then finish npc Controller to remove npc service. Then create drawerController
        // with all the controller. DrawerController should initiate all drawer classes.
        // Thus, after refactor the render() should just call drawerController.drawAll();

        this.game = game;

        clockService = new ClockService();
        npcService = new NPCService(clockService);

        clockController = new ClockController(clockService);
        npcController = new NPCController(npcService);
        playerController  = new PlayerController();
        stoneController = new StoneController();
        interactiveController = new InteractiveController(npcController, stoneController);


        clockController.loadClock();

        clock = clockController.getClock();

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
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
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
