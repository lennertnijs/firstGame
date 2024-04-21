package com.mygdx.game.V2.Graph;

import java.util.Objects;

public final class Vertex<T> {

    private final T value;

    public Vertex(T value){
        Objects.requireNonNull(value, "Vertex cannot store null value.");
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Vertex<?>))
            return false;
        Vertex<?> vertex = (Vertex<?>) other;
        return value.equals(vertex.value);
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Vertex[value=%s]", value);
    }
}
