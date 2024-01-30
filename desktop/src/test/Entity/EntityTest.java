package Entity;

import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTest {

    @Test
    public void testConstructor(){
        Position position = Position.builder().x(200).y(120).build();
        String spritePath = "resources/stone.png";
        Entity entity = new Entity(position);
        Assertions.assertAll(
                () -> Assertions.assertEquals(position, entity.getPosition())
        );
    }

    @Test
    public void testSetPosition(){
        Position position = Position.builder().x(200).y(120).build();
        Position newPosition = Position.builder().x(400).y(240).build();
        String spritePath = "resources/stone.png";
        Entity entity = new Entity(position);
        entity.setPosition(newPosition);
        Assertions.assertAll(
                () -> Assertions.assertEquals(entity.getPosition(), newPosition)
        );
    }

    @Test
    public void testSetPositionInvalid(){
        Position position = Position.builder().x(200).y(120).build();
        String spritePath = "resources/stone.png";
        Entity entity = new Entity(position);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> entity.setPosition(null))
        );
    }
}
