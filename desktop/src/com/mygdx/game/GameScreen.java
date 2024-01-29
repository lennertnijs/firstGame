package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.*;
import com.mygdx.game.Controller.ClockController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Drawer.NPCDrawer;
import com.mygdx.game.Drawer.PlayerDrawer;
import com.mygdx.game.Drawer.PlayerTexturesRepo;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Input.KeyboardInputController;
import com.mygdx.game.Interactive.InteractiveController;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Player.Inventory;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Service.ClockService;
import com.mygdx.game.Service.NPCService;

import java.util.HashMap;

public class GameScreen implements Screen {
    final MyGame game;
    Texture map;
    Texture character;
    Rectangle characterRect;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle mapRect;


    // ===========================
    Clock clock;
    ClockController clockController;
    NPCController npcController;
    NPCDrawer npcDrawer;
    NPCService npcService;
    ClockService clockService;

    KeyboardInputController keyboardInput;
    Position position = Position.builder().x(960).y(510).build();
    Player player = Player.builder().position(position).name("Bart")
            .inventory(Inventory.builder().size(0).items(new ItemInstance[0]).build())
            .build();
    Texture[] texture;
    Animation<Texture> animation;
    float timePassed;

    InteractiveController interactiveController;
    PlayerTexturesRepo playerTexturesRepo;
    PlayerDrawer playerDrawer;



    /* Loads the game screen. Only is executed upon screen load */
    public GameScreen(final MyGame game) {
        this.game = game;

        texture = new Texture[4];

        clockService = new ClockService();
        npcService = new NPCService(clockService);

        interactiveController = new InteractiveController(npcService);
        npcDrawer = new NPCDrawer(game);
        clockController = new ClockController(clockService);
        npcController = new NPCController(npcService, npcDrawer);


        npcController.loadNPCS();
        clockController.loadClock();

        clock = clockController.getClock();


        // load the images for the droplet and the bucket, 64x64 pixels each
        map = new Texture(Gdx.files.internal("images/untitled.png"));
        character = new Texture(Gdx.files.internal("images/guy.png"));


        Texture frame1 = new Texture(Gdx.files.internal("images/lmaoxd.png"));
        Texture frame2 = new Texture(Gdx.files.internal("images/guy_right.png"));
        Texture frame3 = new Texture(Gdx.files.internal("images/guy.png"));
        Texture frame4 = new Texture(Gdx.files.internal("images/guy_left.png"));
        texture[0] = frame1;
        texture[1] = frame2;

        texture[2] = frame3;

        texture[3] = frame4;
        animation = new Animation<>(0.25F, texture);

        HashMap<Direction, Texture> map1 = new HashMap<>();
        map1.put(Direction.UP, frame1);
        map1.put(Direction.RIGHT, frame1);
        map1.put(Direction.DOWN, frame1);
        map1.put(Direction.LEFT, frame1);


        HashMap<Direction, Animation<Texture>> map2 = new HashMap<>();
        map2.put(Direction.UP, animation);
        map2.put(Direction.RIGHT, animation);
        map2.put(Direction.DOWN, animation);
        map2.put(Direction.LEFT, animation);

        playerTexturesRepo = PlayerTexturesRepo.builder().idleTextures(map1).movementAnimations(map2).build();
        playerDrawer = new PlayerDrawer(game, playerTexturesRepo);
        timePassed = 0;


        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        mapRect = new Rectangle();
        mapRect.width = 4800;
        mapRect.height = 4800;
        mapRect.x = 0;
        mapRect.y = 0;

        characterRect = new Rectangle();
        characterRect.height = 128;
        characterRect.width = 64;
        characterRect.x = (float) 1920 / 2;
        characterRect.y = 510;

        keyboardInput = new KeyboardInputController(player, npcController, interactiveController, playerDrawer);
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
        game.batch.draw(map, mapRect.x, mapRect.y, mapRect.width, mapRect.height);


        npcController.updateNPCs();
        clockController.updateClock();

        timePassed += Gdx.graphics.getDeltaTime();

        clockController.updateClock();
        keyboardInput.handleMovement();
        game.font.draw(game.batch, clock.getTimeInHHMM(), 1700, 800);
        game.font.draw(game.batch, String.valueOf(clock.getDay()), 1700, 725);
        game.font.draw(game.batch, String.valueOf(clock.getSeason()), 1700, 650);
        game.font.draw(game.batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 1400, 900);
        //game.batch.draw(character, player.getPosition().getX(), player.getPosition().getY(), characterRect.width, characterRect.height);
        //Texture texture = animation.getKeyFrame(timePassed, true);

        //game.batch.draw(texture, 200, 200);
        camera.position.set(player.getPosition().getX(), player.getPosition().getY(), 0);
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
