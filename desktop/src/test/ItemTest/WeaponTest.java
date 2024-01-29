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

    @Test
    public void testConstructorInvalid(){
        Weapon.Builder builder1 = Weapon.weaponBuilder().damage(0).name("Sword").spritePath("path");
        Weapon.Builder builder2 = Weapon.weaponBuilder().damage(15).name(null).spritePath("path");
        Weapon.Builder builder3 = Weapon.weaponBuilder().damage(15).name("Sword").spritePath(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder3::build)
        );
    }
}
