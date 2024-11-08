package com.mygdx.game.r_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class R_tree<T extends GameObject2D> {

    private final Node<T> root;
    private final int maxEntries;
    private int height;
    private int size;

    public R_tree(int x, int y, int width, int height, int maxEntries){
        this.root = new Node<>(x, y, width, height);
        this.maxEntries = maxEntries;
    }

    public boolean isFree(Rectangle rectangle){
        Objects.requireNonNull(rectangle);
        return isFreeRecursive(root, rectangle);
    }

    private boolean isFreeRecursive(Node<T> current, Rectangle rectangle){
        for(Node<T> child : current.getChildren()){
            if(!child.contains(rectangle)) continue;

            if(child.isLeaf()){
                for(GameObject2D object : child.getObjects()){
                    if(object.intersects(rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height())){
                        return false;
                    }
                }
            }else{
                if(!isFreeRecursive(child, rectangle)){
                    return false;
                }
            }
        }
        return true;
    }

    public void insert(T object){
        Objects.requireNonNull(object);
        Node<T> appropriateLeaf = chooseSubTree(object.getRectangle());
        appropriateLeaf.addObject(object);
        if(appropriateLeaf.getChildren().size() <= maxEntries){
            return;
        }
        split(appropriateLeaf);
    }

    public void split(Node<T> node){
        List<T> objects = node.getObjects();
        Pair<T> seeds = pickSeeds(node.getObjects());
        List<T> group1 = new ArrayList<>();
        group1.add(seeds.first());
        List<T> group2 = new ArrayList<>();
        group2.add(seeds.second());
        distributeEntries(objects, group1, group2);
        for(T object : objects){
            if(group1.size() < maxEntries){
                group1.add(object);
            }else{
                group2.add(object);
            }
        }
    }

    // pick the two objects that have the biggest area in their MBR, without including their own area
    public Pair<T> pickSeeds(List<T> objects){
        T first = null;
        T second = null;
        int value = Integer.MIN_VALUE;
        for(T o1 : objects){
            for(T o2 : objects){
                Rectangle MBR = Rectangle.createMinimumBoundingRectangle(o1.getRectangle(), o2.getRectangle());
                int d = MBR.area() - o1.getRectangle().area() - o2.getRectangle().area();
                if(d > value){
                    first = o1;
                    second = o2;
                }
            }
        }
        return new Pair<>(first, second);
    }

    // distribute all the objects in the two groups
    // based on least area increase of MBR, then smallest area, then least entries. then just fuck it
    public void distributeEntries(List<T> objects, List<T> group1, List<T> group2){
        Rectangle r1 = group1.get(0).getRectangle();
        Rectangle r2 = group2.get(0).getRectangle();
        while(objects.size() > 0){
            T next = pickNext(objects, r1, r2);
            Rectangle newR1 = Rectangle.createMinimumBoundingRectangle(next.getRectangle(), r1);
            Rectangle newR2 = Rectangle.createMinimumBoundingRectangle(next.getRectangle(), r2);
            if(newR1.area() - r1.area() < newR2.area() - r2.area()){
                group1.add(next);
                r1 = newR1;
            }else if(newR1.area() - r1.area() > newR2.area() - r2.area()){
                group2.add(next);
                r2 = newR2;
            }
            // area increase is equal;
            if(newR1.area() < newR2.area()){
                group1.add(next);
                r1 = newR1;
            }else if(newR1.area() > newR2.area()){
                group2.add(next);
                r2 = newR2;
            }

            // areas of groups are equal
            if(group1.size() < group2.size()){
                group1.add(next);
                r1 = newR1;
            }else{
                group2.add(next);
                r2 = newR2;
            }
            objects.remove(next);
        }
    }

    // picks the next object, such that the difference between the area increases for each group is maximal
    public T pickNext(List<T> objects, Rectangle r1, Rectangle r2){
        T next = objects.get(0);
        int d = Integer.MIN_VALUE;
        for(T object : objects){
            Rectangle group1MBR = Rectangle.createMinimumBoundingRectangle(new Rectangle[]{r1, object.getRectangle()});
            Rectangle group2MBR = Rectangle.createMinimumBoundingRectangle(new Rectangle[]{r2, object.getRectangle()});
            int group1Diff = group1MBR.area() - r1.area();
            int group2Diff = group2MBR.area() - r2.area();
            if(Math.abs(group1Diff - group2Diff) > d){
                d = Math.abs(group1Diff - group2Diff);
                next = object;
            }
        }
        return next;
    }

    public Node<T> chooseSubTree(Rectangle rectangle){
        Objects.requireNonNull(rectangle);
        return chooseSubTreeRecursive(root, rectangle);
    }

    private Node<T> chooseSubTreeRecursive(Node<T> current, Rectangle rectangle){
        if(current.isLeaf()){
            return current;
        }
        Node<T> optimalNode = current.getChildren().get(0);
        for(Node<T> child : current.getChildren()){
            if(child.contains(rectangle)){
                return chooseSubTreeRecursive(child, rectangle);
            }
            // MBR = minimum bounding rectangle
            Rectangle MBR = Rectangle.createMinimumBoundingRectangle(new Rectangle[]{rectangle, child.getRectangle()});
            if(MBR.area() < optimalNode.getArea() || MBR.area() == optimalNode.getArea() && MBR.perimeter() < optimalNode.getPerimeter()){
                optimalNode = child;
            }
        }
        return chooseSubTreeRecursive(optimalNode, rectangle);
    }
}
