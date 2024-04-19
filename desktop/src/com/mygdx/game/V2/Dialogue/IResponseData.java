package com.mygdx.game.V2.Dialogue;

import java.util.List;

public interface IResponseData {

    String getResponse();
    List<Action> getActions();
    List<String> getNextPrompts();
}
