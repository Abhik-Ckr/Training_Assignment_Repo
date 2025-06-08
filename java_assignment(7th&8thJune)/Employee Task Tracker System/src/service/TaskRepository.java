package service;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository <T> {
    final List<T> taskList = new ArrayList<>();

    public void add(T task){
        taskList.add(task);
    }
    public void remove(T task){
        taskList.remove(task);
    }
    public List<T> getAll() {
        return new ArrayList<>(taskList);
    }
    public T findByIndex(int index){
        if(index >= 0 && index < taskList.size()){
            return taskList.get(index);
        }
        return null;
    }
}
