package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

        JsonValue textureRepositoryJson = file.get("textureRepository");
        CharacterTextureRepository textureRepository = readTextureRepository(textureRepositoryJson);

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


    private CharacterTextureRepository readTextureRepository(JsonValue textureRepositoryJson){
        Map<Direction, Texture> idleTextures = readIdleTextures(textureRepositoryJson.get("IDLING"));
        Map<Direction, Animation<Texture>> movingTextures = readAnimatedTextures(textureRepositoryJson.get("WALKING"));
        return CharacterTextureRepository.builder()
                .idleTextures(idleTextures)
                .movementAnimations(movingTextures)
                .build();

    }

    private Map<Direction, Texture> readIdleTextures(JsonValue idleJson){
        Map<Direction, Texture> idleTextures = new HashMap<>();
        idleTextures.put(Direction.UP, new Texture(idleJson.getString("UP")));
        idleTextures.put(Direction.RIGHT, new Texture(idleJson.getString("RIGHT")));
        idleTextures.put(Direction.DOWN, new Texture(idleJson.getString("DOWN")));
        idleTextures.put(Direction.LEFT, new Texture(idleJson.getString("LEFT")));
        return idleTextures;
    }

    private Map<Direction, Animation<Texture>> readAnimatedTextures(JsonValue animatedJson){
        Map<Direction, Animation<Texture>> animatedTextures = new HashMap<>();
        ArrayList<Animation<Texture>> animationList = new ArrayList<>();
        for(JsonValue oneDirectionJson : animatedJson){
            Texture frame1 = new Texture(oneDirectionJson.getString("firstFrame"));
            Texture frame2 = new Texture(oneDirectionJson.getString("secondFrame"));
            Texture frame3 = new Texture(oneDirectionJson.getString("thirdFrame"));
            Texture frame4 = new Texture(oneDirectionJson.getString("fourthFrame"));
            Texture[] textureList = {frame1, frame2, frame3, frame4};
            Animation<Texture> animation = new Animation<>(0.25F, textureList);
            animationList.add(animation);
        }
        for(int i = 0; i < Direction.values().length; i++){
            animatedTextures.put(Direction.values()[i], animationList.get(i));
        }
        return animatedTextures;
    }
}
