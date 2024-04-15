package com.mygdx.game.V2.Dialogue;

import java.util.List;

public interface IDialogueData {
    List<String> getActive();
    void process(String text);
}
