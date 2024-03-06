package com.mygdx.game.Clock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Headless.HeadlessApplication;
import com.mygdx.game.Headless.HeadlessApplicationConfiguration;
import com.mygdx.game.MockGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class ClockServiceTest {

    private ClockService clockService;

    @BeforeEach
    public void initialiseHeadless(){
        HeadlessApplication headlessApp = new HeadlessApplication(
                new MockGame(), new HeadlessApplicationConfiguration());
        Gdx.gl = mock(GL20.class);
        clockService = new ClockService();
    }
}
