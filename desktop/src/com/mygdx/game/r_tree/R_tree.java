package com.mygdx.game.r_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class R_tree<T extends GameObject2D> {

    private final Node<T> root;
    private final int M;
    private final int m;
    private int height;
    private int size;

    public R_tree(int x, int y, int width, int height, int maxEntries){
        this.root = new Node<>(x, y, width, height);
        this.M = maxEntries;
        this.m = (int) (maxEntries * 0.40);
    }

    // m = 40% of M
    // m = 8, M = 20

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

    // updated
    public void split(Node<T> node){
        List<T> sortedThroughBestAxis = chooseSplitAxis(node.getObjects());
        int index = chooseSplitIndex(sortedThroughBestAxis);
        // split
    }

    public int chooseSplitIndex(List<T> objects){
        // choose the distribution with the minimum overlap value (between group 1 and group 2)
        // resolve ties with minimum area value
        return 0;
    }

    // / M - 2m + 2 distributions, so 6 distributions
    // k from 1 -> 6
    // first group contains (m - 1)  + k entries, the second the other
    public List<T> chooseSplitAxis(List<T> objects){
        List<T> x_axis_sort = new ArrayList<>(objects.size());
        x_axis_sort.add(objects.get(0));
        List<T> y_axis_sort = new ArrayList<>(objects.size());
        y_axis_sort.add(objects.get(0));
        for(T object : objects){
            int i = 0;
            int j = 0;
            while(object.getRectangle().x() < x_axis_sort.get(i).getRectangle().x() ||
                    object.getRectangle().x() == x_axis_sort.get(i).getRectangle().x() && object.getRectangle().x() + object.getRectangle().width() < x_axis_sort.get(i).getRectangle().x() + x_axis_sort.get(i).getRectangle().width()){
                i++;
            }
            while(object.getRectangle().y() < y_axis_sort.get(j).getRectangle().y() ||
                    object.getRectangle().y() == y_axis_sort.get(j).getRectangle().y() && object.getRectangle().y() + object.getRectangle().height() < y_axis_sort.get(i).getRectangle().y() + y_axis_sort.get(i).getRectangle().height()){
                j++;
            }
            x_axis_sort.add(i, object);
        }
        int horizontalMarginSum = 0;
        int verticalMarginSum = 0;
        List<T> first = new ArrayList<>();
        List<T> second = new ArrayList<>();
        for(int k = 1; k < (M - 2*m + 2); k++){
            int firstGroupAmount = m - 1 + k;
            for(int i = 0; i < x_axis_sort.size(); i++){
                if(i < firstGroupAmount){
                    first.add(x_axis_sort.get(i));
                }else{
                    second.add(x_axis_sort.get(i));
                }
            }
            Rectangle r1 = Rectangle.createMinimumBoundingRectangle(first);
            Rectangle r2 = Rectangle.createMinimumBoundingRectangle(second);
            horizontalMarginSum += r1.perimeter();
            horizontalMarginSum += r2.perimeter();
            first.clear();
            second.clear();
        }

        for(int k = 1; k < (M - 2*m + 2); k++){
            int firstGroupAmount = m - 1 + k;
            for(int i = 0; i < y_axis_sort.size(); i++){
                if(i < firstGroupAmount){
                    first.add(y_axis_sort.get(i));
                }else{
                    second.add(y_axis_sort.get(i));
                }
            }
            Rectangle r1 = Rectangle.createMinimumBoundingRectangle(first);
            Rectangle r2 = Rectangle.createMinimumBoundingRectangle(second);
            verticalMarginSum += r1.perimeter();
            verticalMarginSum += r2.perimeter();
            first.clear();
            second.clear();
        }
        if(horizontalMarginSum < verticalMarginSum){
            return x_axis_sort;
        }else{
            return y_axis_sort;
        }
    }

    public Node<T> chooseSubTree(Rectangle rectangle){
        Objects.requireNonNull(rectangle);
        return chooseSubTree(root, rectangle);
    }

    // updated
    private Node<T> chooseSubTree(Node<T> current, Rectangle rectangle){
        if(current.isLeaf()){
            return current;
        }
        // leaf children
        if(current.getChildren().get(0).isLeaf()){
            Node<T> solution = current.getChildren().get(0);
            int minimumOverlap = Integer.MAX_VALUE;
            int minimumEnlargement = Integer.MAX_VALUE;
            int minimumArea = Integer.MAX_VALUE;
            for(Node<T> child : current.getChildren()){
                Rectangle MBR = Rectangle.createMinimumBoundingRectangle(child.getRectangle(), rectangle);
                int enlargement = MBR.area() - child.getRectangle().area();
                int overlapSum = 0;
                for(Node<T> otherChild : current.getChildren()){
                    if(otherChild == child) continue;
                    overlapSum += MBR.overlapWith(otherChild.getRectangle());
                    if(overlapSum > minimumOverlap){
                        break;
                    }
                }
                if(overlapSum < minimumOverlap || overlapSum == minimumOverlap && enlargement < minimumEnlargement ||
                        overlapSum == minimumOverlap && enlargement == minimumEnlargement && MBR.area() < minimumArea){
                    solution = child;
                    minimumOverlap = overlapSum;
                    minimumEnlargement = enlargement;
                    minimumArea = MBR.area();
                }
            }
            return chooseSubTree(solution, rectangle);
        }else{
            Node<T> solution = current.getChildren().get(0);
            int minimumEnlargement = Integer.MAX_VALUE;
            for(Node<T> child : current.getChildren()){
                Rectangle MBR = Rectangle.createMinimumBoundingRectangle(child.getRectangle(), rectangle);
                int enlargement = MBR.area() - child.getRectangle().area();
                if(enlargement < minimumEnlargement || enlargement == minimumEnlargement && MBR.area() < solution.getArea() ||
                    enlargement == minimumEnlargement && MBR.area() == solution.getArea() && MBR.perimeter() < solution.getPerimeter()){
                    minimumEnlargement = enlargement;
                    solution = child;
                }
            }
            return chooseSubTree(solution, rectangle);
        }
    }
}
