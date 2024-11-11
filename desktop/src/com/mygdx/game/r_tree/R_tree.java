package com.mygdx.game.r_tree;

import com.mygdx.game.util.Vec2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class R_tree<T extends GameObject2D> {

    private Node<T> root;
    private final int max;
    private final int min;
    private int depth;
    private int size;
    private int overflowCount;

    public R_tree(int maxEntries){
        this.root = new Node<>();
        if(maxEntries < 2){
            throw new IllegalArgumentException("Maximum entry count must be 2 or larger for any functional R*-tree.");
        }
        this.max = maxEntries;
        this.min = (int) Math.ceil(maxEntries * 0.40);
        this.depth = 1;
        this.size = 0;
        this.overflowCount = 0;
    }

    public enum Axis {
        X_AXIS,
        Y_AXIS
    }

    public enum Action {
        SPLIT,
        REINSERT
    }

    public Node<T> getRoot(){
        return root;
    }

    public int getMaxEntries(){
        return max;
    }

    public int getMinEntries(){
        return min;
    }

    public int getSize(){
        return size;
    }

    public int getDepth(){
        return depth;
    }

//    public boolean isFree(Rectangle rectangle){
//        Objects.requireNonNull(rectangle);
//        return isFreeRecursive(root, rectangle);
//    }
//
//    private boolean isFreeRecursive(Node<T> current, Rectangle rectangle){
//        for(Node<T> child : current.getChildren()){
//            if(child.getRectangle().overlapWith(rectangle) == 0) continue;
//
//            if(child.isLeaf()){
//                for(GameObject2D object : child.getObjects()){
//                    if(rectangle.overlapWith(object.getRectangle()) != 0){
//                        return false;
//                    }
//                }
//            }else{
//                if(!isFreeRecursive(child, rectangle)){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public void insert(T object){
        Objects.requireNonNull(object);
        Node<T> insertNode = chooseSubTree(object.getRectangle(), depth);

        insertNode.addObject(object);
        this.size++;
        if(insertNode.getObjects().size() <= max){
            return;
        }
        int currentDepth = this.depth;
        if(overflowTreatment(insertNode, currentDepth--) == Action.SPLIT){
            while(insertNode.getParent() != null && overflowTreatment(insertNode.getParent(), currentDepth--) == Action.SPLIT){
                insertNode = insertNode.getParent();
            }
        }
        this.overflowCount = 0;
    }

    public Action overflowTreatment(Node<T> node, int depth){
        overflowCount += 1;
        if(depth != 1 && overflowCount == 1){
            reInsert(node);
            return Action.REINSERT;
        }else{
            split(node);
            return Action.SPLIT;
        }
    }

    public void reInsert(Node<T> node){
        List<T> sorted = new ArrayList<>(node.getObjects().size());
        Vec2 center = node.getRectangle().center();
        for(T object : node.getObjects()){
            if(sorted.isEmpty()){
                sorted.add(object);
            }
            int index = 0;
            for(T sortedObject : sorted){
                if(object.getRectangle().center().distanceTo(center) > sortedObject.getRectangle().center().distanceTo(center)){
                    sorted.add(index, object);
                }
                index++;
            }
        }
        int p = min;
        List<T> removed = new ArrayList<>(p);
        for(int i = 0; i < p; i++){
            removed.add(sorted.get(i));
            this.size--;
            node.getObjects().remove(sorted.get(i));
        }
        node.updateRectangle();
        for(T removedObject : removed){
            insert(removedObject);
        }
    }

    // updated
    private void split(Node<T> node){
        Objects.requireNonNull(node);
        List<T> sortedThroughBestAxis = chooseSplitAxis(node.getObjects());
        int index = chooseSplitIndex(sortedThroughBestAxis);
        List<T> first = sortedThroughBestAxis.subList(0, index);
        List<T> second = sortedThroughBestAxis.subList(index, sortedThroughBestAxis.size());
        Node<T> child1 = new Node<>();
        for(T object : first){
            child1.addObject(object);
        }
        Node<T> child2 = new Node<>();
        for(T object : second){
            child2.addObject(object);
        }

        Node<T> possibleNewRoot;
        if(node.equals(root)){
            possibleNewRoot = new Node<>();
            possibleNewRoot.addChild(child1);
            possibleNewRoot.addChild(child2);
            for(Node<T> child : node.getChildren()){
                if(!child.equals(node)){
                    possibleNewRoot.addChild(child);
                }
            }
            this.root = possibleNewRoot;
            this.depth++;
            return;
        }else{
            node.getParent().addChild(child1);
            node.getParent().addChild(child2);
            node.getParent().removeChild(node);
        }
        while(!node.equals(root)){
            node.updateRectangle();
            node = node.getParent();
        }
        node.updateRectangle();
    }

    /**
     * Attempts all possible splits, and picks the most optimal one. Returns this split index.
     * Note that the split index is the index of the FIRST element to be in the SECOND group.
     * --
     * More specifically, attempts all possible split indices, and picks the split with the least area overlap.
     * (and smallest area, if area overlap is tied)
     */
    private int chooseSplitIndex(List<T> objects){
        Objects.requireNonNull(objects);
        if(objects.contains(null)){
            throw new NullPointerException();
        }
        List<T> firstGroup = new ArrayList<>();
        List<T> secondGroup = new ArrayList<>();
        int minimumOverlap = Integer.MAX_VALUE;
        int minimumArea = Integer.MAX_VALUE;
        int solutionIndex = 0;
        for(int k = 1; k < (max - 2 * min + 2); k++){
            int firstGroupAmount = min - 1 + k;
            for(int i = 0; i < objects.size(); i++){
                if(i < firstGroupAmount){
                    firstGroup.add(objects.get(i));
                }else{
                    secondGroup.add(objects.get(i));
                }
            }
            Rectangle r1 = Rectangle.createMinimumBounding(firstGroup.stream().map(GameObject2D::getRectangle).toList());
            Rectangle r2 = Rectangle.createMinimumBounding(secondGroup.stream().map(GameObject2D::getRectangle).toList());
            int overlap = r1.overlapWith(r2);
            int area = r1.area() + r2.area();
            if(overlap < minimumOverlap || overlap == minimumOverlap && area < minimumArea){
                solutionIndex = firstGroupAmount;
                minimumOverlap = overlap;
                minimumArea = area;
            }
        }
        return solutionIndex;
    }

    /**
     * Chooses the optimal axis to split along, and returns the list of objects sorted along the optimal axis.
     * @param objects The list of objects to sort (and find the optimal axis for). Cannot be null. Cannot contain null.
     */
    private List<T> chooseSplitAxis(List<T> objects){
        Objects.requireNonNull(objects);
        if(objects.contains(null)){
            throw new NullPointerException();
        }
        List<T> x_axis_sort = mergeSort(objects, Axis.X_AXIS);
        List<T> y_axis_sort = mergeSort(objects, Axis.Y_AXIS);
        int horizontalMarginSum = calculateMarginSum(x_axis_sort);
        int verticalMarginSum = calculateMarginSum(y_axis_sort);
        if(horizontalMarginSum < verticalMarginSum){
            return x_axis_sort;
        }else{
            return y_axis_sort;
        }
    }

    /**
     * Calculates the margin sum of all predefined distributions of the list of objects along a given axis.
     * --
     * More specifically, creates distributions for k = 1,...,(M - 2*m + 2) exclusive, where for each k
     * the sorted objects are split into two groups:
     * - group 1 will contain the first m - 1 + k elements
     * - group 2 will contain all the other elements
     * The MBR for both these groups will then be calculated, and the margins added to the result.
     *
     * @param objects The list of objects. MUST BE SORTED!
     */
    private int calculateMarginSum(List<T> objects){
        int marginSum = 0;
        List<T> firstGroup = new ArrayList<>();
        List<T> secondGroup = new ArrayList<>();
        for(int k = 1; k < (max - 2 * min + 2); k++){
            int firstGroupAmount = min - 1 + k;
            for(int i = 0; i < objects.size(); i++){
                if(i < firstGroupAmount){
                    firstGroup.add(objects.get(i));
                }else{
                    secondGroup.add(objects.get(i));
                }
            }
            Rectangle r1 = Rectangle.createMinimumBounding(firstGroup.stream().map(GameObject2D::getRectangle).toList());
            Rectangle r2 = Rectangle.createMinimumBounding(secondGroup.stream().map(GameObject2D::getRectangle).toList());
            marginSum += r1.perimeter();
            marginSum += r2.perimeter();
            firstGroup.clear();
            secondGroup.clear();
        }
        return marginSum;
    }

    /**
     * Sorts the given list using merge sort. The Axis parameter provides the axis to sort along.
     * --
     * More specifically, sorts by left side, and if tied, sorts by right side. If both are equal, sorts in order.
     */
    private List<T> mergeSort(List<T> list, Axis axis){
        Objects.requireNonNull(list);
        Comparator<Rectangle> comparator;
        switch(axis){
            case X_AXIS -> comparator = Comparator.comparing(Rectangle::x).thenComparing(rect -> rect.x() + rect.width());
            case Y_AXIS -> comparator = Comparator.comparing(Rectangle::y).thenComparing(rect -> rect.y() + rect.height());
            default -> throw new IllegalStateException("Should be unreachable");
        }
        if(list.contains(null)){
            throw new NullPointerException();
        }
        if(list.size() <= 1){
            return list;
        }else{
            int splitIndex = list.size() / 2;
            List<T> left = mergeSort(list.subList(0, splitIndex), axis);
            List<T> right = mergeSort(list.subList(splitIndex, list.size()), axis);
            List<T> sorted = new ArrayList<>(left.size() + right.size());
            int leftIndex = 0;
            int rightIndex = 0;
            while(leftIndex < left.size() && rightIndex < right.size()){
                if(comparator.compare(left.get(leftIndex).getRectangle(), right.get(rightIndex).getRectangle()) <= 0){
                    sorted.add(left.get(leftIndex++));
                }else{
                    sorted.add(right.get(rightIndex++));
                }
            }
            while(leftIndex < left.size()){
                sorted.add(left.get(leftIndex++));
            }
            while(rightIndex < right.size()){
                sorted.add(right.get(rightIndex++));
            }
            return sorted;
        }
    }

    /**
     * Selects the optimal Node at the given target depth for insertion of the given Rectangle, and returns it.
     */
    private Node<T> chooseSubTree(Rectangle rectangle, int targetDepth){
        Objects.requireNonNull(rectangle);
        if(targetDepth < 0 || targetDepth > this.depth){
            throw new IllegalArgumentException("The max tree depth to search cannot be negative or larger than the tree's height.");
        }
        return chooseSubTree(root, rectangle, targetDepth, 1); // 1 for root
    }

    /**
     * Picks the appropriate Node of the R*-tree, at the given target depth, in which to insert the Node/object with
     * the given Rectangle.
     * --
     * To select the most appropriate Node, we use multiple LEXICOGRAPHIC metrics, depending on the current depth.
     * In each metric, we add the Rectangle to its current MBR (Minimum Bounding Rectangle) to create a new, usually
     * larger MBR, which we use to rank Nodes:
     * * *
     * If the current depth is the maximum depth (at the leaf layer), we return the current Node.
     * * *
     * If the current depth is 1 layer above the tree's leaf layer, 3 metrics will be used to select the best child:
     * - 1) The sum of the overlaps of the new MBR with all its siblings' MBRs is calculated for each child.
     *      The child with the smallest overlap sum is selected.
     * - 2) The area enlargement of the new MBR compared to its original MBR is calculated for each child.
     *      The child with the smallest area enlargement is selected.
     * - 3) The total area of the new MBR is calculated for each child.
     *      The child with the smallest total area is selected.
     * - 4) If all 3 are tied, the first one where all 3 metrics are tied will be selected.
     * * *
     * If the current depth is ANYTHING besides leaf level or 1 above the leaf level, 3 metrics will be used:
     * - 1) The area enlargement of the new MBR compared to its original MBR is calculated for each child.
     *      The child with the smallest area enlargement is selected.
     * - 2) The total area of the new MBR is calculated for each child.
     *      The child with the smallest total area is selected.
     * - 3) The perimeter of the new MBR is calculated for each child.
     *      The child with the smallest perimeter is selected.
     * - 4) If all 3 are tied, the first one where all 3 metrics are tied will be selected.
     *
     * @param current The current Node in the tree. Cannot be null.
     * @param rectangle The Rectangle of the soon-to-be added element in the tree. Cannot be null.
     * @param targetDepth The target depth to look for an appropriate Node. Should be the tree's depth if looking for a leaf.
     * @param depth The current depth in the tree. Cannot be negative or larger than the tree's depth.
     *
     * @return The appropriate node to insert into, at the provided depth.
     */
    private Node<T> chooseSubTree(Node<T> current, Rectangle rectangle, int targetDepth, int depth){
        Objects.requireNonNull(current);
        Objects.requireNonNull(rectangle);
        if(targetDepth < 0 || targetDepth > this.depth){
            throw new IllegalArgumentException("The max tree depth to search cannot be negative or larger than the tree's height.");
        }
        if(depth < 0 || depth > targetDepth){
            throw new IllegalArgumentException("The current tree depth cannot be negative, or larger than the max depth.");
        }
        if(depth == targetDepth){
            return current;
        }
        // leaf children
        if(depth == targetDepth - 1){
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
            return chooseSubTree(solution, rectangle, targetDepth, depth + 1);
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
            return chooseSubTree(solution, rectangle, targetDepth, depth + 1);
        }
    }
}
