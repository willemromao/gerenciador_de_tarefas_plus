package dao;

import database.DatabaseTableI;
import entity.Tarefa;
import exception.DAOException;
import exception.EntityNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TarefaDAO implements DAO<Tarefa> {
    private final DatabaseTableI<Tarefa> table;

    public TarefaDAO(DatabaseTableI<Tarefa> table) {
        this.table = table;
    }

    @Override
    public void save(Tarefa entity) throws DAOException {
        table.save(entity);
    }

    @Override
    public Optional<Tarefa> findById(int id) throws DAOException {
        try {
            return Optional.ofNullable(table.findById(id));
        } catch (EntityNotFoundException e) {
            throw new DAOException("Error finding entity by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Tarefa> findAll() throws DAOException {
        Predicate<Tarefa> filter = null;
        return table.findAll(filter);
    }

    @Override
    public List<Tarefa> findAll(Predicate<Tarefa> filter) throws DAOException {
        return table.findAll(filter);
    }

    @Override
    public List<Tarefa> findAll(Comparator<Tarefa> comparator) throws DAOException {
        return table.findAll((Predicate<Tarefa>) comparator);
    }

    @Override
    public void update(int id, Tarefa entity) throws DAOException {
        try {
            table.update(id, entity);
        } catch (EntityNotFoundException e) {
            throw new DAOException("Error updating entity: " + e.getMessage(), e);
        }
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