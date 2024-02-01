package com.mygdx.game.Item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Headless.HeadlessApplication;
import com.mygdx.game.Headless.HeadlessApplicationConfiguration;
import com.mygdx.game.MockGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ItemServiceTest {

    private String firstTextureString;
    private String secondTextureString;
    private ItemService itemService;


    @BeforeEach
    public void initialiseHeadless(){
        HeadlessApplication headlessApplication = new HeadlessApplication(new MockGame(),
                new HeadlessApplicationConfiguration());
        Gdx.gl = mock(GL20.class);
        itemService = new ItemService();
        firstTextureString = new Texture("items/pickaxe.png").toString();
        secondTextureString = new Texture("items/diamond_axe.png").toString();

    }

    @Test
    public void testGetItemTexture(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemService.getItemTexture(0).toString(),
                        firstTextureString),
                () -> Assertions.assertEquals(itemService.getItemTexture(1).toString(),
                        secondTextureString),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemService.getItemTexture(-1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> itemService.getItemTexture(2))
        );
    }

    @Test
    public void testCanUseTool(){
        Tool tool = Tool.toolBuilder().itemId(0).name("axe").efficiency(5).durability(5).toolType(ToolType.AXE).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemService.canUseTool(tool)),
                () -> tool.setDurability(0),
                () -> Assertions.assertFalse(itemService.canUseTool(tool))
        );
    }

    @Test
    public void testUseTool(){
        Tool tool = Tool.toolBuilder().itemId(0).name("axe").efficiency(5).durability(2).toolType(ToolType.AXE).build();
        Assertions.assertAll(
                () -> itemService.useTool(tool),
                () -> Assertions.assertEquals(tool.getDurability(), 1),
                () -> itemService.useTool(tool),
                () -> Assertions.assertEquals(tool.getDurability(), 0),
                () -> itemService.useTool(tool),
                () -> Assertions.assertEquals(tool.getDurability(), 0)
        );
    }


    @Test
    public void testCanUseWeapon(){
        Weapon weapon = Weapon.weaponBuilder().itemId(0).name("axe").damage(5).durability(5).weaponType(WeaponType.SWORD).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemService.canUseWeapon(weapon)),
                () -> weapon.setDurability(0),
                () -> Assertions.assertFalse(itemService.canUseWeapon(weapon))
        );
    }

    @Test
    public void testUseWeapon(){
        Weapon weapon = Weapon.weaponBuilder().itemId(0).name("axe").damage(5).durability(2).weaponType(WeaponType.SWORD).build();
        Assertions.assertAll(
                () -> itemService.useWeapon(weapon),
                () -> Assertions.assertEquals(weapon.getDurability(), 1),
                () -> itemService.useWeapon(weapon),
                () -> Assertions.assertEquals(weapon.getDurability(), 0),
                () -> itemService.useWeapon(weapon),
                () -> Assertions.assertEquals(weapon.getDurability(), 0)
        );
    }

    @Test
    public void testCanIncreaseByAmount(){
        Item item = Item.itemBuilder().name("stone").stackSize(32).itemId(0).amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemService.canIncreaseByAmount(item, 27)),
                () -> Assertions.assertFalse(itemService.canIncreaseByAmount(item, 28))
        );
    }

    @Test
    public void testIncreaseByAmount(){
        Item item = Item.itemBuilder().name("stone").stackSize(32).itemId(0).amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getAmount(), 5),
                () -> itemService.increaseByAmount(item, 27),
                () -> Assertions.assertEquals(item.getAmount(), 32),
                () -> itemService.increaseByAmount(item, 1),
                () -> Assertions.assertEquals(item.getAmount(), 32)
        );
    }


    @Test
    public void testCanDecreaseByAmount(){
        Item item = Item.itemBuilder().name("stone").stackSize(32).itemId(0).amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemService.canDecreaseByAmount(item, 5)),
                () -> Assertions.assertFalse(itemService.canDecreaseByAmount(item, 6))
        );
    }

    @Test
    public void testDecreaseByAmount(){
        Item item = Item.itemBuilder().name("stone").stackSize(32).itemId(0).amount(5).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getAmount(), 5),
                () -> itemService.decreaseByAmount(item, 5),
                () -> Assertions.assertEquals(item.getAmount(), 0),
                () -> itemService.decreaseByAmount(item, 1),
                () -> Assertions.assertEquals(item.getAmount(), 0)
        );
    }

    @Test
    public void testIsEmptyStack(){
        Item item1 = Item.itemBuilder().itemId(0).name("axe").stackSize(64).amount(1).build();
        Tool tool1 = Tool.toolBuilder().itemId(0).name("axe").durability(5).efficiency(5).toolType(ToolType.AXE).build();
        Assertions.assertAll(
                () -> Assertions.assertFalse(itemService.isEmptyStack(item1)),
                () -> Assertions.assertFalse(itemService.isEmptyStack(tool1)),
                () -> itemService.decreaseByAmount(item1, 1),
                () -> itemService.decreaseByAmount(tool1, 1),
                () -> Assertions.assertTrue(itemService.isEmptyStack(item1)),
                () -> Assertions.assertTrue(itemService.isEmptyStack(tool1))
        );

    }
}
