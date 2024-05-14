package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.Dialogue.DialogueRepository;
import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventoryManager;
import com.mygdx.game.Inventory.ItemTemplateRepository;
import com.mygdx.game.Navigation.BFSPathFinder;
import com.mygdx.game.Navigation.Graph;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.Navigation.NavigationData;
import com.mygdx.game.TextureSelector.*;
import com.mygdx.game.Util.*;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.WeekSchedule.*;

import java.util.*;

import static com.mygdx.game.Util.Direction.*;
import static com.mygdx.game.WeekSchedule.ActivityType.*;

public class NPCCreator {

    public static NPC create() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("npc/mining.atlas"));

        TextureRegion idleDown = atlas.findRegion("idle_down");
        Point start = new Point(500, 500);
        Dimensions dimensions = new Dimensions(128, 256);
        Sprite sprite = new Sprite(idleDown, start, dimensions, "Map");

        String name = "Gilbert";

        AnimationRepository animRepo = new AnimationRepository(loadAnimationMap(atlas));
        AnimationClock clock = new AnimationClock();
        ITextureSelector selector = new TextureSelector(new Key(IDLING, UP), animRepo, clock);

        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, loadSchedule());
        scheduleMap.put(Day.TUESDAY, loadSchedule());
        scheduleMap.put(Day.WEDNESDAY, loadSchedule());
        IWeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        INavigationData navigationData = new NavigationData(new BFSPathFinder<>(loadGraph()));

        IDialogueData dialogueData = new DialogueData(new ArrayList<>(), new DialogueRepository(new HashMap<>()));

        NPCStats stats = NPCStats.builder().health(500).defense(500).offense(500).speed(10).build();

        IInventoryManager inventoryManager = new InventoryManager(new Inventory(6),
                1, new ItemTemplateRepository());


        return new NPC(
                sprite,
                name,
                selector,
                weekSchedule,
                navigationData,
                dialogueData,
                stats,
                inventoryManager);
    }

    private static Map<Key, Animation> loadAnimationMap(TextureAtlas atlas){
        Dimensions dimensions = new Dimensions(128, 256);
        Map<Key, Animation> map = new HashMap<>();

        Frame idleUpFrame = new Frame(atlas.findRegion("idle_up"), dimensions);
        Frame idleRightFrame = new Frame(atlas.findRegion("idle_right"), dimensions);
        Frame idleDownFrame = new Frame(atlas.findRegion("idle_down"), dimensions);
        Frame idleLeftFrame = new Frame(atlas.findRegion("idle_left"), dimensions);

        Animation idleUpAnimation = new Animation(Collections.singletonList(idleUpFrame), 10000000000f);
        Animation idleRightAnimation = new Animation(Collections.singletonList(idleRightFrame), 10000000000f);
        Animation idleDownAnimation = new Animation(Collections.singletonList(idleDownFrame), 10000000000f);
        Animation idleLeftAnimation = new Animation(Collections.singletonList(idleLeftFrame), 10000000000f);

        map.put(new Key(IDLING, UP), idleUpAnimation);
        map.put(new Key(IDLING, RIGHT), idleRightAnimation);
        map.put(new Key(IDLING, DOWN), idleDownAnimation);
        map.put(new Key(IDLING, LEFT), idleLeftAnimation);

        Animation miningRightAnimation = loadAnimation(atlas, "mine_right", 4);
        Animation miningLeftAnimation = loadAnimation(atlas, "mine_left", 4);
        Animation miningUpAnimation = loadAnimation(atlas, "mine_up", 4);

        Frame mineD1 = new Frame(atlas.findRegion("mine_down", 1),new Vector(250, 250), dimensions);
        Frame mineD2 = new Frame(atlas.findRegion("mine_down", 2), dimensions);
        Frame mineD3 = new Frame(atlas.findRegion("mine_down", 3), dimensions);
        Frame mineD4 = new Frame(atlas.findRegion("mine_down", 4), dimensions);

        Animation miningDownAnimation = new Animation(Arrays.asList(mineD1, mineD2, mineD3, mineD4), 2000f);

        map.put(new Key(MINING, RIGHT), miningRightAnimation);
        map.put(new Key(MINING, DOWN), miningDownAnimation);
        map.put(new Key(MINING, LEFT), miningLeftAnimation);
        map.put(new Key(MINING, UP), miningUpAnimation);

        Animation walkingRightAnimation = loadAnimation(atlas, "walking_right", 6);
        Animation walkingLeftAnimation = loadAnimation(atlas, "walking_right", 6);
        Animation walkingUpAnimation = loadAnimation(atlas, "walking_up", 6);
        Animation walkingDownAnimation = loadAnimation(atlas,"walking_down", 6);

        map.put(new Key(WALKING, RIGHT), walkingRightAnimation);
        map.put(new Key(WALKING, DOWN), walkingDownAnimation);
        map.put(new Key(WALKING, LEFT), walkingLeftAnimation);
        map.put(new Key(WALKING, UP), walkingUpAnimation);

        return map;
    }

    private static Animation loadAnimation(TextureAtlas atlas, String name, int amountOfFrames){
        List<Frame> frames = new ArrayList<>();
        Dimensions dimensions = new Dimensions(128, 256);
        for(int i = 1; i <= amountOfFrames; i++){
            Frame frame = new Frame(atlas.findRegion(name, i), dimensions);
            frames.add(frame);
        }
        return new Animation(frames, 2000f);
    }

    private static Schedule loadSchedule(){
        Activity activity1 = new Activity(new Location("Map", new Point(0, 0)),
                new Time(4, 50), MINING);
        Activity activity2 = new Activity(new Location("Map", new Point(1000, 500)),
                new Time(5, 50), WALKING);
        Activity activity3 = new Activity(new Location("Map", new Point(1000, 0)),
                new Time(6, 50), IDLING);
        Activity activity4 = new Activity(new Location("Map", new Point(0, 0)),
                new Time(7, 50), IDLING);
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
