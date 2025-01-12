package game.loot;

import java.util.Objects;

public final class Loot {

    private final String name;
    private final int amount;

    public Loot(String name, int amount){
        this.name = Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        this.amount = amount;
    }

    public String name(){
        return name;
    }

    public int amount(){
        return amount;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Loot))
            return false;
        Loot loot = (Loot) other;
        return name.equals(loot.name) && amount == loot.amount;
    }

    @Override
    public int hashCode(){
        return name.hashCode() * 31 + amount;
    }

    @Override
    public String toString(){
        return String.format("loot[name=%s, amount=%d]", name, amount);
    }
}
