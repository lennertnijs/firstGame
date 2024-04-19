package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action.Action;

import java.util.List;

/**
 * Holds all the data surrounding dialogue, including the active inputs.
 * Cannot be modified, but can modify itself.
 */
public interface IDialogueData {
    /**
     * @return The active inputs.
     */
    List<String> getActiveInputs();

    /**
     * Processes the given input.
     * If the input was part of the active inputs, removes it from the active inputs, and does:
     * - Retrieves the response.
     * - Adds the new inputs to the active inputs.
     * - Executes all the {@link Action}s.
     * If the input was not part of the active inputs, returns.
     * @param input The input. Cannot be null.
     */
    void processInput(String input);
}
