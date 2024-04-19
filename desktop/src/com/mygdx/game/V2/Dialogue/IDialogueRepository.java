package com.mygdx.game.V2.Dialogue;

import java.util.NoSuchElementException;

/**
 * Holds all the dialogue related data.
 * Maps input lines to {@link IResponseData}s.
 */
public interface IDialogueRepository {

    /**
     * Fetches the {@link IResponseData} for the given input.
     * @param input The input. Cannot be null.
     *
     * @return The {@link IResponseData}.
     * @throws NoSuchElementException If no mapping for the given input was found.
     */
    IResponseData getResponseData(String input);
}
