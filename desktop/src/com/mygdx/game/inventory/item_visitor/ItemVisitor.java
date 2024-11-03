package com.mygdx.game.inventory.item_visitor;

import com.mygdx.game.monster.Monster;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.player.Player;
import com.mygdx.game.npc.NPC;

public interface ItemVisitor {
    void visit(Player player);
    void visit(NPC npc);
    void visit(Monster monster);
    void visit(Breakable breakable);
}
