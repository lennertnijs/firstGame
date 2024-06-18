package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.Dialogue.DialogueRepository;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.Navigation.BFSPathFinder;
import com.mygdx.game.Navigation.Graph;
import com.mygdx.game.Navigation.NavigationData;
import com.mygdx.game.Animation.*;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.*;

import java.util.*;
import java.util.Map;

import static com.mygdx.game.Util.Direction.*;

public class NPCCreator {

    public static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("npc/mining.atlas"));
    public static NPC create() {
        TextureRegion idleDown = atlas.findRegion("idle_down");
        Point position = new Point(500, 500);
        Dimensions dimensions = new Dimensions(idleDown.getRegionWidth(), idleDown.getRegionHeight());
        String map = "main";

        String name = "Gilbert";

        Map<AnimationKey, Animation> animationMap = AnimationMapLoader.load("player/Player.pack");

        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, loadSchedule());
        scheduleMap.put(Day.TUESDAY, loadSchedule());
        scheduleMap.put(Day.WEDNESDAY, loadSchedule());
        WeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        NavigationData navigationData = new NavigationData(new BFSPathFinder<>(loadGraph()));

        DialogueData dialogueData = new DialogueData(new ArrayList<>(), new DialogueRepository(new HashMap<>()));

        Stats stats = Stats.builder().health(500).defense(500).offense(500).speed(10).build();

        Inventory inventory = Inventory.createEmptyOfSize(6);

        return NPC.builder()
                .position(position)
                .dimensions(dimensions)
                .map(map)
                .animationMap(animationMap)
                .direction(RIGHT)
                .delta(0)
                .name(name)
                .inventory(inventory)
                .weekSchedule(weekSchedule)
                .navigationData(navigationData)
                .dialogueData(dialogueData)
                .stats(stats)
                .build();
    }

    private static Schedule loadSchedule(){
        Activity activity1 = new Activity("mine", "main", new Point(1000, 1500), new Time(4, 50));
        Activity activity2 = new Activity("walk", "main", new Point(1000, 500), new Time(5, 50));
        Activity activity3 = new Activity("idle", "main", new Point(1000, 0), new Time(6, 50));
        Activity activity4 = new Activity("idle", "main", new Point(1000, 1500), new Time(7, 50));
        return new Schedule(Arrays.asList(activity1, activity2, activity3, activity4));
    }

    private static Graph<Location> loadGraph(){
        Location l1 = new Location("main", new Point(0, 0));
        Location l2 = new Location("main", new Point(500, 500));
        Location l3 = new Location("main", new Point(1000, 500));
        Location l4 = new Location("main", new Point(1000, 0));
        Location l5 = new Location("main", new Point(1000, 1500));
        Graph<Location> g = new Graph<>();
        g.addVertices(Arrays.asList(l1, l2, l3, l4, l5));
        g.connect(l1, l2);
        g.connect(l2, l3);
        g.connect(l3, l4);
        g.connect(l4, l5);
        return g;
    }
}
