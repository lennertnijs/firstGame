package com.mygdx.game.V2.Dialogue;

import java.util.List;

public interface IDialogueRepository {

    IResponseData getResponse(Line line);
}
