package V2;

import com.mygdx.game.V2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeekScheduleTest {

    private DaySchedule daySchedule1;
    private DaySchedule daySchedule2;
    private DaySchedule daySchedule3;
    private Map<Day, DaySchedule> map1;
    private Map<Day, DaySchedule> map2;
    private Map<Day, DaySchedule> map3;
    private WeekSchedule weekSchedule1;
    private WeekSchedule weekSchedule2;
    private WeekSchedule weekSchedule3;

    @BeforeEach
    public void initialise(){
        Position position1 = Position.create(5, 15);
        Position position2 = Position.create(10, 20);
        Time time1 = Time.create(0, 55);
        Time time2 = Time.create(5, 55);
        Time time3 = Time.create(10, 55);
        Time time4 = Time.create(15,55);
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";

        Activity activity1 = Activity.builder().position(position1).time(time1).type(type).mapName(mapName).build();
        Activity activity2 = Activity.builder().position(position2).time(time2).type(type).mapName(mapName).build();
        Activity activity3 = Activity.builder().position(position1).time(time3).type(type).mapName(mapName).build();
        Activity activity4 = Activity.builder().position(position1).time(time4).type(type).mapName(mapName).build();

        daySchedule1 = DaySchedule.create(Arrays.asList(activity1, activity2, activity3));
        daySchedule2 = DaySchedule.create(Arrays.asList(activity2, activity3, activity4));
        daySchedule3 = DaySchedule.create(Arrays.asList(activity1, activity2));


       map1 = new LinkedHashMap<Day, DaySchedule>(){{
            put(Day.MONDAY, daySchedule1);
            put(Day.TUESDAY, daySchedule2);
            put(Day.WEDNESDAY, daySchedule3);
        }};
        map2 = new LinkedHashMap<Day, DaySchedule>(){{
            put(Day.MONDAY, daySchedule1);
            put(Day.TUESDAY, daySchedule3);
            put(Day.WEDNESDAY, daySchedule2);
        }};
        map3 = new LinkedHashMap<Day, DaySchedule>(){{
            put(Day.MONDAY, daySchedule1);
            put(Day.TUESDAY, daySchedule2);
            put(Day.WEDNESDAY, daySchedule3);
        }};
        weekSchedule1 = WeekSchedule.create(map1);
        weekSchedule2 = WeekSchedule.create(map2);
        weekSchedule3 = WeekSchedule.create(map3);
    }

    @Test
    public void testConstructor(){
        assertEquals(weekSchedule1.getDaySchedulesWithDays(), map1);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> WeekSchedule.create(null));
        Map<Day, DaySchedule> nullKeyMap = new HashMap<Day, DaySchedule>(){{
            put(null, daySchedule1);
        }};
        assertThrows(NullPointerException.class, () -> WeekSchedule.create(nullKeyMap));
        Map<Day, DaySchedule> nullValueMap = new HashMap<Day, DaySchedule>(){{
            put(Day.MONDAY, null);
        }};
        assertThrows(NullPointerException.class, () -> WeekSchedule.create(nullValueMap));
    }

    @Test
    public void testGetDaySchedule(){
        assertEquals(weekSchedule1.getDaySchedule(Day.MONDAY), daySchedule1);
        assertEquals(weekSchedule1.getDaySchedule(Day.WEDNESDAY), daySchedule3);
        assertThrows(NoSuchElementException.class, () -> weekSchedule1.getDaySchedule(Day.THURSDAY));
    }

    @Test
    public void testGetDayScheduleWithNull(){
        assertThrows(NullPointerException.class, () -> weekSchedule1.getDaySchedule(null));
    }

    @Test
    public void testEquals(){
        assertEquals(weekSchedule1, weekSchedule3);
        assertNotEquals(weekSchedule1, weekSchedule2);
        assertNotEquals(weekSchedule1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(weekSchedule1.hashCode(), weekSchedule3.hashCode());
        assertNotEquals(weekSchedule1.hashCode(), weekSchedule2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "WeekSchedule[DaySchedules={MONDAY=DaySchedule[Activities=[Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=0, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=10, y=20]], Time[hours=5, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=10, minutes=55], Type=IDLING]]], TUESDAY=DaySchedule[Activities=[Activity[Location[mapName=world, Position[x=10, y=20]], Time[hours=5, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=10, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=15, minutes=55], Type=IDLING]]], WEDNESDAY=DaySchedule[Activities=[Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=0, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=10, y=20]], Time[hours=5, minutes=55], Type=IDLING]]]}]";
        assertEquals(weekSchedule1.toString(), expectedString);
    }
}
