package game;

import game.game_object.GameObjectType;
import game.inventory.ItemType;

public record DamageAmpKey(GameObjectType objectType, ItemType itemType) {

}
