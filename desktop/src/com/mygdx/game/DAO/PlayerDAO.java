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
        String miningPath = file.getString("miningPath");
        CharacterTextureRepository textureRepository = CharacterTextureRepository.builder()
                .idleTextures(getIdleTextures(idlePath))
                .movementAnimations(getMovingAnimations(movingPath))
                .miningAnimations(getMiningAnimations(miningPath))
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
        idleTextures.put(Direction.UP, new TextureRegion(texture, 0, 0, 16, 32));
        idleTextures.put(Direction.RIGHT, new TextureRegion(texture, 16, 0, 16, 32));
        idleTextures.put(Direction.DOWN, new TextureRegion(texture, 32, 0, 16, 32));
        idleTextures.put(Direction.LEFT, new TextureRegion(texture, 48, 0, 16, 32));
        return idleTextures;
    }

    private Map<Direction, Animation<TextureRegion>> getMovingAnimations(String spritePath){
        Texture texture = new Texture(Gdx.files.internal(spritePath));
        Map<Direction, Animation<TextureRegion>> movingAnimations = new HashMap<>();
        Animation<TextureRegion> animationUp = new Animation<>(((float) 1 /6) , getTextureRegionLine(texture, 0));
        movingAnimations.put(Direction.UP, animationUp);
        Animation<TextureRegion> animationRight = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 33));
        movingAnimations.put(Direction.RIGHT, animationRight);
        Animation<TextureRegion> animationDown = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 66));
        movingAnimations.put(Direction.DOWN, animationDown);
        Animation<TextureRegion> animationLeft = new Animation<>(((float) 1 /6), getTextureRegionLine(texture, 99));
        movingAnimations.put(Direction.LEFT, animationLeft);
        return movingAnimations;
    }

    private TextureRegion[] getTextureRegionLine(Texture texture, int y){
        TextureRegion[] textureRegions = new TextureRegion[6];
        textureRegions[0] = new TextureRegion(texture, 0, y, 18, 33);
        textureRegions[1] = new TextureRegion(texture, 18, y, 18, 33);
        textureRegions[2] = new TextureRegion(texture, 2*18, y, 18, 33);
        textureRegions[3] = new TextureRegion(texture, 3*18, y, 18, 33);
        textureRegions[4] = new TextureRegion(texture, 4*18, y, 18, 33);
        textureRegions[5] = new TextureRegion(texture, 5*18, y, 18, 33);
        return textureRegions;
    }

    private Map<Direction, Animation<TextureRegion>> getMiningAnimations(String spritePath){
        Texture texture = new Texture(Gdx.files.internal(spritePath));
        Map<Direction, Animation<TextureRegion>> miningAnimation = new HashMap<>();
        Animation<TextureRegion> animationUp = new Animation<>(1/4F, getTextureRegionRightToLeft(texture, 0, 31));
        miningAnimation.put(Direction.UP, animationUp);
        Animation<TextureRegion> animationRight = new Animation<>(1/4F, getTextureRegionRightToLeft(texture, 42, 42));
        miningAnimation.put(Direction.RIGHT, animationRight);
        Animation<TextureRegion> animationDown = new Animation<>(1/4F, getTextureRegionRightToLeft(texture, 84, 31));
        miningAnimation.put(Direction.DOWN, animationDown);
        Animation<TextureRegion> animationLeft = new Animation<>(1/4F, getTextureRegionLeftToRight(texture, 126, 42));
        miningAnimation.put(Direction.LEFT, animationLeft);
        return miningAnimation;
    }

    private TextureRegion[] getTextureRegionRightToLeft(Texture texture, int y, int x_width){
        TextureRegion[] textureRegions = new TextureRegion[4];
        textureRegions[0] = new TextureRegion(texture, 0, y, x_width, 42);
        textureRegions[1] = new TextureRegion(texture, x_width, y, x_width, 42);
        textureRegions[2] = new TextureRegion(texture, 2*x_width, y, x_width, 42);
        textureRegions[3] = new TextureRegion(texture, 3*x_width, y, x_width, 42);
        return textureRegions;
    }

    private TextureRegion[] getTextureRegionLeftToRight(Texture texture, int y, int x_width){
        TextureRegion[] textureRegions = new TextureRegion[4];
        textureRegions[0] = new TextureRegion(texture, 3*x_width, y, x_width, 42);
        textureRegions[1] = new TextureRegion(texture, 2*x_width, y, x_width, 42);
        textureRegions[2] = new TextureRegion(texture, x_width, y, x_width, 42);
        textureRegions[3] = new TextureRegion(texture, 0, y, x_width, 42);
        return textureRegions;
    }
}
