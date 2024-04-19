package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action;

import java.util.List;

/**
 * Represents the response to a dialogue input.
 */
public interface IResponseData {

    /**
     * @return The response.
     */
    String getResponse();

    /**
     * @return The new input lines.
     */
    List<String> getNewInputs();

    /**
     * @return The {@link Action}s to be taken.
     */
    List<Action> getActions();
}
