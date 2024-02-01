package ItemTest;

import com.mygdx.game.Item.Weapon;
import com.mygdx.game.Item.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeaponTest {

    @Test
    public void testConstructor(){
        Weapon weapon = Weapon.weaponBuilder().itemId(0).name("Sword").damage(15).durability(50).weaponType(WeaponType.DAGGER).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weapon.getItemId(), 0),
                () -> Assertions.assertEquals(weapon.getStackSize(), 1),
                () -> Assertions.assertEquals(weapon.getAmount(), 1),
                () -> Assertions.assertEquals(weapon.getName(), "Sword"),
                () -> Assertions.assertEquals(weapon.getDamage(), 15),
                () -> Assertions.assertEquals(weapon.getDurability(), 50),
                () -> Assertions.assertEquals(weapon.getWeaponType(), WeaponType.DAGGER)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Weapon.Builder builder1 = Weapon.weaponBuilder().itemId(-1).name("Sword").damage(15).durability(50).weaponType(WeaponType.DAGGER);
        Weapon.Builder builder2 = Weapon.weaponBuilder().itemId(0).name(null).damage(15).durability(50).weaponType(WeaponType.DAGGER);
        Weapon.Builder builder3 = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(0).durability(50).weaponType(WeaponType.DAGGER);
        Weapon.Builder builder4 = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(15).durability(-1).weaponType(WeaponType.DAGGER);
        Weapon.Builder builder5 = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(15).durability(50).weaponType(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder4::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder5::build)
        );
    }


    @Test
    public void testEquals(){
        Weapon dagger = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(15).durability(50).weaponType(WeaponType.DAGGER).build();
        Weapon spear = Weapon.weaponBuilder().itemId(0).name("Spear").damage(15).durability(50).weaponType(WeaponType.SPEAR).build();
        Weapon dagger2 = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(15).durability(50).weaponType(WeaponType.DAGGER).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(dagger, dagger2),
                () -> Assertions.assertEquals(dagger, dagger),
                () -> Assertions.assertNotEquals(dagger, spear),
                () -> Assertions.assertNotEquals(dagger, new Object()),
                () -> Assertions.assertEquals(dagger.hashCode(), dagger2.hashCode()),
                () -> Assertions.assertNotEquals(dagger.hashCode(), spear.hashCode()),
                () -> Assertions.assertEquals(dagger.hashCode(), dagger.hashCode())
        );
    }

    @Test
    public void testSetDurability(){
        Weapon dagger = Weapon.weaponBuilder().itemId(0).name("Dagger").damage(15).durability(50).weaponType(WeaponType.DAGGER).build();

        Assertions.assertAll(
                () -> dagger.setDurability(0),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> dagger.setDurability(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> dagger.setDurability(1))
        );
    }
}
