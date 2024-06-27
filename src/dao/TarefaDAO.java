package dao;

import database.Database;
import database.DatabaseTable;
import entity.Tarefa;
import exception.DAOException;
import exception.EntityNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TarefaDAO<T extends Tarefa> implements DAO<T> {
    private final DatabaseTable<T> table;

    public TarefaDAO(DatabaseTable<T> table) {
        this.table = table;
    }

    public TarefaDAO(Class<T> entityType) {
        this.table = Database.getInstance().getTable(entityType);
    }

    @Override
    public void save(Tarefa entity) throws DAOException {
        table.save((T) entity);
    }

    @Override
    public Optional<T> findById(int id) throws DAOException {
        try {
            return Optional.ofNullable(table.findById(id));
        } catch (EntityNotFoundException e) {
            throw new DAOException("Error finding entity by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() throws DAOException {
        return table.findAll(t -> true);
    }

    @Override
    public List<T> findAll(Predicate<T> filter) throws DAOException {
        return table.findAll(filter);
    }

    @Override
    public List<T> findAll(Comparator<T> comparator) throws DAOException {
        return table.findAll(t -> true).stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void update(int id, Tarefa entity) throws DAOException {
        table.update(id, (T) entity);
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            table.delete(id);
        } catch (EntityNotFoundException e) {
            throw new DAOException("Error deleting entity: " + e.getMessage(), e);
        }
    }
}
