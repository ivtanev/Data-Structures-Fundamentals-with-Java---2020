package core;

import interfaces.Entity;
import interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class Data implements Repository {
    Queue<Entity> data;
    private Entity root;

    public Data(){
        this.data = new PriorityQueue<>();
        this.root = null;
    }

    public Data(Data other){
        this.root = other.root;
        this.data = new PriorityQueue<>(other.data);
    }

    @Override
    public void add(Entity entity) {
        if(this.root == null){
            this.root = entity;
        }else {
            this.getById(entity.getParentId()).addChild(entity);
        }
        this.data.offer(entity);
    }

    @Override
    public Entity getById(int id) {
        if(this.root == null){
            return null;
        }
        Deque<Entity> queue = new ArrayDeque<>();
        queue.offer(this.root);
        while (!queue.isEmpty()){
            Entity current = queue.poll();
            if(current.getId() == id){
                return current;
            }
            for (Entity child : current.getChildren()) {
                queue.offer(child);
            }
        }
        return null;
    }

    @Override
    public List<Entity> getByParentId(int id) {
        Entity parent = this.getById(id);
        return parent == null ? new ArrayList<>() : parent.getChildren();
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.data);
    }

    @Override
    public Repository copy() {
        return new Data(this);
    }

    @Override
    public List<Entity> getAllByType(String type) {
        if(!type.equals("User") && !type.equals("Invoice") && !type.equals("StoreClient")){
            throw new IllegalArgumentException("Illegal type: " + type);
        }
        return this.data.stream()
                .filter(e->e.getClass().getSimpleName().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public Entity peekMostRecent() {
        if(this.size() == 0){
            throw new IllegalStateException("Operation...");
        }
        return this.data.peek();
    }

    @Override
    public Entity pollMostRecent() {
        if(this.size() == 0){
            throw new IllegalStateException("Operation...");
        }
        Entity polledElement = this.data.poll();
        this.getById(polledElement.getParentId()).getChildren().remove(polledElement);
        return polledElement;
    }

    @Override
    public int size() {
        return this.data.size();
    }
}
