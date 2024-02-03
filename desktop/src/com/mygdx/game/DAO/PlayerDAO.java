package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Direction;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Item.Item;
import com.mygdx.game.Player.Inventory;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.CharacterTextureRepository;

import java.util.HashMap;

public class PlayerDAO {

    public PlayerDAO(){

    }

    public Player readPlayer(){
        //JsonReader reader = new JsonReader();
        //JsonValue file = reader.parse("resources/player.json");

        Position position = Position.builder().x(960).y(510).build();

        Texture[] texture = new Texture[4];
        Texture frame1 = new Texture(Gdx.files.internal("images/lmaoxd.png"));
        Texture frame2 = new Texture(Gdx.files.internal("images/guy_right.png"));
        Texture frame3 = new Texture(Gdx.files.internal("images/guy.png"));
        Texture frame4 = new Texture(Gdx.files.internal("images/guy_left.png"));
        texture[0] = frame1;
        texture[1] = frame2;

        texture[2] = frame3;

        texture[3] = frame4;
        Animation<Texture> animation = new Animation<>(0.25F, texture);

        HashMap<Direction, Texture> map1 = new HashMap<>();
        map1.put(Direction.UP, frame1);
        map1.put(Direction.RIGHT, frame1);
        map1.put(Direction.DOWN, frame1);
        map1.put(Direction.LEFT, frame1);


        HashMap<Direction, Animation<Texture>> map2 = new HashMap<>();
        map2.put(Direction.UP, animation);
        map2.put(Direction.RIGHT, animation);
        map2.put(Direction.DOWN, animation);
        map2.put(Direction.LEFT, animation);

        CharacterTextureRepository characterTextureRepository = CharacterTextureRepository.builder().idleTextures(map1).movementAnimations(map2).build();


        return Player.builder().position(position).name("Bart")
                .inventory(Inventory.builder().items(new Item[0]).build())
                .textureRepository(characterTextureRepository)
                .currentItemIndex(0)
                .build();
    }
}
