package ItemTest;

import com.mygdx.game.Item.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeaponTest {

    @Test
    public void testConstructor(){
        Weapon weapon = Weapon.weaponBuilder().damage(15).name("Sword").build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weapon.getDamage(), 15),
                () -> Assertions.assertEquals(weapon.getStackSize(), 1),
                () -> Assertions.assertEquals(weapon.getName(), "Sword")
        );
    }

    @Test
    public void testConstructorInvalid(){
        Weapon.Builder builder1 = Weapon.weaponBuilder().damage(0).name("Sword");
        Weapon.Builder builder2 = Weapon.weaponBuilder().damage(15).name(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build)
        );
    }
}
