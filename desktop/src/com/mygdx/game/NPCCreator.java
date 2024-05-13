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

    public static NPC create(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/idle.png"));
        String name = "Gilbert";
        TextureRegion t1 = atlas.findRegion("idle");
        //Texture idleTexture2 = new Texture(Gdx.files.internal("images/idle.png"));
        Sprite sprite = new Sprite(t1, new Point(0, 0), new Vector(5, 5), new Dimensions(50, 50), "Map");


        Key key = new Key(IDLING, UP);
        Map<Key, Animation> animationMap = new HashMap<>();

        Key idleUp = new Key(IDLING, UP);
        Key idleRight = new Key(IDLING, RIGHT);
        Key idleDown = new Key(IDLING, DOWN);
        Key idleLeft = new Key(IDLING, LEFT);
        Texture idleTexture = new Texture(Gdx.files.internal("images/idle.png"));
        List<TextureRegion> up = Collections.singletonList(new TextureRegion(idleTexture, 0, 0, 16, 32));
        List<TextureRegion> right = Collections.singletonList(new TextureRegion(idleTexture, 16, 0, 16, 32));
        List<TextureRegion> down = Collections.singletonList(new TextureRegion(idleTexture, 32, 0, 16, 32));
        List<TextureRegion> left = Collections.singletonList(new TextureRegion(idleTexture, 48, 0, 16, 32));

//        animationMap.put(idleUp,  new Animation(up, 1500));
//        animationMap.put(idleRight, new Animation(right, 1500));
//        animationMap.put(idleDown, new Animation(down, 1500));
//        animationMap.put(idleLeft, new Animation(left, 1500));

        AnimationRepository animRepo = new AnimationRepository(animationMap);

        AnimationClock clock = new AnimationClock();
        ITextureSelector selector = new TextureSelector(key, animRepo, clock);

        Activity activityMonday1 = new Activity(new Location("main", new Point(500, 500)),
                new Time(5, 50), IDLING);
        Schedule monday = new Schedule(Collections.singletonList(activityMonday1));
        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, monday);
        IWeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        INavigationData navigationData = new NavigationData(new BFSPathFinder<>(new Graph<>()));

        IDialogueData dialogueData = new DialogueData(Arrays.asList("L!", "L2"), new DialogueRepository(new HashMap<>()));

        NPCStats stats = NPCStats.builder().health(500).defense(500).offense(500).speed(500).build();

        IInventoryManager inventoryManager = new InventoryManager(new Inventory(6),
                1, new ItemTemplateRepository());


        return new NPC(sprite, name, selector, weekSchedule, navigationData, dialogueData, stats, inventoryManager);
    }

    public List<Key> generateKeys(ActivityType type){
        Key up = new Key(type, UP);
        Key right = new Key(type, RIGHT);
        Key down = new Key(type, DOWN);
        Key left = new Key(type, LEFT);
        return Arrays.asList(up, right, down, left);
    }
}
