package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action.Action;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Holds all the data surrounding dialogue, including the active inputs.
 * Cannot be modified, but can modify itself.
 */
public interface IDialogueData {
    /**
     * @return A copy of the active input lines.
     */
    List<String> getActiveInputs();

    /**
     * Processes the given input line.
     * If the input is valid, removes it from the active inputs, and:
     * - Retrieves the response.
     * - Adds the new inputs to the active inputs.
     * - Executes all the {@link Action}s linked to the input.
     * @param input The input. Cannot be null.
     *
     * @throws NoSuchElementException If no response was found to the given input line.
     * @throws NullPointerException If the input line is null.
     */
    void processInput(String input);
}
