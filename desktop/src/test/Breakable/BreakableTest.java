package Breakable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Loot.ILootTable;
import com.mygdx.game.Loot.MockLootTable;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BreakableTest {

    private final TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
    private final Point position = new Point(50, 60);
    private final Dimensions dimensions = new Dimensions(50, 25);
    private final String map = "main";
    private final int health = 250;
    private final int hardness = 2000;
    private final String type = "rock";
    private final ILootTable lootTable = new MockLootTable();
    private Breakable breakable;

    @BeforeEach
    public void initialise(){
        breakable = Breakable.builder().textureRegion(textureRegion).position(position).dimensions(dimensions)
                .map(map).health(health).hardness(hardness).type(type).lootTable(lootTable).build();
    }

    @Test
    public void testConstructorWithNegativeHealth(){
        assertThrows(IllegalArgumentException.class,
                () -> Breakable.builder().textureRegion(textureRegion).position(position).dimensions(dimensions)
                        .map(map).health(-1).hardness(hardness).type(type).lootTable(lootTable).build());
    }

    @Test
    public void testConstructorWithZeroHealth(){
        assertThrows(IllegalArgumentException.class,
                () -> Breakable.builder().textureRegion(textureRegion).position(position).dimensions(dimensions)
                        .map(map).health(0).hardness(hardness).type(type).lootTable(lootTable).build());
    }

    @Test
    public void testConstructorWithNegativeHardness(){
        assertThrows(IllegalArgumentException.class,
                () -> Breakable.builder().textureRegion(textureRegion).position(position).dimensions(dimensions)
                        .map(map).health(health).hardness(-1).type(type).lootTable(lootTable).build());
    }

    @Test
    public void testGetTexture(){
        assertEquals(textureRegion, breakable.getTexture());
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, breakable.getPosition());
    }

    @Test
    public void testGetDimensions(){
        assertEquals(dimensions, breakable.getDimensions());
    }

    @Test
    public void testGetMap(){
        assertEquals(map, breakable.getMap());
    }

    @Test
    public void testGetHealth(){
        assertEquals(health, breakable.getHealth());
    }

    @Test
    public void testGetHardness(){
        assertEquals(hardness, breakable.hardness());
    }

    @Test
    public void testGetType(){
        assertEquals(type, breakable.type());
    }

    @Test
    public void testDamage(){
        assertEquals(health, breakable.getHealth());
        breakable.damage(50);
        assertEquals(health - 50, breakable.getHealth());
        breakable.damage(150);
        assertEquals(health - 200, breakable.getHealth());
        breakable.damage(100);
        assertEquals(0, breakable.getHealth());
    }

    @Test
    public void testDamageWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> breakable.damage(-1));
    }

    @Test
    public void testDamageWithZero(){
        breakable.damage(0); // allowed
    }

    @Test
    public void testIsBroken(){
        assertFalse(breakable.isBroken());
        breakable.damage(250);
        assertTrue(breakable.isBroken());
    }
}
