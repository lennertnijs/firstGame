package com.mygdx.game.Tree;

import com.mygdx.game.Entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BreakableService {

    private final BreakableRepository repository;

    public BreakableService(){
        this.repository = new BreakableRepository();
    }

    public List<Breakable> getAllBreakables(){
        return repository.getBreakables();
    }

    public int getBreakableAmount(){
        return repository.getBreakableAmount();
    }

    public List<Breakable> getAllStones(){
        return repository.getBreakables().stream().filter(e -> e.getBreakableType() == BreakableType.STONE)
                .collect(Collectors.toList());
    }

    public List<Breakable> getAllTrees(){
        return repository.getBreakables().stream().filter(e -> e.getBreakableType() == BreakableType.TREE)
                .collect(Collectors.toList());
    }

    public void add(Breakable breakable){
        Objects.requireNonNull(breakable, "Cannot add a null breakable object to the repository.");
        repository.add(breakable);
    }

    public void remove(Breakable breakable){
        repository.remove(breakable);
    }

    public void pointCollidesWithBreakable(Position position){
        Objects.requireNonNull(position, "Cannot check breakable collision with a null point");
        int collisionX = position.getX();
        int collisionY = position.getY();
        for(Breakable breakable: repository.getBreakables()){
            int breakableX = breakable.getPosition().getX();
            int breakableY = breakable.getPosition().getY();

        }
    }
}
