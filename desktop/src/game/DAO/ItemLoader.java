package game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.inventory.ItemType;

import java.util.HashMap;
import java.util.Map;

public final class ItemLoader {

    public static Map<ItemType, TextureRegion> loadItemTextures(){
        Map<ItemType, TextureRegion> itemTextures = new HashMap<>();
        String[] itemTypes = new String[]{"wood", "stone", "pickaxe", "axe", "sword", "bow"};
        for (String itemType : itemTypes) {
            TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("items/" + itemType + ".png")));
            itemTextures.put(ItemType.valueOf(itemType.toUpperCase()), textureRegion);
        }
        return itemTextures;
    }
}
