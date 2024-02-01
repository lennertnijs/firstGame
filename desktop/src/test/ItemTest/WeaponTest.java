package ItemTest;

import com.mygdx.game.Item.Weapon;
import com.mygdx.game.Item.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeaponTest {

    @Test
    public void testConstructor(){
        Weapon weapon = Weapon.weaponBuilder().damage(15).name("Sword").weaponType(WeaponType.DAGGER).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weapon.getDamage(), 15),
                () -> Assertions.assertEquals(weapon.getStackSize(), 1),
                () -> Assertions.assertEquals(weapon.getName(), "Sword"),
                () -> Assertions.assertEquals(weapon.getWeaponType(), WeaponType.DAGGER)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Weapon.Builder builder1 = Weapon.weaponBuilder().damage(0).name("Sword").weaponType(WeaponType.DAGGER);
        Weapon.Builder builder2 = Weapon.weaponBuilder().damage(15).name(null).weaponType(WeaponType.DAGGER);
        Weapon.Builder builder3 = Weapon.weaponBuilder().damage(15).name("Dagger").weaponType(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder3::build)
        );
    }


    @Test
    public void testEquals(){
        Weapon dagger = Weapon.weaponBuilder().damage(15).name("Dagger").weaponType(WeaponType.DAGGER).build();
        Weapon spear = Weapon.weaponBuilder().damage(15).name("Spear").weaponType(WeaponType.SPEAR).build();
        Weapon dagger2 = Weapon.weaponBuilder().damage(15).name("Dagger").weaponType(WeaponType.DAGGER).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(dagger, dagger2),
                () -> Assertions.assertEquals(dagger, dagger),
                () -> Assertions.assertNotEquals(dagger, spear),
                () -> Assertions.assertNotEquals(dagger, new Object()),
                () -> Assertions.assertEquals(dagger.hashCode(), dagger2.hashCode()),
                () -> Assertions.assertNotEquals(dagger.hashCode(), spear.hashCode())
        );
    }
}
