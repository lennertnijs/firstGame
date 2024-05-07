package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameScreen implements Screen {
    final MyGame game;
    final OrthographicCamera camera;

    public GameScreen(MyGame game) {
        // Texture bar = new Texture(Gdx.files.internal("inventoryBar.png")) to create a Texture

        // game.batch.draw(map, 0, 0, 4800 ,4800 ); To draw

        // Implements InputProcessor to make an input handler.
        // Gdx.input.setInputProcessor(gamePlayInputProcessor); to then set it.

        // to play music
        // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        // rainMusic.setLooping(true);
        this.game = game;


        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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

        game.batch.begin();
        // camera.position.set(0, 0, 0);
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
