package Templates;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ItemTemplate.ItemTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStackTemplateTest {

    private String name;
    private String description;
    private Texture texture;
    private int stackSize;
    private ItemTemplate template;

    @BeforeEach
    public void initialise(){
        name = "Stone";
        description = "A stone.";
        texture = Mockito.mock(Texture.class);
        stackSize = 64;

        template = new ItemTemplate(name, description, texture, stackSize);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new ItemTemplate(null, description, texture, stackSize));
    }

    @Test
    public void testConstructorWithNullDescription(){
        assertThrows(NullPointerException.class,
                () -> new ItemTemplate(name, null, texture, stackSize));
    }
//
//    @Test
//    public void testConstructorWithNullTextureRegion(){
//        assertThrows(NullPointerException.class,
//                () -> new ItemTemplate(name, description, null, stackSize));
//    }

    @Test
    public void testConstructorWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemTemplate(name, description, texture, -1));
    }

    @Test
    public void testConstructorWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemTemplate(name, description, texture, 0));
    }

    @Test
    public void testGetName(){
        assertEquals(name, template.name());
    }

    @Test
    public void testGetDescription(){
        assertEquals(description, template.description());
    }

    @Test
    public void testGetTexture(){
        assertEquals(texture, template.texture());
    }

    @Test
    public void testGetStackSize(){
        assertEquals(stackSize, template.stackSize());
    }

    @Test
    public void testEquals(){
        ItemTemplate template1 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate template2 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate template3 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate diffTemplate = new ItemTemplate("Wood", description, texture, stackSize);
        // reflexive
        assertEquals(template1, template1);
        // symmetrical
        assertEquals(template1, template2);
        assertEquals(template2, template1);
        // transitive
        assertEquals(template1, template2);
        assertEquals(template2, template3);
        assertEquals(template1, template3);
        // not equals
        assertNotEquals(template1, diffTemplate);
        assertNotEquals(template1, new Object());
        assertNotEquals(template1, null);
    }

    @Test
    public void testHashCode(){
        ItemTemplate template1 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate template2 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate template3 = new ItemTemplate(name, description, texture, stackSize);
        ItemTemplate diffTemplate = new ItemTemplate("Wood", description, texture, stackSize);
        // reflexive
        assertEquals(template1.hashCode(), template1.hashCode());
        // symmetrical
        assertEquals(template1.hashCode(), template2.hashCode());
        assertEquals(template2.hashCode(), template1.hashCode());
        // transitive
        assertEquals(template1.hashCode(), template2.hashCode());
        assertEquals(template2.hashCode(), template3.hashCode());
        assertEquals(template1.hashCode(), template3.hashCode());
        // not equals
        assertNotEquals(template1.hashCode(), diffTemplate.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(template.toString());
    }
}
