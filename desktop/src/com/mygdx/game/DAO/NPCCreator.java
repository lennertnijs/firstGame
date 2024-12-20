package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.npc.dialogue.DialogueData;
import com.mygdx.game.npc.dialogue.DialogueRepository;
import com.mygdx.game.npc.navigation.Location;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.npc.navigation.BFSPathFinder;
import com.mygdx.game.npc.navigation.graph.Graph;
import com.mygdx.game.npc.navigation.NavigationData;
import com.mygdx.game.npc.week_schedule.*;
import com.mygdx.game.game_object.renderer.Renderer;
import com.mygdx.game.game_object.renderer.StaticRenderer;
import com.mygdx.game.effect.Stats;
import com.mygdx.game.util.Vec2;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.game_object.renderer.Frame;
import com.mygdx.game.inventory.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NPCCreator {

    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("npc/mining.atlas"));
    public static NPC create() {
        TextureRegion idleDown = atlas.findRegion("idle_down");
        Vec2 position = new Vec2(500, 500);
        String map = "main";

        String name = "Gilbert";

        //Map<Key, Animation> animations = load("player/player.pack");

        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("npc/mining.png")));
        Frame frame = Frame.builder().texture(tr).build();
        Renderer renderer = new StaticRenderer(frame);

        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, loadSchedule());
        scheduleMap.put(Day.TUESDAY, loadSchedule());
        scheduleMap.put(Day.WEDNESDAY, loadSchedule());
        WeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        NavigationData navigationData = new NavigationData(loadGraph(), new BFSPathFinder<>());

        DialogueData dialogueData = new DialogueData(new ArrayList<>(), new DialogueRepository(new HashMap<>()));

        Stats stats = Stats.builder().health(500).defense(500).offense(500).speed(50).build();

        Map<String, Integer> stackSizeMap = new HashMap<String, Integer>(){{
            put("Stone", 64);
            put("Wood", 64);
        }};
        Item[] items = new Item[6];
        Inventory inventory = new Inventory(items, stackSizeMap);

        return NPC.builder()
                .renderer(renderer)
                .map(null)
                .name(name)
                .inventory(inventory)
                .weekSchedule(weekSchedule)
                .navigationData(navigationData)
                .dialogueData(dialogueData)
                .stats(stats)
                .build();
    }

    private static Schedule loadSchedule(){
        Activity activity1 = new Activity("mine", "main", new Vec2(1000, 1500), new Time(4, 50));
        Activity activity2 = new Activity("walk", "main", new Vec2(1000, 500), new Time(5, 50));
        Activity activity3 = new Activity("idle", "main", new Vec2(1000, 0), new Time(6, 50));
        Activity activity4 = new Activity("idle", "main", new Vec2(1000, 1500), new Time(7, 50));
        return new Schedule(Arrays.asList(activity1, activity2, activity3, activity4));
    }

    private static Graph<Location> loadGraph(){
        Location l1 = new Location("main", new Vec2(0, 0));
        Location l2 = new Location("main", new Vec2(500, 500));
        Location l3 = new Location("main", new Vec2(1000, 500));
        Location l4 = new Location("main", new Vec2(1000, 0));
        Location l5 = new Location("main", new Vec2(1000, 1500));
        Graph<Location> g = new Graph<>();
        g.addVertices(Arrays.asList(l1, l2, l3, l4, l5));
        g.connect(l1, l2);
        g.connect(l2, l3);
        g.connect(l3, l4);
        g.connect(l4, l5);
        return g;
    }
}
