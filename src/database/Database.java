package database;

import entity.Entity;
import exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Database {
    private static Database instance;
    private final Map<Class<?>, DatabaseTable<?>> tables = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private Database() {
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private <T extends Entity> DatabaseTable<T> getTable(Class<T> entityType) {
        return (DatabaseTable<T>) tables.computeIfAbsent(entityType, k -> new DatabaseTable<>());
    }

    public <T extends Entity> void save(Class<T> entityType, T entity) {
        if (entity.getId() == 0) {
            entity.setId(nextId.getAndIncrement());
        }
        getTable(entityType).save(entity);
    }

    public <T extends Entity> T findById(Class<T> entityType, int id) throws EntityNotFoundException {
        return getTable(entityType).findById(id);
    }

    public <T extends Entity> void update(Class<T> entityType, int id, T entity) throws EntityNotFoundException {
        getTable(entityType).update(id, entity);
    }

    public <T extends Entity> void delete(Class<T> entityType, int id) throws EntityNotFoundException {
        getTable(entityType).delete(id);
    }

    public <T extends Entity> List<T> findAll(Class<T> entityType, Predicate<T> filter) {
        return getTable(entityType).findAll(filter);
    }
}
