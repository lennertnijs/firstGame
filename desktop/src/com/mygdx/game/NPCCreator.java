package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.Dialogue.DialogueRepository;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventoryManager;
import com.mygdx.game.Inventory.ItemTemplateRepository;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.NPC.Stats;
import com.mygdx.game.Navigation.BFSPathFinder;
import com.mygdx.game.Navigation.Graph;
import com.mygdx.game.Navigation.NavigationData;
import com.mygdx.game.AnimationRepository.*;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.*;

import java.util.*;
import java.util.Map;

import static com.mygdx.game.Util.Direction.*;
import static com.mygdx.game.Keys.NPCActivityType.*;
import static com.mygdx.game.Util.GameMap.MAIN;

public class NPCCreator {

    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("npc/mining.atlas"));
    public static NPC create() {
        TextureRegion idleDown = atlas.findRegion("idle_down");
        Point position = new Point(500, 500);
        Dimensions dimensions = new Dimensions(idleDown.getRegionWidth(), idleDown.getRegionHeight());
        String map = "Map";

        String name = "Gilbert";

        AnimationRepository animRepo = new AnimationRepository(loadAnimationMap());

        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, loadSchedule());
        scheduleMap.put(Day.TUESDAY, loadSchedule());
        scheduleMap.put(Day.WEDNESDAY, loadSchedule());
        WeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        NavigationData navigationData = new NavigationData(new BFSPathFinder<>(loadGraph()));

        DialogueData dialogueData = new DialogueData(new ArrayList<>(), new DialogueRepository(new HashMap<>()));

        Stats stats = Stats.builder().health(500).defense(500).offense(500).speed(10).build();

        IInventoryManager inventoryManager = new InventoryManager(new Inventory(6),
                1, new ItemTemplateRepository());

        return NPC.builder()
                .position(position)
                .dimensions(dimensions)
                .map(map)
                .animationRepo(animRepo)
                .direction(RIGHT)
                .activityStack(new LinkedList<>(Collections.singletonList(IDLING)))
                .delta(0)
                .name(name)
                .inventoryManager(inventoryManager)
                .weekSchedule(weekSchedule)
                .navigationData(navigationData)
                .dialogueData(dialogueData)
                .stats(stats)
                .build();
    }

    public static Player createPlayer(){
        TextureRegion idleDown = atlas.findRegion("idle_down");
        Point position = new Point(500, 500);
        Dimensions dimensions = new Dimensions(idleDown.getRegionWidth(), idleDown.getRegionHeight());
        String map = "Map";

        String name = "Gilbert";

        AnimationRepository animRepo = new AnimationRepository(loadAnimationMap());
        IInventoryManager inventoryManager = new InventoryManager(new Inventory(6),
                1, new ItemTemplateRepository());


        return new Player(
                position,
                dimensions,
                map,
                name,
                animRepo,
                inventoryManager
                );
    }

    private static Map<AnimationKey, Animation> loadAnimationMap(){
        Map<AnimationKey, Animation> map = new HashMap<>();

        Frame idleUpFrame = Frame.builder().textureRegion(atlas.findRegion("idle_up")).build();
        Frame idleRightFrame = Frame.builder().textureRegion(atlas.findRegion("idle_right")).build();
        Frame idleDownFrame = Frame.builder().textureRegion(atlas.findRegion("idle_down")).build();
        Frame idleLeftFrame = Frame.builder().textureRegion(atlas.findRegion("idle_left")).build();

        Animation idleUpAnimation = new Animation(Collections.singletonList(idleUpFrame), 1);
        Animation idleRightAnimation = new Animation(Collections.singletonList(idleRightFrame), 1);
        Animation idleDownAnimation = new Animation(Collections.singletonList(idleDownFrame), 1);
        Animation idleLeftAnimation = new Animation(Collections.singletonList(idleLeftFrame), 1);

        map.put(new EntityKey(IDLING, UP), idleUpAnimation);
        map.put(new EntityKey(IDLING, RIGHT), idleRightAnimation);
        map.put(new EntityKey(IDLING, DOWN), idleDownAnimation);
        map.put(new EntityKey(IDLING, LEFT), idleLeftAnimation);

        Animation miningRightAnimation = loadAnimation(atlas, "mine_right", 4);
        Animation miningLeftAnimation = loadAnimation(atlas, "mine_left", 4);
        Animation miningUpAnimation = loadAnimation(atlas, "mine_up", 4);

        Frame mineD1 = Frame.builder().textureRegion(atlas.findRegion("mine_down", 1)).build();
        Frame mineD2 = Frame.builder().textureRegion(atlas.findRegion("mine_down", 2)).build();
        Frame mineD3 = Frame.builder().textureRegion(atlas.findRegion("mine_down", 3)).build();
        Frame mineD4 = Frame.builder().textureRegion(atlas.findRegion("mine_down", 4)).build();

        Animation miningDownAnimation = new Animation(Arrays.asList(mineD1, mineD2, mineD3, mineD4), 2000f);

        map.put(new EntityKey(MINING, RIGHT), miningRightAnimation);
        map.put(new EntityKey(MINING, DOWN), miningDownAnimation);
        map.put(new EntityKey(MINING, LEFT), miningLeftAnimation);
        map.put(new EntityKey(MINING, UP), miningUpAnimation);

        Animation walkingRightAnimation = loadAnimation(atlas, "walking_right", 6);
        Animation walkingLeftAnimation = loadAnimation(atlas, "walking_right", 6);
        Animation walkingUpAnimation = loadAnimation(atlas, "walking_up", 6);
        Animation walkingDownAnimation = loadAnimation(atlas,"walking_down", 6);

        map.put(new EntityKey(WALKING, RIGHT), walkingRightAnimation);
        map.put(new EntityKey(WALKING, DOWN), walkingDownAnimation);
        map.put(new EntityKey(WALKING, LEFT), walkingLeftAnimation);
        map.put(new EntityKey(WALKING, UP), walkingUpAnimation);

        return map;
    }

    private static Animation loadAnimation(TextureAtlas atlas, String name, int amountOfFrames){
        List<Frame> frames = new ArrayList<>();
        for(int i = 1; i <= amountOfFrames; i++){
            Frame frame = Frame.builder().textureRegion(atlas.findRegion(name, i)).build();
            frames.add(frame);
        }
        return new Animation(frames, 2000f);
    }

    private static Schedule loadSchedule(){
        Activity activity1 = new Activity(MINING, MAIN, new Point(0, 0), new Time(4, 50));
        Activity activity2 = new Activity(WALKING, MAIN, new Point(1000, 500), new Time(5, 50));
        Activity activity3 = new Activity(IDLING, MAIN, new Point(1000, 0), new Time(6, 50));
        Activity activity4 = new Activity(IDLING, MAIN, new Point(0, 0), new Time(7, 50));
        return new Schedule(Arrays.asList(activity1, activity2, activity3, activity4));
    }

    private static Graph<Location> loadGraph(){
        Location l1 = new Location("Map", new Point(0, 0));
        Location l2 = new Location("Map", new Point(500, 500));
        Location l3 = new Location("Map", new Point(1000, 500));
        Location l4 = new Location("Map", new Point(1000, 0));
        Location l5 = new Location("Map", new Point(1000, 1500));
        Graph<Location> g = new Graph<>();
        g.addVertices(Arrays.asList(l1, l2, l3, l4, l5));
        g.connect(l1, l2);
        g.connect(l2, l3);
        g.connect(l3, l4);
        g.connect(l4, l5);
        return g;

    }
}
