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
}
