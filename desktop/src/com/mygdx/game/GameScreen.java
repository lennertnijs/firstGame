package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.CalendarClock;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.SystemTimeProvider;
import com.mygdx.game.DAO.DefaultPlayerLoader;
import com.mygdx.game.DAO.HouseLoader;
import com.mygdx.game.DAO.MapLoader;
import com.mygdx.game.DAO.NPCCreator;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Collections;


public class GameScreen implements Screen {
    final MyGame game;
    final OrthographicCamera camera;
    private final NPC npc;
    private final Clock gameClock;
    private final SpriteDrawer drawer;
    private final GameController gameController;

    public GameScreen(MyGame game) {
        // to play music
        // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));rainMusic.setLooping(true);
        this.game = game;
        npc = NPCCreator.create();
        CalendarClock calendarClock = new CalendarClock(Day.MONDAY, new Time(4, 30));
        gameClock = new Clock(calendarClock, new SystemTimeProvider());

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        drawer = new SpriteDrawer(game);
        GameObjectRepository repository = new GameObjectRepository(Collections.singletonList(npc), DefaultPlayerLoader.load(), MapLoader.loadAll(), HouseLoader.load());
        gameController = new GameController(repository, gameClock, game);
        Gdx.input.setInputProcessor(new KeyboardInputProcessor(gameController));
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
        game.font.draw(game.batch, gameClock.getTime().toString(), 500, 500);
        drawer.draw(npc);
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
