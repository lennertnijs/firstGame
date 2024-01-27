package com.mygdx.game;

//
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.*;
import com.mygdx.game.Clock.Calendar;
import com.mygdx.game.Controller.ClockController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.DAO.CalendarDAO;
import com.mygdx.game.Drawer.NPCDrawer;

public class GameScreen implements Screen {
    final MyGame game;
    Texture map;
    Texture character;
    Texture charRight;
    Texture charLeft;
    Rectangle characterRect;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle mapRect;
    Texture frame;

    Rectangle inventorySlot;

    Clock clock = createClock();
    ClockController clockController = new ClockController();
    NPCController npcController;
    NPCDrawer npcDrawer;



    /* Loads the game screen. Only is executed upon screen load */
    public GameScreen(final MyGame game) {
        this.game = game;

        npcDrawer = new NPCDrawer(game);
        npcController = new NPCController(clock, npcDrawer);
        npcController.loadNPCS();


        // load the images for the droplet and the bucket, 64x64 pixels each
        map = new Texture(Gdx.files.internal("images/map.png"));
        character = new Texture(Gdx.files.internal("images/guy.png"));
        charLeft = new Texture(Gdx.files.internal("images/guy_left.png"));
        charRight = new Texture(Gdx.files.internal("images/guy_right.png"));
        frame = new Texture(Gdx.files.internal("images/frame.png"));

        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        mapRect = new Rectangle();
        mapRect.width = 5000;
        mapRect.height = 2500;
        mapRect.x = 0;
        mapRect.y = 0;

        characterRect = new Rectangle();
        characterRect.height = 256;
        characterRect.width = 128;
        characterRect.x = (float) 1920 / 2;
        characterRect.y = 510;

        inventorySlot = new Rectangle();
        inventorySlot.height = 128;
        inventorySlot.width = 128;
        inventorySlot.x = (float) 1920 / 2;
        inventorySlot.y = 0;
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
        game.batch.draw(frame, inventorySlot.x, inventorySlot.y, inventorySlot.width, inventorySlot.height);
        clockController.updateClock(clock);
        game.font.getData().setScale(3, 3);
        game.font.draw(game.batch, clock.getTimeInHHMM(), 1700, 800);
        game.font.draw(game.batch, String.valueOf(clock.getDay()), 1700, 725);
        game.font.draw(game.batch, String.valueOf(clock.getSeason()), 1700, 650);
        game.font.draw(game.batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 1400, 900);
        movementInputs();
        game.batch.end();

        camera.position.set(characterRect.x, characterRect.y, 0);
    }


    private Clock createClock() {
        Calendar calendar = CalendarDAO.readCalendar();
        int minutes = 1420;
        int currentDaysInMonth = 12;
        return Clock.builder()
                .calendar(calendar)
                .season(Season.DARK)
                .dayOfTheSeason(currentDaysInMonth)
                .day(Day.MONDAY)
                .timeInMinutes(minutes)
                .build();
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

    private void movementInputs() {
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            characterRect.x -= 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.x -= 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(charLeft, characterRect.x, characterRect.y, characterRect.width, characterRect.height);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            characterRect.x += 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.x += 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(charRight, characterRect.x, characterRect.y, characterRect.width, characterRect.height);
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            characterRect.y -= 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.y -= 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(character, characterRect.x, characterRect.y, characterRect.width, characterRect.height);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            characterRect.y += 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.y += 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(character, characterRect.x, characterRect.y, characterRect.width, characterRect.height);
        }

        if (characterRect.x < 0) {
            characterRect.x = 0;
        }
        if (characterRect.x > 5000) {
            characterRect.x = 5000;
        }
        if (characterRect.y < 0) {
            characterRect.y = 0;
        }
        if (characterRect.y > 2500) {
            characterRect.y = 2500;
        }
    }
}
