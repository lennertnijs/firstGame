package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

        Frame mineR1 = new Frame(atlas.findRegion("mine_right", 1), dimensions);
        Frame mineR2 = new Frame(atlas.findRegion("mine_right", 2), dimensions);
        Frame mineR3 = new Frame(atlas.findRegion("mine_right", 3), dimensions);
        Frame mineR4 = new Frame(atlas.findRegion("mine_right", 4), dimensions);

        Animation miningRightAnimation = new Animation(Arrays.asList(mineR1, mineR2, mineR3, mineR4), 2000f);

        Frame mineL1 = new Frame(atlas.findRegion("mine_left", 1), dimensions);
        Frame mineL2 = new Frame(atlas.findRegion("mine_left", 2), dimensions);
        Frame mineL3 = new Frame(atlas.findRegion("mine_left", 3), dimensions);
        Frame mineL4 = new Frame(atlas.findRegion("mine_left", 4), dimensions);

        Animation miningLeftAnimation = new Animation(Arrays.asList(mineL1, mineL2, mineL3, mineL4), 2000f);

        Frame mineU1 = new Frame(atlas.findRegion("mine_up", 1), dimensions);
        Frame mineU2 = new Frame(atlas.findRegion("mine_up", 2), dimensions);
        Frame mineU3 = new Frame(atlas.findRegion("mine_up", 3), dimensions);
        Frame mineU4 = new Frame(atlas.findRegion("mine_up", 4), dimensions);

        Animation miningUpAnimation = new Animation(Arrays.asList(mineU1, mineU2, mineU3, mineU4), 2000f);

        Frame mineD1 = new Frame(atlas.findRegion("mine_down", 1),new Vector(250, 250), dimensions);
        Frame mineD2 = new Frame(atlas.findRegion("mine_down", 2), dimensions);
        Frame mineD3 = new Frame(atlas.findRegion("mine_down", 3), dimensions);
        Frame mineD4 = new Frame(atlas.findRegion("mine_down", 4), dimensions);

        Animation miningDownAnimation = new Animation(Arrays.asList(mineD1, mineD2, mineD3, mineD4), 2000f);

        map.put(new Key(MINING, RIGHT), miningRightAnimation);
        map.put(new Key(MINING, DOWN), miningDownAnimation);
        map.put(new Key(MINING, LEFT), miningLeftAnimation);
        map.put(new Key(MINING, UP), miningUpAnimation);

        Frame walkR1 = new Frame(atlas.findRegion("walking_right", 1), dimensions);
        Frame walkR2 = new Frame(atlas.findRegion("walking_right", 2), dimensions);
        Frame walkR3 = new Frame(atlas.findRegion("walking_right", 3), dimensions);
        Frame walkR4 = new Frame(atlas.findRegion("walking_right", 4), dimensions);
        Frame walkR5 = new Frame(atlas.findRegion("walking_right", 5), dimensions);
        Frame walkR6 = new Frame(atlas.findRegion("walking_right",6), dimensions);

        Animation walkingRightAnimation = new Animation(Arrays.asList(walkR1, walkR2, walkR3, walkR4, walkR5, walkR6), 2000f);

        Frame walkL1 = new Frame(atlas.findRegion("walking_right",1), dimensions);
        Frame walkL2 = new Frame(atlas.findRegion("walking_right",2), dimensions);
        Frame walkL3 = new Frame(atlas.findRegion("walking_right",3), dimensions);
        Frame walkL4 = new Frame(atlas.findRegion("walking_right",4), dimensions);
        Frame walkL5 = new Frame(atlas.findRegion("walking_right",5), dimensions);
        Frame walkL6 = new Frame(atlas.findRegion("walking_right",6), dimensions);

        Animation walkingLeftAnimation = new Animation(Arrays.asList(walkL1, walkL2, walkL3, walkL4, walkL5, walkL6), 2000f);

        Frame walkU1 = new Frame(atlas.findRegion("walking_up",1), dimensions);
        Frame walkU2 = new Frame(atlas.findRegion("walking_up",2), dimensions);
        Frame walkU3 = new Frame(atlas.findRegion("walking_up",3), dimensions);
        Frame walkU4 = new Frame(atlas.findRegion("walking_up",4), dimensions);
        Frame walkU5 = new Frame(atlas.findRegion("walking_up",5), dimensions);
        Frame walkU6 = new Frame(atlas.findRegion("walking_up",6), dimensions);

        Animation walkingUpAnimation = new Animation(Arrays.asList(walkU1, walkU2, walkU3, walkU4, walkU5, walkU6), 2000f);

        Frame walkD1 = new Frame(atlas.findRegion("walking_down",1), dimensions);
        Frame walkD2 = new Frame(atlas.findRegion("walking_down",2), dimensions);
        Frame walkD3 = new Frame(atlas.findRegion("walking_down",3), dimensions);
        Frame walkD4 = new Frame(atlas.findRegion("walking_down",4), dimensions);
        Frame walkD5 = new Frame(atlas.findRegion("walking_down",5), dimensions);
        Frame walkD6 = new Frame(atlas.findRegion("walking_down",6), dimensions);

        Animation walkingDownAnimation = new Animation(Arrays.asList(walkD1, walkD2, walkD3, walkD4, walkD5, walkD6), 2000f);

        map.put(new Key(WALKING, RIGHT), walkingRightAnimation);
        map.put(new Key(WALKING, DOWN), walkingDownAnimation);
        map.put(new Key(WALKING, LEFT), walkingLeftAnimation);
        map.put(new Key(WALKING, UP), walkingUpAnimation);

        return map;
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
