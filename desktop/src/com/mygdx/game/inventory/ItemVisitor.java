package com.mygdx.game.inventory;

import com.mygdx.game.Bat.Monster;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Player.Player;
import com.mygdx.game.npc.NPC;

public interface ItemVisitor {
    void visit(Player player);
    void visit(NPC npc);
    void visit(Monster monster);
    void visit(Breakable breakable);
}
