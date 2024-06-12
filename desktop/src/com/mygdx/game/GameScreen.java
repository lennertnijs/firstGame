package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.CalendarClock;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.SystemTimeProvider;
import com.mygdx.game.DAO.*;
import com.mygdx.game.Input.KeyboardInputProcessor;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Collections;


public class GameScreen implements Screen {
    final MyGame game;
    final OrthographicCamera camera;
    private final GameController gameController;

    public GameScreen(MyGame game) {
        // to play music
        // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));rainMusic.setLooping(true);
        this.game = game;
        CalendarClock calendarClock = new CalendarClock(Day.MONDAY, new Time(4, 30));
        Clock gameClock = new Clock(calendarClock, new SystemTimeProvider());

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        GameObjectRepository repository = new GameObjectRepository(Collections.singletonList(NPCCreator.create()), MapLoader.loadAll(), HouseLoader.load());
        PlayerController playerController = new PlayerController(DefaultPlayerLoader.load());
        gameController = new GameController(repository, gameClock, new SpriteDrawer(game), playerController);
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(playerController));
    }

    /**
     * This method is executed with every frame.
     *         // camera.position.set(0, 0, 0);
     */
    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        gameController.update();
        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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
        // dispose of game things, like music, etc.
    }
}
