package com.mygdx.game.inventory.item_visitor;

import com.mygdx.game.monster.Monster;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.UseItemState;
import com.mygdx.game.npc.NPC;

public final class PickaxeUseVisitor implements ItemVisitor {


    @Override
    public void visit(Player player) {
        player.changeState(new UseItemState(player));
        player.setActivity("mine");
    }

    @Override
    public void visit(NPC npc) {

    }

    @Override
    public void visit(Monster monster) {

    }

    @Override
    public void visit(Breakable breakable) {

    }
}
