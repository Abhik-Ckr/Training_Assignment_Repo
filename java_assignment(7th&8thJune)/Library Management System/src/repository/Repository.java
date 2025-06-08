package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T> {
    Map<String, T> data = new HashMap<>();

    public void add(String id, T item){
        data.put(id, item);
    }

    public T get(String id){
        return data.get(id);
    }

    public void remove(String id){
        data.remove(id);
    }
    public List<T> getAll(){
        return new ArrayList<>(data.values());
    }
    public boolean contains(String id){
        return data.containsKey(id);
    }
    public void clear() {
        data.clear();
    }

}
