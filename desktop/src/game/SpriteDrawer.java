package game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.game_object.GameObject;
import game.inventory.Inventory;
import game.inventory.Item;
import game.inventory.ItemType;

import java.util.Map;

public class SpriteDrawer {

    private final MyGame game;

    public SpriteDrawer(MyGame game){
        this.game = game;
    }

    public void draw(GameObject o){
        TextureRegion texture = o.getTexture();
        int x = o.getPosition().x();
        int y = o.getPosition().y();
        int width = o.getWidth();
        int height = o.getHeight();
        game.batch.draw(texture, x, y, width, height);
    }

    public void draw(Texture texture){
        game.batch.draw(texture, 0, 0, 1920, 1080);
    }

    public void drawInventory(Inventory inventory, Map<ItemType, TextureRegion> textures){
        int startX = 250;
        int startY = 900;
        int inventorySize = inventory.size();
        int count = 1;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 9; j++){
                if(count > inventorySize){
                    return;
                }
                int actualX = startX + j * 150;
                int actualY = startY - i * 150;
                Item item = inventory.getItem(count - 1);
                if(item != null){
                    game.batch.draw(textures.get(inventory.getItem(count - 1).type), actualX, actualY, 100, 100);
                }
                count++;
            }
        }
    }

    public void drawActiveItem(Item item, Map<ItemType, TextureRegion> textures){
        game.batch.draw(textures.get(item.type), 950, 50, 100, 100);
    }

    public void drawText(String text){
        game.font.draw(game.batch, text, 400, 250);
    }
}
