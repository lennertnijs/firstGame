package com.mygdx.game.V2.Dialogue;

import java.util.List;

public interface IResponseData {

    Line getResponse();
    List<Action> getActions();
    List<Line> getNextPrompts();
}
