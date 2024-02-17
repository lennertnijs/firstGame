package com.mygdx.game.Tree;

import com.mygdx.game.Entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mygdx.game.Constants.STONE_HEIGHT;
import static com.mygdx.game.Constants.STONE_WIDTH;

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

    public boolean pointCollidesWithBreakable(Position position){
        Objects.requireNonNull(position, "Cannot check breakable collision with a null point");
        int x = position.getX();
        int y = position.getY();
        for(Breakable breakable: repository.getBreakables()){
            int breakableX = breakable.getPosition().getX();
            int breakableY = breakable.getPosition().getY();
            boolean collides = x >= breakableX && x < breakableX + breakable.getWidth() &&
                               y >= breakableY && y < breakableY + breakable.getHardness();
            if(collides){
                return true;
            }
        }
        return false;
    }

    public boolean hitBoxCollidesWithBreakable(Position position, int width, int height){
        Objects.requireNonNull(position, "Cannot check breakable collision with a null hit box starting point");
        if(width < 0 || height < 0){
            throw new IllegalArgumentException("Cannot check breakable collision with a hit box of negative measures");
        }
        int x = position.getX();
        int y = position.getY();
        for(Breakable breakable : repository.getBreakables()){
            int breakableX = breakable.getPosition().getX();
            int breakableY = breakable.getPosition().getY();
            int breakableWidth = breakable.getWidth();
            int breakableHeight = breakable.getHeight();
            boolean collides = x + width >= breakableX && x <= breakableX + breakableWidth &&
                               y + height >= breakableY && y <= breakableY + breakableHeight;
            if(collides){
                return true;
            }
        }
        return false;
    }

    public Optional<Breakable> getBreakableCollidingWithPoint(Position position){
        Objects.requireNonNull(position, "Cannot check breakable collision with a null point");
        int x = position.getX();
        int y = position.getY();
        for(Breakable breakable: repository.getBreakables()){
            int breakableX = breakable.getPosition().getX();
            int breakableY = breakable.getPosition().getY();
            boolean collides = x >= breakableX && x < breakableX + breakable.getWidth() &&
                    y >= breakableY && y < breakableY + breakable.getHardness();
            if(collides){
                return Optional.of(breakable);
            }
        }
        return null;
    }

    public Optional<Breakable> getBreakableCollidingWithHitBox(Position position, int width, int height){
        Objects.requireNonNull(position, "Cannot check breakable collision with a null hit box starting point");
        if(width < 0 || height < 0){
            throw new IllegalArgumentException("Cannot check breakable collision with a hit box of negative measures");
        }
        int x = position.getX();
        int y = position.getY();
        for(Breakable breakable : repository.getBreakables()){
            int breakableX = breakable.getPosition().getX();
            int breakableY = breakable.getPosition().getY();
            int breakableWidth = breakable.getWidth();
            int breakableHeight = breakable.getHeight();
            boolean collides = x + width >= breakableX && x <= breakableX + breakableWidth &&
                    y + height >= breakableY && y <= breakableY + breakableHeight;
            if(collides){
                return Optional.of(breakable);
            }
        }
        return null;
    }
}
