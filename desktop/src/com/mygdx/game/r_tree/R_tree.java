package com.mygdx.game.r_tree;

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

    public Node<T> chooseSubTree(Rectangle rectangle){
        Objects.requireNonNull(rectangle);
        return chooseSubTreeRecursive(root, rectangle);
    }

    private Node<T> chooseSubTreeRecursive(Node<T> current, Rectangle rectangle){
        if(current.isLeaf()){
            return current;
        }
        Node<T> optimalNode = null;
        int minimumScore = Integer.MAX_VALUE;
        for(Node<T> child : current.getChildren()){
            if(child.contains(rectangle)){
                return chooseSubTreeRecursive(child, rectangle);
            }
            Rectangle updatedMBR = findMinimumBoundingRectangle(child.getRectangle(), rectangle);
            int score = updatedMBR.area() - child.getRectangle().area();

            if(score < minimumScore){
                minimumScore = score;
                optimalNode = child;
            }else if(score == minimumScore && updatedMBR.perimeter() < child.getRectangle().perimeter()){
                optimalNode = child;
            }
        }
        Objects.requireNonNull(optimalNode);
        return chooseSubTreeRecursive(optimalNode, rectangle);
    }

    private Rectangle findMinimumBoundingRectangle(Rectangle r1, Rectangle r2){
        int min_x = Math.min(r1.x() ,r2.x());
        int min_y = Math.min(r1.y(), r2.y());
        int max_x = Math.max(r1.x() + r1.width(), r2.x() + r2.width());
        int max_y = Math.max(r1.y() + r1.height(), r2.y() + r2.height());
        return new Rectangle(min_x, min_y, max_x - min_x, max_y - min_y);
    }
}
