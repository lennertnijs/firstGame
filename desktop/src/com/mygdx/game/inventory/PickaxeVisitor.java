package com.mygdx.game.inventory;

import com.mygdx.game.Bat.Monster;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.UseItemState;
import com.mygdx.game.npc.NPC;

public class PickaxeVisitor implements ItemVisitor {

    @Override
    public void visit(Player player) {
        player.changeState(new UseItemState(player));
        player.setActivity("mine");
    }

    @Override
    public void visit(NPC npc) {

    }

    @Override
    public void visit(Monster monster){

    }

    @Override
    public void visit(Breakable breakable) {

    }
}
