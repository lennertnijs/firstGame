package game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.game_object.Transform;
import game.game_object.renderer.*;
import game.inventory.Inventory;
import game.inventory.Item;
import game.npc.dialogue.DialogueData;
import game.npc.dialogue.DialogueRepository;
import game.npc.navigation.Location;
import game.npc.navigation.BFSPathFinder;
import game.npc.navigation.graph.Graph;
import game.npc.navigation.NavigationData;
import game.npc.week_schedule.Activity;
import game.npc.week_schedule.Schedule;
import game.npc.week_schedule.Time;
import game.npc.week_schedule.WeekSchedule;
import game.stats.Stats;
import game.util.Day;
import game.util.Direction;
import game.util.Vec2;
import game.npc.NPC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static game.util.Direction.*;
import static game.util.Direction.LEFT;

public class NPCLoader {

    public static NPC create() {
        Vec2 position = new Vec2(500, 500);
        Transform transform = new Transform(position);
        String map = "main";

        String name = "Gilbert";

        Renderer renderer = new AnimatedRenderer(loadAnimations(), new Key("idle", DOWN), 0);

        Map<Day, Schedule> scheduleMap = new HashMap<>();
        scheduleMap.put(Day.MONDAY, loadSchedule());
        scheduleMap.put(Day.TUESDAY, loadSchedule());
        scheduleMap.put(Day.WEDNESDAY, loadSchedule());
        WeekSchedule weekSchedule = new WeekSchedule(scheduleMap);

        NavigationData navigationData = new NavigationData(loadGraph(), new BFSPathFinder<>());

        DialogueData dialogueData = new DialogueData(new ArrayList<>(), new DialogueRepository(new HashMap<>()));

        Stats stats = Stats.builder().health(500).defense(500).offense(500).speed(50).build();

        Inventory inventory = new Inventory( new Item[6]);
        return new NPC(transform, renderer, map, name, inventory, stats, weekSchedule, navigationData, dialogueData);
    }

    private static Schedule loadSchedule(){
        Activity activity1 = new Activity("mine", "main", new Vec2(1000, 1500), new Time(4, 35));
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

    private static Map<Key, Animation> loadAnimations(){
        Map<Key, Animation> animations = new HashMap<>();
        String[] names = new String[]{"idle", "walk", "mine"};
        Direction[] directions = new Direction[]{UP, RIGHT, DOWN, LEFT};
        for (String name : names) {
            TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("npc/" + name + ".png")));
            Frame frame = Frame.builder().texture(texture).scaleX(2).scaleY(2).build();
            Animation animation = new Animation(new Frame[]{frame}, 100);
            for (Direction direction : directions) {
                Key key = new Key(name, direction);
                animations.put(key, animation);
            }
        }
        return animations;
    }
}
