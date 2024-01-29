package ItemTest;

import com.mygdx.game.Item.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeaponTest {

    @Test
    public void testConstructor(){
        Weapon weapon = Weapon.weaponBuilder().damage(15).name("Sword").spritePath("path").build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weapon.getDamage(), 15),
                () -> Assertions.assertEquals(weapon.getStackSize(), 1),
                () -> Assertions.assertEquals(weapon.getName(), "Sword"),
                () -> Assertions.assertEquals(weapon.getSpritePath(), "path")
        );

    }
}
