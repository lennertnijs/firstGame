package ItemTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Headless.HeadlessApplication;
import com.mygdx.game.Headless.HeadlessApplicationConfiguration;
import com.mygdx.game.Item.*;
import com.mygdx.game.MockGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ItemControllerTest {


    private String firstTextureString;
    private String secondTextureString;
    private ItemController itemController;


    @BeforeEach
    public void initialiseHeadless(){
        HeadlessApplication headlessApplication = new HeadlessApplication(new MockGame(),
                new HeadlessApplicationConfiguration());
        Gdx.gl = mock(GL20.class);
        itemController = new ItemController();
        firstTextureString = new Texture("items/pickaxe.png").toString();
        secondTextureString = new Texture("items/diamond_axe.png").toString();

    }

    @Test
    public void testGetTexture(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemController.getTexture(0).toString(), firstTextureString),
                () -> Assertions.assertEquals(itemController.getTexture(1).toString(), secondTextureString),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemController.getTexture(-1))
        );
    }

    @Test
    public void testCanUseTool(){
        Tool tool = Tool.toolBuilder().itemId(0).name("name").efficiency(5).durability(5).toolType(ToolType.AXE).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemController.canUseTool(tool)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.canUseTool(null))
        );
    }

    @Test
    public void testUseTool(){
        Tool tool = Tool.toolBuilder().itemId(0).name("name").efficiency(5).durability(5).toolType(ToolType.AXE).build();
        Assertions.assertAll(
                () -> itemController.useTool(tool),
                () -> Assertions.assertEquals(tool.getDurability(), 4),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.useTool(null))
        );
    }

    @Test
    public void testCanUseWeapon(){
        Weapon weapon = Weapon.weaponBuilder().itemId(0).name("name").damage(5).durability(5).weaponType(WeaponType.SPEAR).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemController.canUseWeapon(weapon)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.canUseWeapon(null))
        );
    }

    @Test
    public void testUseWeapon(){
        Weapon weapon = Weapon.weaponBuilder().itemId(0).name("name").damage(5).durability(5).weaponType(WeaponType.SPEAR).build();
        Assertions.assertAll(
                () -> itemController.useWeapon(weapon),
                () -> Assertions.assertEquals(weapon.getDurability(), 4),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.useWeapon(null))
        );
    }

    @Test
    public void testCanIncreaseByAmount(){
        Item item = Item.itemBuilder().itemId(0).stackSize(40).name("name").amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemController.canIncrease(item, 35)),
                () -> Assertions.assertFalse(itemController.canIncrease(item, 36)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemController.canIncrease(item, -1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.canIncrease(null, 1))
        );
    }


    @Test
    public void testIncreaseByAmount(){
        Item item = Item.itemBuilder().itemId(0).stackSize(40).name("name").amount(5).build();
        Assertions.assertAll(
                () -> itemController.increase(item, 34),
                () -> Assertions.assertEquals(item.getAmount(), 39),
                () -> itemController.increase(item, 1),
                () -> Assertions.assertEquals(item.getAmount(), 40),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemController.increase(item, -1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.increase(null, 1))
        );
    }


    @Test
    public void testCanDecreaseByAmount(){
        Item item = Item.itemBuilder().itemId(0).stackSize(40).name("name").amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemController.canDecrease(item, 5)),
                () -> Assertions.assertFalse(itemController.canDecrease(item, 6)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemController.canDecrease(item, -1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.canDecrease(null, 1))
        );
    }

    @Test
    public void testDecreaseByAmount(){
        Item item = Item.itemBuilder().itemId(0).stackSize(40).name("name").amount(5).build();
        Assertions.assertAll(
                () -> itemController.decrease(item, 4),
                () -> Assertions.assertEquals(item.getAmount(), 1),
                () -> itemController.decrease(item, 1),
                () -> Assertions.assertEquals(item.getAmount(), 0),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemController.decrease(item, -1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.decrease(null, 1))
        );
    }

    @Test
    public void testIsEmpty(){
        Item item = Item.itemBuilder().itemId(0).stackSize(40).name("name").amount(5).build();
        itemController.decrease(item, 4);
        Assertions.assertAll(
                () -> Assertions.assertFalse(itemController.isEmpty(item)),
                () -> itemController.decrease(item, 1),
                () -> Assertions.assertTrue(itemController.isEmpty(item)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemController.isEmpty(null))
        );
    }

}
