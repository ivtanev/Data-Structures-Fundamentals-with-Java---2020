package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        traverseTreeWithRecurrence(builder, 0, this);

        return builder.toString().trim();
    }

    private void traverseTreeWithRecurrence(StringBuilder builder, int indent, Tree<E> tree) {
        builder.append(this.getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(builder, indent + 2, child);
        }
    }

    private void traverseTreeWithRecurrence(List<Tree<E>> collection, Tree<E> tree) {

        collection.add(tree);

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(collection, child);
        }
    }

    private String getPadding(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        return traverseWithBFS()
                .stream()
                .filter(tree -> tree.children.size() == 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    private List<Tree<E>> traverseWithBFS() {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        List<Tree<E>> allNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            allNodes.add(tree);
            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return allNodes;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<Tree<E>> allNodes = new ArrayList<>();
        this.traverseTreeWithRecurrence(allNodes, this);
        return allNodes
                .stream()
                .filter(tree -> tree.getParent() != null && tree.children.size() > 0)
                .map(Tree::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isMiddleKeys(Tree<E> element) {

        return element.getParent() != null;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> trees = this.traverseWithBFS();


        int maxPath = 0;
        Tree<E> deepestLeftMostNode = null;

        for (Tree<E> tree : trees) {
            if (tree.isLeaf()) {
                int currentPath = getStepsFromLeafToRoot(tree);
                if (currentPath > maxPath) {
                    maxPath = currentPath;
                    deepestLeftMostNode = tree;
                }
            }
        }
        return deepestLeftMostNode;
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    private int getStepsFromLeafToRoot(Tree<E> tree) {
        Tree<E> current = tree;
        int counter = 0;
        while (current.parent != null) {
            counter++;
            current = current.parent;
        }
        return counter;
    }


    @Override
    public List<E> getLongestPath() {
        Tree<E> tree = getDeepestLeftmostNode();
        List<E> longestPath = new ArrayList<>();
        longestPath.add(tree.key);
        while (tree.parent != null) {
            tree = tree.parent;
            longestPath.add(tree.key);
        }
        Collections.reverse(longestPath);
        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        List<List<E>> result = new ArrayList<>();
        int currentSum = 0;
        List<Tree<E>> allNodes = new ArrayList<>();
        List<E> elementsInGivenPath = new ArrayList<>();
        this.traverseTreeWithRecurrence(allNodes, this);
        for (Tree<E> currentNode : allNodes) {
            if (currentNode.isLeaf()) {

                getGivenPath(elementsInGivenPath, currentNode);

                currentSum = getCurrentSum(sum, currentSum, elementsInGivenPath);

                if (currentSum == sum) {
                    Collections.reverse(elementsInGivenPath);
                    Collections.addAll(result, elementsInGivenPath);
                }
                currentSum = 0;
                elementsInGivenPath = new ArrayList<>();

            }
        }

        return result;
    }

    private void getGivenPath(List<E> elementsInGivenPath, Tree<E> currentNode) {
        while (currentNode != null) {
            elementsInGivenPath.add(currentNode.key);
            currentNode = currentNode.parent;
        }
    }

    private int getCurrentSum(int sum, int currentSum, List<E> elementsInGivenPath) {
        for (E element : elementsInGivenPath) {
            currentSum += element.hashCode();

            if (currentSum > sum) {
                break;
            }
        }
        return currentSum;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> allNodes = new ArrayList<>();
        int currentSum = sum;
        List<Tree<E>> result = new ArrayList<>();


        this.traverseTreeWithRecurrence(allNodes, this);
        int[] temp = new int[1];
        temp[0] = sum;
        for (Tree<E> node : allNodes) {
            if (node.isMiddleKeys(node)) {
                findSubTreeSum(node, temp);
                currentSum = temp[0];
                if (currentSum == 0) {
                    Collections.addAll(result,node);
                    break;
                }
                temp[0] = sum;
            }
        }


        return result;
    }

    private void findSubTreeSum(Tree<E> tree, int[] sum) {
        sum[0] -= tree.key.hashCode();
        for (Tree<E> child : tree.children) {
            findSubTreeSum(child, sum);
            //sum -= child.key.hashCode();
        }
        //return sum;
    }

}



