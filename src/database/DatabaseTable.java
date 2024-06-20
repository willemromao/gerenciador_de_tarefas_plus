package database;

import entity.Entity;
import exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
    private final Map<Integer, T> data = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void save(T entity) {
        int entityId = nextId.getAndIncrement();
        entity.setId(entityId);
        data.put(entityId, entity);
    }

    @Override
    public T findById(int id) throws EntityNotFoundException {
        T entity = data.get(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        return entity;
    }

    @Override
    public void update(int id, T entity) throws EntityNotFoundException {
        if (!data.containsKey(id)) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        entity.setId(id);
        data.put(id, entity);
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        if (data.remove(id) == null) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public List<T> findAll(Predicate<T> filter) {
        if (filter == null) {
            return new ArrayList<>(data.values());
        }
        return data.values().stream().filter(filter).collect(Collectors.toList());
    }
}
