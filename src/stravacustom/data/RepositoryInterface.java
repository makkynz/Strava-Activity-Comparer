package stravacustom.data;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<T> {
    public List<T> getAll();
    public T getById(UUID id);
    public void add(T obj);
    public void update(T obj);
    public void removeById(UUID id);
    public  void remove(T obj) ;
}
