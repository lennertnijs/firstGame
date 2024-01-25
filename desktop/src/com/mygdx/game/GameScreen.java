package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Clock.*;
import com.mygdx.game.Controller.ClockController;
import com.mygdx.game.DAO.CalendarDAO;
import com.mygdx.game.Graph.Graph;
import com.mygdx.game.Graph.Vertex;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.NPC.Position2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameScreen implements Screen {
    final MyGame game;
    Texture map;
    Texture character;
    Texture charRight;
    Texture charLeft;
    Texture side;
    Texture side2;

    Rectangle charac;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle mapRect;
    int walking = 0;
    Texture pickaxe;
    Texture stone;

    Texture frame;

    Array<Integer> rocks = spawnRocks();

    NPC npc = NPC.builder().position(new Position2D(2000, 2000)).name("name").build(); //2000, 2000

    Rectangle inventorySlot;

    Clock clock = createClock();
    ClockController clockController = new ClockController();


    /* Loads the game screen. Only is executed upon screen load */
    public GameScreen(final MyGame game) {

        this.game = game;
        // load the images for the droplet and the bucket, 64x64 pixels each
        map = new Texture(Gdx.files.internal("images/map.png"));
        character = new Texture(Gdx.files.internal("images/guy.png"));
        charLeft = new Texture(Gdx.files.internal("images/guyleft.png"));
        charRight = new Texture(Gdx.files.internal("images/guyright.png"));
        side = new Texture(Gdx.files.internal("images/side.png"));
        side2 = new Texture(Gdx.files.internal("images/side2.png"));
        pickaxe = new Texture(Gdx.files.internal("images/pick.png"));
        stone = new Texture(Gdx.files.internal("images/stone.png"));
        frame = new Texture(Gdx.files.internal("images/frame.png"));

        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);

        mapRect = new Rectangle();
        mapRect.width = 5000;
        mapRect.height = 2500;
        mapRect.x = 0;
        mapRect.y = 0;

        charac = new Rectangle();
        charac.height = 256;
        charac.width = 128;
        charac.x = (float) 1920 /2;
        charac.y = 510;

        inventorySlot = new Rectangle();
        inventorySlot.height = 128;
        inventorySlot.width = 128;
        inventorySlot.x = (float) 1920 /2;
        inventorySlot.y = 0;


    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();

        game.batch.draw(map, mapRect.x, mapRect.y, mapRect.width, mapRect.height);
        game.batch.draw(frame, inventorySlot.x, inventorySlot.y, inventorySlot.width, inventorySlot.height);
        if(walking > 15 && walking < 30){
            game.batch.draw(charLeft, charac.x, charac.y, charac.width, charac.height);
        } else if (walking > 45 && walking <= 60) {
            game.batch.draw(charRight, charac.x, charac.y, charac.width, charac.height);
        }else{
            game.batch.draw(character, charac.x, charac.y, charac.width, charac.height);
        }
        game.batch.draw(character, npc.getPosition().getX(), npc.getPosition().getY(), charac.width, charac.height);
        clockController.updateClock(clock);

        game.font.getData().setScale(3,3);
        game.font.draw(game.batch, clock.getTimeInHHMM(), 1700, 800);
        game.font.draw(game.batch, String.valueOf(clock.getDay()), 1700, 725);
        game.font.draw(game.batch, String.valueOf(clock.getSeason()), 1700, 650);

        Array<Rectangle> hitboxesRocks = new Array<>();


        Graph graph = new Graph();
        Vertex v1 = new Vertex("name1",2000, 2000);
        Vertex v2 = new Vertex("name2", 1000,2000);
        Vertex v3 = new Vertex("name3", 1000, 1000);
        graph.addVertex(v1, new ArrayList<>(Collections.singletonList(v2)));
        graph.addVertex(v2, new ArrayList<>(Collections.singletonList(v3)));
        graph.addVertex(v3, new ArrayList<>(Collections.singletonList(v1)));


        for(int i = 0; i < rocks.size ; i+= 2){
            Rectangle hitbox = new Rectangle();
            hitbox.height = 128;
            hitbox.width = 128;
            hitbox.x = rocks.get(i);
            hitbox.y = rocks.get(i+1);
            hitboxesRocks.add(hitbox);


            float xTotal = Math.abs(charac.x - rocks.get(i));
            float yTotal = Math.abs(charac.y - rocks.get(i+1));
            if(Math.sqrt(xTotal*xTotal + yTotal*yTotal) < 10500){
                float num = yTotal/xTotal + 1;
                int total = 3;
                int xChange = Math.round(total/num);
                int yChange = total-xChange;
                if(charac.x != rocks.get(i)){
                    if(charac.x < rocks.get(i)){
                        rocks.set(i, rocks.get(i)-xChange);
                    }else{
                        rocks.set(i, rocks.get(i)+xChange);
                    }

                }
                if(charac.y != rocks.get(i+1)){
                    if(charac.y < rocks.get(i+1)){
                        rocks.set(i+1, rocks.get(i+1)-yChange);
                    }else{
                        rocks.set(i+1, rocks.get(i+1)+yChange);
                    }

                }
            }


            game.batch.draw(stone, rocks.get(i), rocks.get(i+1),128, 128);
        }
        //npc.update();
        game.font.draw(game.batch, "Text ", 0, 480);
        movementInputs();
        game.batch.end();

        camera.position.set(charac.x, charac.y, 0);
    }


    private Clock createClock(){
        Calendar calendar = CalendarDAO.readCalendar();
        int minutes = 1420;
        int currentDaysInMonth = 12;
        return Clock.builder()
                .calendar(calendar)
                .season(Season.DARK)
                .dayOfTheSeason(currentDaysInMonth)
                .day(Day.SUNDAY)
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


    private Array<Integer> spawnRocks(){
        Random random = new Random();
        Array<Integer> coordinates = new Array<>();
        for(int i = 0; i< 100; i++){
            coordinates.add(random.nextInt(5000)+1);
        }
        return coordinates;
    }

    private void movementInputs(){
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            charac.x -= 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.x -= 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(side2, charac.x, charac.y, charac.width, charac.height);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            charac.x += 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.x += 200 * Gdx.graphics.getDeltaTime();
            game.batch.draw(side, charac.x, charac.y, charac.width, charac.height);
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            charac.y -= 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.y -= 200 * Gdx.graphics.getDeltaTime();
            walking += 1;
            if(walking > 60){
                walking = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            charac.y += 200 * Gdx.graphics.getDeltaTime();
            inventorySlot.y += 200 * Gdx.graphics.getDeltaTime();
        }

        if(charac.x < 0){
            charac.x = 0;
        }
        if(charac.x >5000){
            charac.x = 5000;
        }
        if(charac.y < 0){
            charac.y = 0;
        }
        if(charac.y >2500){
            charac.y = 2500;
        }
    }



}
