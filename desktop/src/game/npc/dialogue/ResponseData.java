package game.npc.dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ResponseData(String response, List<String> newInputs) {

    public ResponseData(String response, List<String> newInputs) {
        Objects.requireNonNull(response, "Response is null.");
        Objects.requireNonNull(newInputs, "New input list is null.");
        if (newInputs.contains(null))
            throw new NullPointerException("New input list contains null.");
        this.response = response;
        this.newInputs = new ArrayList<>(newInputs);
    }

    @Override
    public List<String> newInputs() { return new ArrayList<>(newInputs);
    }
}
