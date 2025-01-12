package game.inventory;

public final class Item {

    public final ItemType type;
    private int amount;
    private int durability;
    public final int maxDurability;
    public final int damage;

    public Item(Builder builder){
        this.type = builder.type;
        this.amount = builder.amount;
        this.durability = builder.durability;
        this.maxDurability = builder.maxDurability;
        this.damage = builder.damage;
    }

    public int getAmount(){
        return amount;
    }

    public int getDurability(){
        return durability;
    }

    public void decrementDurability(){
        this.durability = Math.max(this.durability - 1, 0);
    }

    public int increaseAmount(int increase){
        if(increase <= 0) {
            throw new IllegalArgumentException("Increase cannot be negative or 0.");
        }
        if(amount > type.stackSize){
            throw new IllegalStateException("Current amount is larger than the stack size.");
        }
        int actualIncrease = Math.min(increase, type.stackSize - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    public int decreaseAmount(int decrease){
        if(decrease <= 0) {
            throw new IllegalArgumentException("Decrease is negative or 0.");
        }
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private ItemType type = null;
        private int amount = 0;
        private int durability = 0;
        private int maxDurability = 0;
        private int damage = 0;

        public Builder itemType(ItemType type){
            this.type = type;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public Builder durability(int durability){
            this.durability = durability;
            return this;
        }

        public Builder maxDurability(int maxDurability){
            this.maxDurability = maxDurability;
            return this;
        }

        public Builder damage(int damage){
            this.damage = damage;
            return this;
        }

        public Item build(){
            assert(type != null);
            assert(amount > 0);
            assert(durability >= 0);
            assert(maxDurability >= 0);
            assert(damage >= 0);
            return new Item(this);
        }
    }
}
