package com.mygdx.game.loot;

public final class MockLootTable implements ILootTable{

    public MockLootTable(){
    }

    @Override
    public Loot getRandomLoot() {
        return new Loot("mock", 64);
    }
}
