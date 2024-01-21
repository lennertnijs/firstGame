import com.mygdx.game.Graph.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestVertex {

    @Test
    public void testVertex(){
        Vertex v1 = new Vertex("location1", 200, 200);
        Assertions.assertEquals(v1.getName(), "location1");
        Assertions.assertEquals(v1.getX(), 200);
        Assertions.assertEquals(v1.getY(), 200);
    }
}
