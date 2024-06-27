package dao;

import database.Database;
import entity.Tarefa;
import exception.DAOException;
import exception.EntityNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TarefaDAO<T extends Tarefa> implements DAO<T> {
    private final Class<T> entityType;
    private final Database database;

    public TarefaDAO(Class<T> entityType) {
        this.entityType = entityType;
        this.database = Database.getInstance();
    }

    @Override
    public void save(T entity) throws DAOException {
        try {
            database.save(entityType, entity);
        } catch (Exception e) {
            throw new DAOException("Erro ao salvar entidade: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> findById(int id) throws DAOException {
        try {
            return Optional.ofNullable(database.findById(entityType, id));
        } catch (EntityNotFoundException e) {
            throw new DAOException("Erro ao localizar entidade por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() throws DAOException {
        try {
            return database.findAll(entityType, t -> true);
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar todas as entidades: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll(Predicate<T> filter) throws DAOException {
        try {
            return database.findAll(entityType, filter);
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar entidades com filtro: " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll(Comparator<T> comparator) throws DAOException {
        try {
            return database.findAll(entityType, t -> true).stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar entidades ordenadas: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, T entity) throws DAOException {
        try {
            database.update(entityType, id, entity);
        } catch (EntityNotFoundException e) {
            throw new DAOException("Erro ao atualizar entidade: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(entityType, id);
        } catch (EntityNotFoundException e) {
            throw new DAOException("Erro ao excluir entidade: " + e.getMessage(), e);
        }
    }
}
