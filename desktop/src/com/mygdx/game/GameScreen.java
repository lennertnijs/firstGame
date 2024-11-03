package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.clock.CalendarClock;
import com.mygdx.game.clock.Clock;
import com.mygdx.game.clock.SystemTimeProvider;
import com.mygdx.game.DAO.DefaultPlayerLoader;
import com.mygdx.game.input.KeyboardInputProcessor;
import com.mygdx.game.npc.week_schedule.Day;
import com.mygdx.game.npc.week_schedule.Time;


public class GameScreen implements Screen {
    final MyGame game;
    final OrthographicCamera camera;
    private final GameController gameController;
    private final Texture bar = new Texture(Gdx.files.internal("bar.png"));


    public GameScreen(MyGame game) {
        // to play music
        // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));rainMusic.setLooping(true);
        this.game = game;
        CalendarClock calendarClock = new CalendarClock(Day.MONDAY, new Time(4, 30));
        Clock gameClock = new Clock(calendarClock, new SystemTimeProvider());
        gameController = new GameController(DefaultPlayerLoader.load(), gameClock, new SpriteDrawer(game));
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(gameController));

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        Viewport viewport = new ExtendViewport(1920, 1080, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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
        game.batch.draw(bar, gameController.getPlayer().getPosition().x() - 72, gameController.getPlayer().getPosition().y() - 510);
        game.batch.end();
        camera.position.set(gameController.getPlayer().getPosition().x(), gameController.getPlayer().getPosition().y(), 0);
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
