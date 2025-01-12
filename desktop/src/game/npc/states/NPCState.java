package game.npc.states;

public interface NPCState {

    String getState();
    void progress(double delta);
}
