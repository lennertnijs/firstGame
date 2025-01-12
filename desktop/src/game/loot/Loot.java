package game.loot;

import java.util.Objects;

public record Loot(String name, int amount) {

    public Loot(String name, int amount) {
        this.name = Objects.requireNonNull(name, "Name is null.");
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        this.amount = amount;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Loot loot))
            return false;
        return name.equals(loot.name) && amount == loot.amount;
    }

    @Override
    public String toString() {
        return String.format("loot[name=%s, amount=%d]", name, amount);
    }
}
