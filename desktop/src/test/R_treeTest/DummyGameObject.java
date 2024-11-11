package R_treeTest;

import com.mygdx.game.r_tree.GameObject2D;
import com.mygdx.game.r_tree.Rectangle;

public final class DummyGameObject implements GameObject2D {

    private final int x, y;
    private final int width, height;

    public DummyGameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
