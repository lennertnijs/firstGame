package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.CalendarClock;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Clock.SystemTimeProvider;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Arrays;


public class GameScreen implements Screen {
    final MyGame game;
    final OrthographicCamera camera;
    private final NPC npc;
    private final Clock gameClock;
    private final SpriteDrawer drawer;
    private final GameObjectRepository repository;

    public GameScreen(MyGame game) {
        // Implements InputProcessor to make an input handler.
        // Gdx.input.setInputProcessor(gamePlayInputProcessor); to then set it.

        // to play music
        // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        // rainMusic.setLooping(true);
        this.game = game;
        npc = NPCCreator.create();
        CalendarClock calendarClock = new CalendarClock(Day.MONDAY, new Time(4, 30));
        gameClock = new Clock(calendarClock, new SystemTimeProvider());

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        drawer = new SpriteDrawer(game);
        repository = new GameObjectRepository(Arrays.asList(npc));
    }

    /**
     * This method is executed with every frame.
     */
    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        npc.update(gameClock.getDay(), gameClock.getTime(), gameClock.update());
        npc.move(1);
        game.batch.begin();
        // camera.position.set(0, 0, 0);
        game.font.draw(game.batch, gameClock.getTime().toString(), 500, 500);
        drawer.draw(npc);
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
        // dispose of game things, like music, etc.
    }

    //        JsonReader reader = new JsonReader();
    //        JsonValue file = reader.parse(Gdx.files.internal("resources/clock.json"))
    // to read json files
}
