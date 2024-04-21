package com.mygdx.game.V2.Graph;

public final class Edge<T> {

    private final Vertex<T> start;
    private final Vertex<T> end;
    private final int weight;

    public Edge(Vertex<T> start, Vertex<T> end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex<T> getStart(){
        return start;
    }

    public Vertex<T> getEnd(){
        return end;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Edge<?>))
            return false;
        Edge<?> edge = (Edge<?>) other;
        return start.equals(edge.start) && end.equals(edge.end) && weight == edge.weight;
    }

    @Override
    public int hashCode(){
        int result = start.hashCode();
        result = result * 31 + end.hashCode();
        result = result * 31 + weight;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Edge[start=%s, end=%s, weight=%d]", start, end, weight);
    }
}
