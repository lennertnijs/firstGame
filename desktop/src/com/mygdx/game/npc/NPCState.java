package com.mygdx.game.npc;

public interface NPCState {

    String getState();
    void progress(double delta);
}
