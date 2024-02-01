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

public class ItemTextureRepositoryTest {

    private String firstTextureString;
    private String secondTextureString;

    @BeforeEach
    public void initialiseHeadless(){
        HeadlessApplication headlessApplication = new HeadlessApplication(new MockGame(),
                new HeadlessApplicationConfiguration());
        Gdx.gl = mock(GL20.class);
        firstTextureString = new Texture("items/pickaxe.png").toString();
        secondTextureString = new Texture("items/diamond_axe.png").toString();

    }

    @Test
    public void testGetItemTexture(){
        ItemTextureRepository itemTextureRepository = new ItemTextureRepository();
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemTextureRepository.getItemTexture(0).toString(), firstTextureString),
                () -> Assertions.assertEquals(itemTextureRepository.getItemTexture(1).toString(), secondTextureString),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> itemTextureRepository.getItemTexture(10000)),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> itemTextureRepository.getItemTexture(-1))
        );
    }
}
