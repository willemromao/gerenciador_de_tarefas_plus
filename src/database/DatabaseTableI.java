package database;

import entity.Entity;
import entity.Tarefa;
import exception.EntityNotFoundException;

import java.util.List;
import java.util.function.Predicate;

public interface DatabaseTableI<T extends Entity> {
    void save(T entity);
    T findById(int id) throws EntityNotFoundException;
    void update(int id, T entity) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;

    List<T> findAll(Predicate<Tarefa> filter);
}
