package game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.Breakables.Breakable;
import game.game_object.GameObjectType;
import game.game_object.Transform;
import game.game_object.renderer.Frame;
import game.game_object.renderer.Renderer;
import game.game_object.renderer.StaticRenderer;
import game.util.Vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BreakableLoader {

    public static Map<GameObjectType, TextureRegion> loadTextures(){
        Map<GameObjectType, TextureRegion> breakableTextures = new HashMap<>();
        String[] breakableTypes = new String[]{"tree", "rock"};
        for (String breakableType : breakableTypes) {
            TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("breakable/" + breakableType + ".png")));
            breakableTextures.put(GameObjectType.valueOf(breakableType.toUpperCase()), textureRegion);
        }
        return breakableTextures;
    }

    public static List<Breakable> loadBreakables(){
        List<Breakable> breakables = new ArrayList<>();
        Transform transform1 = new Transform(new Vec2(250, 500));
        TextureRegion rockTexture = new TextureRegion(new Texture(Gdx.files.internal("breakable/rock.png")));
        Renderer renderer1 = new StaticRenderer(Frame.builder().texture(rockTexture).build());
        Breakable rock = Breakable.builder().transform(transform1).renderer(renderer1).map("main").type(GameObjectType.ROCK)
                .health(500).hardness(250).lootTable(null).build();
        breakables.add(rock);

        Transform transform2 = new Transform(new Vec2(500, 250));
        TextureRegion treeTexture = new TextureRegion(new Texture(Gdx.files.internal("breakable/tree.png")));
        Renderer renderer2 = new StaticRenderer(Frame.builder().texture(treeTexture).build());
        Breakable tree = Breakable.builder().transform(transform2).renderer(renderer2).map("main").type(GameObjectType.TREE)
                .health(500).hardness(250).lootTable(null).build();
        breakables.add(tree);
        return breakables;

    }
}
