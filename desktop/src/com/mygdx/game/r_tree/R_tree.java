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
    private int overflowDepth;

    public R_tree(int maxEntries){
        this.root = new Node<>();
        if(maxEntries < 2){
            throw new IllegalArgumentException("Maximum entry count must be 2 or larger for any functional R*-tree.");
        }
        this.max = maxEntries;
        this.min = (int) Math.ceil(maxEntries * 0.40);
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
        insert(Objects.requireNonNull(object), this.depth);
    }


    private void insert(T object, int depth){
        Node<T> leaf = chooseSubTree(object.getRectangle(), depth);

        leaf.addObject(object);
        this.size++;

        if(leaf.getObjects().size() <= max){
            return;
        }

        int currentDepth = this.depth;
        if(overflowTreatment(leaf, currentDepth--) != Action.SPLIT){
            return;
        }
        this.overflowDepth = -1;
        Node<T> currentNode = leaf;
        while(!currentNode.isRoot() && currentNode.getParent().getChildren().size() > max){
            currentNode = currentNode.getParent();
            overflowTreatment(currentNode, currentDepth--);
            this.overflowDepth = -1;
        }
    }

    private void insertNode(Node<T> node){
        Node<T> toInsert = chooseSubTree(node.getRectangle(), overflowDepth);
        toInsert.addChild(node);
        node.setParent(toInsert);

        if(node.getChildren().size() <= max){
            return;
        }

        int currentDepth = this.depth;
        if(overflowTreatment(node, currentDepth--) != Action.SPLIT){
            return;
        }
        this.overflowDepth = -1;
        Node<T> currentNode = node;
        while(!currentNode.isRoot() && overflowTreatment(currentNode, currentDepth--) == Action.SPLIT){
            currentNode = currentNode.getParent();
            this.overflowDepth = -1;
        }
    }

    // adding a 9th element is not working. at all.
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
            removed.add(sorted.get(i));
            this.size--;
            leaf.getObjects().remove(sorted.get(i));
        }
        leaf.updateRectangle(); // cascade upwards?
        for(T removedObject : removed){
            insert(removedObject, depth);
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
            removed.add(sorted.get(i));
            this.size--;
            internal.remove(sorted.get(i));
        }
        internal.updateRectangle(); // cascade upwards?
        for(Node<T> removedNode : removed){
            insertNode(removedNode);
        }
    }

    private void split(Node<T> node){
        Objects.requireNonNull(node);
        if(node.isLeaf()){
            splitLeaf(node);
            return;
        }
        splitInternal(node);
    }

    private void splitInternal(Node<T> internal){
        Objects.requireNonNull(internal);
        if(!internal.isInternal()){
            throw new IllegalArgumentException("Cannot split the internal Node, as it is a leaf.");
        }
        List<Node<T>> sortedThroughBestAxis = chooseSplitAxisInternal(internal.getChildren());
        int index = chooseSplitIndexInternal(sortedThroughBestAxis);
        if(index < 0 || index > sortedThroughBestAxis.size()){
            throw new IllegalStateException("Root split index is invalid.");
        }
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

        Node<T> parent;
        if(internal.isRoot()){
            parent = new Node<>();
            this.root = parent;
            this.depth++;
        }else{
            parent = internal.getParent();
        }

        parent.addChild(child1);
        child1.setParent(parent);
        parent.addChild(child2);
        child2.setParent(parent);

        child1.updateRectangle();
        child2.updateRectangle();
        parent.updateRectangle();
    }

    private void splitLeaf(Node<T> leaf){
        Objects.requireNonNull(leaf);
        if(!leaf.isLeaf()){
            throw new IllegalArgumentException("Cannot split the given node as it is not a leaf Node.");
        }
        List<T> sortedThroughBestAxis = chooseSplitAxisLeaf(leaf.getObjects());
        int index = chooseSplitIndexLeaf(sortedThroughBestAxis);
        if(index <= 0 || index >= sortedThroughBestAxis.size()){
            throw new IllegalStateException("Root split index is invalid.");
        }
        List<T> first = sortedThroughBestAxis.subList(0, index);
        List<T> second = sortedThroughBestAxis.subList(index, sortedThroughBestAxis.size());

        Node<T> child1 = new Node<>(first);
        Node<T> child2 = new Node<>(second);

        Node<T> parent;
        if(leaf.isRoot()){
            parent = new Node<>();
            this.root = parent;
            this.depth++;
        }else{
            parent = leaf.getParent();
            parent.remove(leaf);
        }

        parent.addChild(child1);
        child1.setParent(parent);
        parent.addChild(child2);
        child2.setParent(parent);

        child1.updateRectangle();
        child2.updateRectangle();
        parent.updateRectangle();
    }


    /**
     * Attempts all possible splits, and picks the most optimal one. Returns this split index.
     * Note that the split index is the index of the FIRST element to be in the SECOND group.
     * --
     * More specifically, attempts all possible split indices, and picks the split with the least area overlap.
     * (and smallest area, if area overlap is tied)
     */
    private int chooseSplitIndexLeaf(List<T> objects){
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

    private int chooseSplitIndexInternal(List<Node<T>> objects){
        Objects.requireNonNull(objects);
        if(objects.contains(null)){
            throw new NullPointerException();
        }
        List<Node<T>> firstGroup = new ArrayList<>();
        List<Node<T>> secondGroup = new ArrayList<>();
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
            Rectangle r1 = Rectangle.createMinimumBounding(firstGroup.stream().map(Node::getRectangle).toList());
            Rectangle r2 = Rectangle.createMinimumBounding(secondGroup.stream().map(Node::getRectangle).toList());
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
    private List<T> chooseSplitAxisLeaf(List<T> objects){
        Objects.requireNonNull(objects);
        if(objects.contains(null)){
            throw new NullPointerException();
        }
        Comparator<T> xComparator = Comparator.comparing((T o) -> o.getRectangle().x())
                                    .thenComparing((T o) -> o.getRectangle().x() + o.getRectangle().width());
        List<T> x_axis_sort = new ArrayList<>(objects);
        x_axis_sort.sort(xComparator);

        Comparator<T> yComparator = Comparator.comparing((T o) -> o.getRectangle().y())
                                    .thenComparing((T o) -> o.getRectangle().y() + o.getRectangle().height());
        List<T> y_axis_sort = new ArrayList<>(objects);
        y_axis_sort.sort(yComparator);
        int horizontalMarginSum = calculateMarginSum(x_axis_sort);
        int verticalMarginSum = calculateMarginSum(y_axis_sort);
        if(horizontalMarginSum < verticalMarginSum){
            return x_axis_sort;
        }else{
            return y_axis_sort;
        }
    }

    private List<Node<T>> chooseSplitAxisInternal(List<Node<T>> nodes){
        Objects.requireNonNull(nodes);
        if(nodes.contains(null)){
            throw new NullPointerException();
        }
        Comparator<Node<T>> xComp = Comparator.comparing((Node<T> n) -> n.getRectangle().x())
                .thenComparing((Node<T> n) -> n.getRectangle().x() + n.getRectangle().width());
        List<Node<T>> sortedAlongX = new ArrayList<>(nodes);
        sortedAlongX.sort(xComp);

        Comparator<Node<T>> yComp = Comparator.comparing((Node<T> n) -> n.getRectangle().y())
                .thenComparing((Node<T> n) -> n.getRectangle().y() + n.getRectangle().height());
        List<Node<T>> sortedAlongY = new ArrayList<>(nodes);
        sortedAlongY.sort(yComp);

        int horizontalMarginSum = calculateMarginSumInternal(sortedAlongX);
        int verticalMarginSum = calculateMarginSumInternal(sortedAlongY);
        if(horizontalMarginSum <= verticalMarginSum){
            return sortedAlongX;
        }
        return sortedAlongY;
    }

    private int calculateMarginSumInternal(List<Node<T>> objects){
        int marginSum = 0;
        List<Node<T>> firstGroup = new ArrayList<>();
        List<Node<T>> secondGroup = new ArrayList<>();
        for(int k = 1; k < (max - 2 * min + 2); k++){
            int firstGroupAmount = min - 1 + k;
            for(int i = 0; i < objects.size(); i++){
                if(i < firstGroupAmount){
                    firstGroup.add(objects.get(i));
                }else{
                    secondGroup.add(objects.get(i));
                }
            }
            Rectangle r1 = Rectangle.createMinimumBounding(firstGroup.stream().map(Node::getRectangle).toList());
            Rectangle r2 = Rectangle.createMinimumBounding(secondGroup.stream().map(Node::getRectangle).toList());
            marginSum += r1.perimeter();
            marginSum += r2.perimeter();
            firstGroup.clear();
            secondGroup.clear();
        }
        return marginSum;
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
