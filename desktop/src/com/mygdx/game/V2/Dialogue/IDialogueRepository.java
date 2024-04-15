package com.mygdx.game.V2.Dialogue;

import java.util.List;

public interface IDialogueRepository {

    Line getResponse(Line line);
    List<Action> getActions(Line line);
    List<Line> getNextPrompts(Line line);
}
