package com.mygdx.game.r_tree;

import com.mygdx.game.util.Vec2;

import java.util.*;

public final class R_tree<T extends GameObject2D> {

    private Node<T> root;
    private final int max;
    private final int min;
    private int depth;
    private int size;
    private int overflowDepth;

    public R_tree(int maxEntries){
        this.root = new Node<>();
        if(maxEntries < 2){
            throw new IllegalArgumentException("Maximum entry count must be 2 or larger for any functional R*-tree.");
        }
        this.max = maxEntries;
        this.min = (int) Math.max(Math.floor(maxEntries * 0.40), 1);
        this.depth = 1;
        this.size = 0;
        this.overflowDepth = -1;
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

    public void insertData(T object){
        insertObject(Objects.requireNonNull(object));
    }


    private void insertObject(T object){
        Node<T> leaf = chooseSubTree(object.getRectangle(), this.depth);
        leaf.addObject(object);
        this.size++;
        if(leaf.getObjects().size() > max){
            propagateUpwards(leaf, this.depth);
        }
    }

    private void insertNode(Node<T> node){
        int depth = 1;
        Node<T> current = node;
        while(current.isInternal()){
            depth++;
            current = current.getChildren().get(0);
        }
        Node<T> internal = chooseSubTree(node.getRectangle(), this.depth - depth + 1);
        internal.addChild(node);
        node.setParent(internal);
        if(node.getParent().getChildren().size() > max){
            propagateUpwards(node, this.depth - depth + 1);
        }
    }

    private void propagateUpwards(Node<T> node, int depth){
        if(overflowTreatment(node, depth--) != Action.SPLIT){
            return;
        }
        this.overflowDepth = -1;
        while(!node.isRoot() && node.getParent().getChildren().size() > max){
            node = node.getParent();
            overflowTreatment(node, depth--);
            this.overflowDepth = -1;
        }
    }

    public Action overflowTreatment(Node<T> node, int depth){
        if(!node.isRoot() && overflowDepth != depth){
            this.overflowDepth = depth;
            reInsert(node);
            return Action.REINSERT;
        }else{
            this.overflowDepth = depth;
            split(node);
            return Action.SPLIT;
        }

    }

    public void reInsert(Node<T> node){
        if(node.isLeaf()){
            reInsertLeaf(node);
            return;
        }
        reInsertInternal(node);
    }

    private void reInsertLeaf(Node<T> leaf){
        List<T> sorted = leaf.getObjects();
        Vec2 center = leaf.getRectangle().center();
        Comparator<T> comparator = Comparator.comparing(r -> r.getRectangle().center().distanceTo(center));
        sorted.sort(comparator);
        int p = min + 1;
        List<T> removed = new ArrayList<>(p);
        for(int i = 0; i < p; i++){
            leaf.getObjects().remove(sorted.get(i));
            this.size--;
            removed.add(sorted.get(i));
        }
        leaf.updateRectangle();
        for(T removedObject : removed){
            insertObject(removedObject);
        }
    }

    private void reInsertInternal(Node<T> internal){
        List<Node<T>> sorted = internal.getChildren();
        Vec2 center = internal.getRectangle().center();
        Comparator<Node<T>> comparator = Comparator.comparing(r -> r.getRectangle().center().distanceTo(center));
        sorted.sort(comparator);
        int p = min;
        List<Node<T>> removed = new ArrayList<>(p);
        for(int i = 0; i < p; i++){
            internal.remove(sorted.get(i));
            removed.add(sorted.get(i));
        }
        internal.updateRectangle();
        for(Node<T> removedNode : removed){
            insertNode(removedNode);
        }
    }

    private void split(Node<T> node){
        if(node.isLeaf()){
            splitLeaf(node);
        }else{
            splitInternal(node);
        }
    }

    private void splitInternal(Node<T> internal){
        if(!internal.isInternal()){
            throw new IllegalArgumentException("Cannot split the internal Node, as it is a leaf.");
        }
        List<Node<T>> sortedThroughBestAxis = chooseSplitAxisNode(internal.getChildren());
        int index = chooseSplitIndexNode(sortedThroughBestAxis);
        List<Node<T>> first = sortedThroughBestAxis.subList(0, index);
        List<Node<T>> second = sortedThroughBestAxis.subList(index, sortedThroughBestAxis.size());
        Node<T> child1 = new Node<>();
        for(Node<T> object : first){
            child1.addChild(object);
        }
        Node<T> child2 = new Node<>();
        for(Node<T> object : second){
            child2.addChild(object);
        }
        updateSplit(internal, child1, child2);
    }

    private void splitLeaf(Node<T> leaf){
        Objects.requireNonNull(leaf);
        if(!leaf.isLeaf()){
            throw new IllegalArgumentException("Cannot split the given node as it is not a leaf Node.");
        }
        List<T> sortedThroughBestAxis = chooseSplitAxisData(leaf.getObjects());
        int index = chooseSplitIndexData(sortedThroughBestAxis);
        if(index < 0 || index > sortedThroughBestAxis.size()){
            throw new IllegalStateException("Root split index is invalid.");
        }
        List<T> first = sortedThroughBestAxis.subList(0, index);
        List<T> second = sortedThroughBestAxis.subList(index, sortedThroughBestAxis.size());

        Node<T> child1 = new Node<>(first);
        Node<T> child2 = new Node<>(second);
        updateSplit(leaf, child1, child2);
    }

    private void updateSplit(Node<T> node, Node<T> child1, Node<T> child2){
        Node<T> parent;
        if(node.isRoot()){
            parent = new Node<>();
            this.root = parent;
            this.depth++;
        }else{
            parent = node.getParent();
            parent.remove(node);
        }

        parent.addChild(child1);
        child1.setParent(parent);
        parent.addChild(child2);
        child2.setParent(parent);

        child1.updateRectangle();
        child2.updateRectangle();
        parent.updateRectangle();
    }

    private int chooseSplitIndexData(List<T> objects){
        return chooseSplitIndex(objects.stream().map(GameObject2D::getRectangle).toList());
    }

    private int chooseSplitIndexNode(List<Node<T>> objects){
        return chooseSplitIndex(objects.stream().map(Node::getRectangle).toList());
    }

    /**
     * Attempts all possible splits, and picks the most optimal one. Returns this split index.
     * Note that the split index is the index of the FIRST element to be in the SECOND group.
     * --
     * More specifically, attempts all possible split indices, and picks the split with the least area overlap.
     * (and smallest area, if area overlap is tied)
     */
    private int chooseSplitIndex(List<Rectangle> rectangles){
        int minimumOverlap = Integer.MAX_VALUE;
        int minimumArea = Integer.MAX_VALUE;
        int solutionIndex = 0;
        for(int k = 1; k < (max - 2 * min + 2); k++){
            int firstGroupAmount = min - 1 + k;
            List<Rectangle> first = rectangles.subList(0, firstGroupAmount);
            List<Rectangle> second = rectangles.subList(firstGroupAmount, rectangles.size());
            Rectangle r1 = Rectangle.createMinimumBounding(first);
            Rectangle r2 = Rectangle.createMinimumBounding(second);
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
    private List<T> chooseSplitAxisData(List<T> objects){
        Comparator<T> xComparator = Comparator.comparing((T o) -> o.getRectangle().x())
                                    .thenComparing((T o) -> o.getRectangle().x() + o.getRectangle().width());
        List<T> x_axis_sort = new ArrayList<>(objects);
        x_axis_sort.sort(xComparator);

        Comparator<T> yComparator = Comparator.comparing((T o) -> o.getRectangle().y())
                                    .thenComparing((T o) -> o.getRectangle().y() + o.getRectangle().height());
        List<T> y_axis_sort = new ArrayList<>(objects);
        y_axis_sort.sort(yComparator);
        int horizontalMarginSum = calculateMarginSumData(x_axis_sort);
        int verticalMarginSum = calculateMarginSumData(y_axis_sort);
        if(horizontalMarginSum < verticalMarginSum){
            return x_axis_sort;
        }else{
            return y_axis_sort;
        }
    }

    private List<Node<T>> chooseSplitAxisNode(List<Node<T>> nodes){
        Comparator<Node<T>> xComp = Comparator
                .comparing((Node<T> n) -> n.getRectangle().x())
                .thenComparing((Node<T> n) -> n.getRectangle().x() + n.getRectangle().width());
        Comparator<Node<T>> yComp = Comparator
                .comparing((Node<T> n) -> n.getRectangle().y())
                .thenComparing((Node<T> n) -> n.getRectangle().y() + n.getRectangle().height());
        List<Node<T>> sortedAlongX = new ArrayList<>(nodes);
        sortedAlongX.sort(xComp);
        List<Node<T>> sortedAlongY = new ArrayList<>(nodes);
        sortedAlongY.sort(yComp);

        int horizontalMarginSum = calculateMarginSumNode(sortedAlongX);
        int verticalMarginSum = calculateMarginSumNode(sortedAlongY);
        if(horizontalMarginSum <= verticalMarginSum){
            return sortedAlongX;
        }
        return sortedAlongY;
    }

    private int calculateMarginSumNode(List<Node<T>> objects){
        return calculateMarginSum(objects.stream().map(Node::getRectangle).toList());
    }

    private int calculateMarginSumData(List<T> objects){
       return calculateMarginSum(objects.stream().map(GameObject2D::getRectangle).toList());
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
     * @param rectangles The list of objects. MUST BE SORTED!
     */
    public int calculateMarginSum(List<Rectangle> rectangles){
        int marginSum = 0;
        for(int k = 1; k < (max - 2 * min + 2); k++){
            int firstGroupAmount = min - 1 + k;
            List<Rectangle> first = rectangles.subList(0, firstGroupAmount);
            List<Rectangle> second = rectangles.subList(firstGroupAmount, rectangles.size());
            Rectangle r1 = Rectangle.createMinimumBounding(first);
            Rectangle r2 = Rectangle.createMinimumBounding(second);
            marginSum += r1.perimeter();
            marginSum += r2.perimeter();
        }
        return marginSum;
    }

    /**
     * Selects the optimal Node at the given target depth for insertion of the given Rectangle, and returns it.
     */
    private Node<T> chooseSubTree(Rectangle rectangle, int targetDepth){
        Objects.requireNonNull(rectangle);
        if(targetDepth <= 0 || targetDepth > this.depth){
            throw new IllegalArgumentException("The max tree depth to search cannot be negative or larger than the tree's height.");
        }
        return chooseSubTree(root, rectangle, targetDepth, 1);
    }

    /**
     * Picks the appropriate Node of the R*-tree, at the given target depth, in which to insert the Node/object with
     * the given Rectangle.
     * --
     * To select the most appropriate Node, we use multiple LEXICOGRAPHIC metrics, depending on the current depth.
     * In each metric, we add the Rectangle to its current MBR (Minimum Bounding Rectangle) to create a new, usually
     * larger MBR, which we use to rank Nodes.
     *
     * @return The appropriate node to insert into, at the provided depth.
     */
    private Node<T> chooseSubTree(Node<T> current, Rectangle rectangle, int targetDepth, int depth){
        if(depth < 0 || depth > targetDepth){
            throw new IllegalArgumentException("Current depth is invalid.");
        }
        if(depth == targetDepth){
            return current;
        }
        if(current.getChildren().size() == 0){
            Node<T> node = new Node<>();
            node.setParent(current);
            current.addChild(node);
        }
        Node<T> optimalChild;
        if(depth == this.depth - 1){
            optimalChild = selectOptimalNodeLeafParent(current.getChildren(), rectangle);
        }else{
            optimalChild = selectOptimalNodeInternal(current.getChildren(), rectangle);
        }
        return chooseSubTree(optimalChild, rectangle, targetDepth, depth + 1);
    }

    /***
     * If the current depth is 1 layer above the tree's leaf layer, 3 metrics will be used to select the best child:
     * - 1) The sum of the overlaps of the new MBR with all its siblings' MBRs is calculated for each child.
     *      The child with the smallest overlap sum is selected.
     * - 2) The area enlargement of the new MBR compared to its original MBR is calculated for each child.
     *      The child with the smallest area enlargement is selected.
     * - 3) The total area of the new MBR is calculated for each child.
     *      The child with the smallest total area is selected.
     * - 4) If all 3 are tied, the first one where all 3 metrics are tied will be selected.
     */
    private Node<T> selectOptimalNodeLeafParent(List<Node<T>> nodes, Rectangle rectangle){
        Node<T> solution = nodes.get(0);
        int minimumOverlap = Integer.MAX_VALUE;
        int minimumEnlargement = Integer.MAX_VALUE;
        int minimumArea = Integer.MAX_VALUE;
        for(Node<T> child : nodes){
            Rectangle MBR = Rectangle.createMinimumBoundingRectangle(child.getRectangle(), rectangle);
            int enlargement = MBR.area() - child.getRectangle().area();
            int overlapSum = 0;
            for(Node<T> otherChild : nodes){
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
        return solution;
    }

    /**
     * If the current depth is ANYTHING besides leaf level or 1 above the leaf level, 3 metrics will be used:
     * - 1) The area enlargement of the new MBR compared to its original MBR is calculated for each child.
     *      The child with the smallest area enlargement is selected.
     * - 2) The total area of the new MBR is calculated for each child.
     *      The child with the smallest total area is selected.
     * - 3) The perimeter of the new MBR is calculated for each child.
     *      The child with the smallest perimeter is selected.
     * - 4) If all 3 are tied, the first one where all 3 metrics are tied will be selected.
     */
    private Node<T> selectOptimalNodeInternal(List<Node<T>> nodes, Rectangle rectangle){
        Node<T> solution = nodes.get(0);
        int minimumEnlargement = Integer.MAX_VALUE;
        for(Node<T> child : nodes){
            Rectangle MBR = Rectangle.createMinimumBoundingRectangle(child.getRectangle(), rectangle);
            int enlargement = MBR.area() - child.getRectangle().area();
            if(enlargement < minimumEnlargement || enlargement == minimumEnlargement && MBR.area() < solution.getArea() ||
                    enlargement == minimumEnlargement && MBR.area() == solution.getArea() && MBR.perimeter() < solution.getPerimeter()){
                minimumEnlargement = enlargement;
                solution = child;
            }
        }
        return solution;
    }


    public int getActualSize(){
        return getActualSizeDFS(root);
    }

    private int getActualSizeDFS(Node<T> node){
        int count = 0;
        if(node.isLeaf()){
            count += node.getObjects().size();
        }else{
            for(Node<T> child : node.getChildren()){
                count += getActualSizeDFS(child);
            }
        }
        return count;
    }
}
