package game;

import game.DAO.PlayerLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import game.clock.CalendarClock;
import game.clock.Clock;
import game.clock.SystemTimeProvider;
import game.util.Day;
import game.npc.week_schedule.Time;


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
        gameController = new GameController(PlayerLoader.load(), gameClock, new SpriteDrawer(game));
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(gameController));

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
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
        game.font.draw(game.batch, String.format("fps: %d", Gdx.graphics.getFramesPerSecond()), 50, 50);
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
