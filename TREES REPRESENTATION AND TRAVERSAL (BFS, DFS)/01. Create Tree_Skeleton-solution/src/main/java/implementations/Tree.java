package implementations;

import interfaces.AbstractTree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
//        List<E> result = new ArrayList<>();
//        if(this.key == null){
//            return result;
//        }
//        result.add(this.key);
//        for (Tree<E> child : children) {
//            result.add(child.key);
//        }
//
//        for (Tree<E> child : children) {
//            if (child.children.size() != 0) {
//                for (Tree<E> eTree : child.children) {
//                    result.add(eTree.key);
//                }
//            }
//        }
        List<E> result = new ArrayList<>();
        if(this.key == null){
            return result;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()){
            Tree<E> current = queue.poll();
            result.add(current.key);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> order = new ArrayList<>();
        this.dfs(this, order);
        return order;
    }

    private void dfs(Tree<E> tree, List<E> order) {
        //TODO return List<E> or do with stack
        for (Tree<E> child : tree.children) {
            this.dfs(child, order);
        }
        order.add(tree.key);
    }


    @Override
    public void addChild(E parentKey, Tree<E> child) {
        //Tree<E> search = findRecursive(this,parentKey);
        Tree<E> search = findBfs(parentKey);
        if (search == null) {
            throw new IllegalArgumentException();
        }

        search.children.add(child);
        child.parent = search;
    }

    private Tree<E> findBfs(E parentKey) {
        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();

        childrenQueue.offer(this);
        while (childrenQueue.size() > 0) {
            Tree<E> current = childrenQueue.poll();

            if (current.key.equals(parentKey)) {
                return current;
            }

            for (Tree<E> child : current.children) {
                childrenQueue.offer(child);
            }
        }

        return null;
    }

    private Tree<E> findRecursive(Tree<E> current, E parentKey) {
        if (current.key.equals(parentKey)) {
            return current;
        }

        for (Tree<E> child : current.children) {
            Tree<E> found = this.findRecursive(child, parentKey);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = findBfs(nodeKey);

        if (toRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : toRemove.children) {
            child.parent = null;
        }
        toRemove.children.clear();

        Tree<E> parent = toRemove.parent;
        if (parent != null) {
            parent.children.remove(toRemove);
        }
        toRemove.key = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findBfs(firstKey);
        Tree<E> secondNode = findBfs(secondKey);

        if(firstNode == null || secondNode == null){
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if(firstParent == null){
            swapRoot(secondNode);
            secondNode.parent = null;
            return;
        }else if(secondParent == null){
            swapRoot(firstNode);
            firstNode.parent = null;
            return;
        }
        firstNode.parent = secondParent;
        secondNode.parent = firstParent;

        int firstIndex = firstParent.children.indexOf(firstNode);
        int secondIndex = secondParent.children.indexOf(secondNode);

        firstParent.children.set(firstIndex,secondNode);
        secondParent.children.set(secondIndex,firstNode);
    }

    private void swapRoot(Tree<E> node) {
        this.key = node.key;
        this.children = node.children;
        this.parent = null;
        node.parent = null;
    }
}



