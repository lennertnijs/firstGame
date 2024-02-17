package com.mygdx.game.Tree;

import com.mygdx.game.Entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The API to interact with the {@link Breakable}s.
 */
public class BreakableService {

    private final BreakableRepository repository;

    /**
     * The constructor for the {@link BreakableService} class.
     * Should only be initialised ONCE, as it also initialises a new repository.
     */
    public BreakableService(){
        this.repository = new BreakableRepository();
    }

    /**
     * Fetches and returns a {@link List} of all the {@link Breakable}s.
     * Does NOT defensively copy currently.
     *
     * @return A {@link List} of all {@link Breakable}s
     */
    public List<Breakable> getAllBreakables(){
        return repository.getBreakables();
    }

    /**
     * Returns the amount of currently existing {@link Breakable}s (of all {@link BreakableType}s).
     *
     * @return The amount of {@link Breakable}s
     */
    public int getBreakableAmount(){
        return repository.getBreakableAmount();
    }

    /**
     * Fetches and returns all breakables of type STONE.
     * More specifically, fetches and returns a {@link List} of all {@link Breakable}s of {@link BreakableType} STONE.
     *
     * @return A {@link List} of all STONE {@link Breakable}s
     */
    public List<Breakable> getAllStones(){
        return repository.getBreakables().stream().filter(e -> e.getBreakableType() == BreakableType.STONE)
                .collect(Collectors.toList());
    }

    /**
     * Fetches and returns all breakables of type TREE.
     * More specifically, fetches and returns a {@link List} of all {@link Breakable}s of {@link BreakableType} TREE.
     *
     * @return A {@link List} of all TREE {@link Breakable}s
     */
    public List<Breakable> getAllTrees(){
        return repository.getBreakables().stream().filter(e -> e.getBreakableType() == BreakableType.TREE)
                .collect(Collectors.toList());
    }

    /**
     * Adds the passed {@link Breakable} to the back-end repository, if it is non-null.
     * @param breakable The {@link Breakable} to be added. Must not be {@code null}.
     */
    public void add(Breakable breakable){
        Objects.requireNonNull(breakable, "Cannot add a null breakable object to the repository.");
        repository.add(breakable);
    }

    /**
     * Removes the passed {@link Breakable} from the back-end repository.
     * More specifically, removes the {@link Breakable} from the repository that is equal to the passed {@link Breakable}.
     * @param breakable The {@link Breakable} to be removed.
     */
    public void remove(Breakable breakable){
        repository.remove(breakable);
    }

    public void hit(Breakable breakable, float damage){
        Objects.requireNonNull(breakable, "Cannot damage a null breakable");
        if(damage < 0){
            throw new IllegalArgumentException("Cannot damage a breakable negatively.");
        }
        breakable.damage(damage);
        if(breakable.isBroken()){
            repository.remove(breakable);
        }
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

    public boolean pointCollidesWithBreakable(Position position){
        return getBreakableCollidingWithPoint(position).isPresent();
    }

    public boolean hitBoxCollidesWithBreakable(Position position, int width, int height){
        return getBreakableCollidingWithHitBox(position, width, height).isPresent();
    }
}
