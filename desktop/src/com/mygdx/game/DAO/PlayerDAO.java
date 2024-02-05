package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Direction;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.ToolType;
import com.mygdx.game.Player.CharacterTextureRepository;
import com.mygdx.game.Player.Inventory;
import com.mygdx.game.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerDAO {

    public PlayerDAO(){

    }

    public Player readPlayer(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/player.json"));

        String name = file.getString("name");

        int x = file.getInt("x");
        int y = file.getInt("y");
        Position position = Position.builder().x(x).y(y).build();

        JsonValue inventoryJson = file.get("inventory");
        Inventory inventory = readInventory(inventoryJson);

        int currentItemIndex = file.getInt("currentItemIndex");

        Direction direction = Direction.valueOf(file.getString("direction"));

        String idlePath = file.getString("idlePath");
        String movingPath = file.getString("movingPath");
        CharacterTextureRepository textureRepository = CharacterTextureRepository.builder()
                .idleTextures(getIdleTextures(idlePath))
                .movementAnimations(getMovingAnimations(movingPath))
                .build();

        return Player.builder()
                .name(name)
                .position(position)
                .inventory(inventory)
                .currentItemIndex(currentItemIndex)
                .direction(direction)
                .textureRepository(textureRepository)
                .build();
    }

    private Inventory readInventory(JsonValue inventoryJson){
        ArrayList<Item> items = new ArrayList<>();
        for(JsonValue itemJson : inventoryJson){
            Item item = null;
            String type = itemJson.getString("type");
            int id = itemJson.getInt("id");
            String name = itemJson.getString("name");
            if(type.equals("tool")){
                int efficiency = itemJson.getInt("efficiency");
                int durability = itemJson.getInt("durability");
                ToolType toolType = ToolType.valueOf(itemJson.getString("toolType"));
                item = Tool.toolBuilder().itemId(id).name(name)
                        .efficiency(efficiency).durability(durability).toolType(toolType).build();
            }else if(type.equals("weapon")){
                // tba
            }else{
                int stackSize = itemJson.getInt("stackSize");
                int amount = itemJson.getInt("amount");
                item = Item.itemBuilder().itemId(id).name(name).stackSize(stackSize).amount(amount).build();
            }
            items.add(item);
        }
        Item[] itemArray = new Item[items.size()];
        int index = 0;
        for(Item item: items){
            itemArray[index] = item;
            index++;
        }
        return Inventory.builder().items(itemArray).build();
    }

    private Map<Direction, TextureRegion> getIdleTextures(String spritePath){
        Texture texture = new Texture(Gdx.files.internal(spritePath));
        Map<Direction, TextureRegion> idleTextures = new HashMap<>();
        idleTextures.put(Direction.RIGHT, new TextureRegion(texture, 0, 0, 64, 64));
        idleTextures.put(Direction.LEFT, new TextureRegion(texture, 0, 64, 64, 64));
        idleTextures.put(Direction.DOWN, new TextureRegion(texture, 0, 2*64, 64, 64));
        idleTextures.put(Direction.UP, new TextureRegion(texture, 0, 3*64, 64, 64));
        return idleTextures;
    }

    private Map<Direction, Animation<TextureRegion>> getMovingAnimations(String spritePath){
        Texture texture = new Texture(Gdx.files.internal(spritePath));
        Map<Direction, Animation<TextureRegion>> movingAnimations = new HashMap<>();
        Animation<TextureRegion> animationRight = new Animation<>(((float) 1 /6) , getTextureRegionLine(texture, 0));
        movingAnimations.put(Direction.RIGHT, animationRight);
        Animation<TextureRegion> animationLeft = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 64));
        movingAnimations.put(Direction.LEFT, animationLeft);
        Animation<TextureRegion> animationDown = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 2*64));
        movingAnimations.put(Direction.DOWN, animationDown);
        Animation<TextureRegion> animationUp = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 3*64));
        movingAnimations.put(Direction.UP, animationUp);
        return movingAnimations;
    }

    private TextureRegion[] getTextureRegionLine(Texture texture, int y){
        TextureRegion[] textureRegions = new TextureRegion[6];
        textureRegions[0] = new TextureRegion(texture, 0, y, 64, 64);
        textureRegions[1] = new TextureRegion(texture, 64, y, 64, 64);
        textureRegions[2] = new TextureRegion(texture, 2*64, y, 64, 64);
        textureRegions[3] = new TextureRegion(texture, 3*64, y, 64, 64);
        textureRegions[4] = new TextureRegion(texture, 4*64, y, 64, 64);
        textureRegions[5] = new TextureRegion(texture, 5*64, y, 64, 64);
        return textureRegions;
    }
}
