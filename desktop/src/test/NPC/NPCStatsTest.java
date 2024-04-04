package NPC;

import com.mygdx.game.V2.NPCStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCStatsTest {

    private NPCStats stats1;
    private NPCStats stats2;
    private NPCStats stats3;
    @BeforeEach
    public void initialise(){
        stats1 = NPCStats.builder().health(50).offense(5).defense(5).speed(1000).build();
        stats2 = NPCStats.builder().health(100).offense(5).defense(10).speed(2000).build();
        stats3 = NPCStats.builder().health(50).offense(5).defense(5).speed(1000).build();
    }

    @Test
    public void testConstructor(){
        assertEquals(stats1.getHealth(), 50);
        assertEquals(stats1.getOffense(), 5);
        assertEquals(stats1.getDefense(), 5);
        assertEquals(stats1.getSpeed(), 1000);
    }

    @Test
    public void testConstructorInvalid(){
        NPCStats.Builder invalidHealth = NPCStats.builder().health(0).offense(5).defense(5).speed(1000);
        NPCStats.Builder invalidOffense = NPCStats.builder().health(50).offense(-1).defense(5).speed(1000);
        NPCStats.Builder invalidDefense = NPCStats.builder().health(50).offense(5).defense(-1).speed(1000);
        NPCStats.Builder invalidSpeed = NPCStats.builder().health(50).offense(5).defense(5).speed(NPCStats.MIN_SPEED - 1);
        assertThrows(IllegalArgumentException.class, invalidHealth::build);
        assertThrows(IllegalArgumentException.class, invalidOffense::build);
        assertThrows(IllegalArgumentException.class, invalidDefense::build);
        assertThrows(IllegalArgumentException.class, invalidSpeed::build);
    }

    @Test
    public void testSetHealth(){
        stats1.setHealth(1000);
        assertEquals(stats1.getHealth(), 1000);
    }

    @Test
    public void testSetHealthInvalid(){
        assertThrows(IllegalArgumentException.class, () -> stats1.setHealth(-1));
    }

    @Test
    public void testSetOffense(){
        stats1.setOffense(500);
        assertEquals(stats1.getOffense(), 500);
    }

    @Test
    public void testSetOffenseInvalid(){
        assertThrows(IllegalArgumentException.class, () -> stats1.setOffense(-1));
    }

    @Test
    public void testSetDefense(){
        stats1.setDefense(500);
        assertEquals(stats1.getDefense(), 500);
    }

    @Test
    public void testSetDefenseInvalid(){
        assertThrows(IllegalArgumentException.class, () -> stats1.setDefense(-1));
    }

    @Test
    public void testSetSpeed(){
        stats1.setSpeed(500);
        assertEquals(stats1.getSpeed(), 500);
    }

    @Test
    public void testSetSpeedInvalid(){
        assertThrows(IllegalArgumentException.class, () -> stats1.setSpeed(NPCStats.MIN_SPEED - 1));
    }

    @Test
    public void testEquals(){
        assertEquals(stats1, stats3);
        assertNotEquals(stats1, stats2);
        assertNotEquals(stats1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(stats1.hashCode(), stats3.hashCode());
        assertNotEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "NPCStats[health=50, offense=5, defense=5, speed=1000]";
        assertEquals(stats1.toString(), expectedString);
    }
}
