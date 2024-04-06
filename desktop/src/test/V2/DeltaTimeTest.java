package V2;

import com.mygdx.game.V2.TextureSelector.DeltaTime;
import com.mygdx.game.V2.TextureSelector.MockTimeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeltaTimeTest {

    private MockTimeProvider timeProvider;
    private DeltaTime deltaTime;

    @BeforeEach
    public void initialise(){
        timeProvider = new MockTimeProvider();
        deltaTime = DeltaTime.create(timeProvider);
    }

    @Test
    public void testConstructor(){
        assertEquals(deltaTime.getDelta(), 0);
        timeProvider.incrementMockValue();
        timeProvider.incrementMockValue();
        assertEquals(deltaTime.getDelta(), 2);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> DeltaTime.create(null));
    }

    @Test
    public void testToString(){
        String expectedString = "DeltaTime[Delta=0.000000, PreviousSystemTime=0]";
        assertEquals(deltaTime.toString(), expectedString);
    }
}
