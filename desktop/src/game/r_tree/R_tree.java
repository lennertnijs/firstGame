package game.r_tree;

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

    public void insert(T t){
        insertData(Objects.requireNonNull(t, "Cannot insert null."), this.depth);
    }


    private void insertData(T t, int depth){
        Node<T> leafNode = chooseSubTree(t.getRectangle(), depth);
        leafNode.addObject(t);
        this.size++;
        if(leafNode.getObjects().size() <= max){
            return;
        }
        if(overflowTreatment(leafNode, depth--) != Action.SPLIT){
            return;
        }
        this.overflowDepth = -1;
        while(!leafNode.isRoot() && leafNode.getParent().getChildren().size() > max){
            leafNode = leafNode.getParent();
            overflowTreatment(leafNode, depth--);
            this.overflowDepth = -1;
        }
    }

    private void insertNode(Node<T> node, int depth){
        Node<T> internal = chooseSubTree(node.getRectangle(), depth); // picks a full subtree AGAIN
        internal.addChild(node);
        if(internal.getChildren().size() <= max){
            return;
        }
        if(overflowTreatment(internal, depth--) != Action.SPLIT){
            return;
        }
        this.overflowDepth = -1;
        while(!internal.isRoot() && internal.getParent().getChildren().size() > max){
            internal = internal.getParent();
            overflowTreatment(internal, depth--);
            this.overflowDepth = -1;
        }
    }

    public Action overflowTreatment(Node<T> node, int depth){
        if(!node.isRoot() && overflowDepth != depth){
            this.overflowDepth = depth;
            reInsert(node, depth);
            return Action.REINSERT;
        }else{
            this.overflowDepth = depth;
            split(node);
            return Action.SPLIT;
        }

    }

    private void reInsert(Node<T> node, int depth){
        if(node.isLeaf()){
            List<T> sorted = new ArrayList<>(node.getObjects());
            Comparator<T> comparator = Comparator.comparing(r -> r.getRectangle().center().distanceTo(node.getRectangle().center()));
            sorted.sort(comparator);
            int p = min + 1;
            List<T> removed = new ArrayList<>(p);
            for(int i = 0; i < p; i++){
                node.getObjects().remove(sorted.get(i));
                this.size--;
                removed.add(sorted.get(i));
            }
            node.updateRectangle();
            for(T removedObject : removed){
                insertData(removedObject, depth);
            }
        }else{
            List<Node<T>> sorted = new ArrayList<>(node.getChildren());
            Comparator<Node<T>> comparator = Comparator.comparing(r -> r.getRectangle().center().distanceTo(node.getRectangle().center()));
            sorted.sort(comparator);
            int p = min + 1;
            List<Node<T>> removed = new ArrayList<>(p);
            for(int i = 0; i < p; i++){
                node.remove(sorted.get(i));
                removed.add(sorted.get(i));
            }
            node.updateRectangle();
            for(Node<T> removedNode : removed){
                insertNode(removedNode, depth);
            }
        }
    }

    private void split(Node<T> node){
        Node<T> child1 = new Node<>();
        Node<T> child2 = new Node<>();
        if(node.isLeaf()){
            List<T> sortedAlongBestAxis = chooseSplitAxisData(node.getObjects());
            int splitIndex = chooseSplitIndex(sortedAlongBestAxis.stream().map(GameObject2D::getRectangle).toList());
            child1.addObjects(sortedAlongBestAxis.subList(0, splitIndex));
            child2.addObjects(sortedAlongBestAxis.subList(splitIndex, sortedAlongBestAxis.size()));
        }else{
            List<Node<T>> sortedAlongBestAxis = chooseSplitAxisNode(node.getChildren());
            int splitIndex = chooseSplitIndex(sortedAlongBestAxis.stream().map(Node::getRectangle).toList());
            child1.addChildren(sortedAlongBestAxis.subList(0, splitIndex));
            child2.addChildren(sortedAlongBestAxis.subList(splitIndex, sortedAlongBestAxis.size()));
        }
        Node<T> parent = node.getParent() != null ? node.getParent() : new Node<>();
        if(node.getParent() == null){
            this.root = parent;
            this.depth++;
        }else{
            parent.remove(node);
        }
        parent.addChild(child1);
        parent.addChild(child2);
    }

    /**
     * Attempts predefined possible splits, and picks the most optimal one. Returns the split index.
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
     * Calculates the total margin sum of predefined distributions of the list of SORTED rectangles and returns it.
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
        int horizontalMarginSum = calculateMarginSum(x_axis_sort.stream().map(GameObject2D::getRectangle).toList());
        int verticalMarginSum = calculateMarginSum(y_axis_sort.stream().map(GameObject2D::getRectangle).toList());
        if(horizontalMarginSum < verticalMarginSum){
            return x_axis_sort;
        }else{
            return y_axis_sort;
        }
    }

    private List<Node<T>> chooseSplitAxisNode(List<Node<T>> nodes){
        Comparator<Node<T>> xComp = Comparator.comparing((Node<T> n) -> n.getRectangle().x())
                .thenComparing((Node<T> n) -> n.getRectangle().x() + n.getRectangle().width());
        Comparator<Node<T>> yComp = Comparator.comparing((Node<T> n) -> n.getRectangle().y())
                .thenComparing((Node<T> n) -> n.getRectangle().y() + n.getRectangle().height());
        List<Node<T>> sortedAlongX = new ArrayList<>(nodes);
        sortedAlongX.sort(xComp);
        List<Node<T>> sortedAlongY = new ArrayList<>(nodes);
        sortedAlongY.sort(yComp);

        int horizontalMarginSum = calculateMarginSum(sortedAlongX.stream().map(Node::getRectangle).toList());
        int verticalMarginSum = calculateMarginSum(sortedAlongY.stream().map(Node::getRectangle).toList());
        if(horizontalMarginSum <= verticalMarginSum){
            return sortedAlongX;
        }
        return sortedAlongY;
    }

    private Node<T> chooseSubTree(Rectangle rectangle, int targetDepth){
        return chooseSubTree(root, rectangle, targetDepth, 1);
    }

    private Node<T> chooseSubTree(Node<T> current, Rectangle rectangle, int targetDepth, int depth){
        if(depth == targetDepth){
            return current;
        }
        for(int i = current.getChildren().size(); i < max; i++){
            Node<T> node = new Node<>();
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
