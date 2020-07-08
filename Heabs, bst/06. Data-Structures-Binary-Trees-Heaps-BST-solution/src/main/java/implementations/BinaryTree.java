package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;

    public BinaryTree(E key, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.setKey(key);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightChild;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();

        result.append(getPadding(indent)).append(this.getKey());

        if (this.leftChild != null) {
            String element = this.getLeft().asIndentedPreOrder(indent + 2);
            result.append(System.lineSeparator()).append(element);
        }

        if (this.rightChild != null) {
            String element = this.getRight().asIndentedPreOrder(indent + 2);
            result.append(System.lineSeparator()).append(element);
        }

        return result.toString();
    }

    private String getPadding(int indent) {
        StringBuilder paddingResult = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            paddingResult.append(" ");
        }
        return paddingResult.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if(this.getLeft() != null){
            result.addAll(this.getLeft().preOrder());
        }
        if(this.getRight() != null){
            result.addAll(this.getRight().preOrder());
        }


        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if(this.getLeft() != null){
            result.addAll(this.getLeft().inOrder());
        }
        result.add(this);
        if(this.getRight() != null){
            result.addAll(this.getRight().inOrder());
        }


        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if(this.getLeft() != null){
            result.addAll(this.getLeft().postOrder());
        }
        if(this.getRight() != null){
            result.addAll(this.getRight().postOrder());
        }
        result.add(this);


        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if(this.getLeft() != null){
            this.getLeft().forEachInOrder(consumer);
        }

        consumer.accept(this.getKey());

        if(this.getRight() != null){
            this.getRight().forEachInOrder(consumer);
        }
    }
}
