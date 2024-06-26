package service;

import dao.DAO;
import entity.*;
import exception.DAOException;
import exception.ServiceOperationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TarefaService {
    private final DAO<TarefaHabito> habitoDAO;
    private final DAO<TarefaDiaria> diariaDAO;
    private final DAO<TarefaAfazer> afazerDAO;

    public TarefaService(DAO<TarefaHabito> habitoDAO, DAO<TarefaDiaria> diariaDAO, DAO<TarefaAfazer> afazerDAO) {
        this.habitoDAO = habitoDAO;
        this.diariaDAO = diariaDAO;
        this.afazerDAO = afazerDAO;
    }

    public void incrementarPositivo(TarefaHabito habito) throws ServiceOperationException {
        try {
            habito.incrementarPositivo();
            habitoDAO.update(habito.getId(), habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error incrementing positive count for habit task: " + e.getMessage(), e);
        }
    }

    public void incrementarNegativo(TarefaHabito habito) throws ServiceOperationException {
        try {
            habito.incrementarNegativo();
            habitoDAO.update(habito.getId(), habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error incrementing negative count for habit task: " + e.getMessage(), e);
        }
    }

    public void concluir(Tarefa tarefa) throws ServiceOperationException {
        try {
            tarefa.concluir();
            getDAO(tarefa).update(tarefa.getId(), tarefa);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error completing task: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaHabito habito) throws ServiceOperationException {
        try {
            habitoDAO.save(habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error adding habit task: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaDiaria diaria) throws ServiceOperationException {
        try {
            diariaDAO.save(diaria);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error adding daily task: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaAfazer afazer) throws ServiceOperationException {
        try {
            afazerDAO.save(afazer);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error adding todo task: " + e.getMessage(), e);
        }
    }

    public void editar(Tarefa tarefa) throws ServiceOperationException {
        try {
            getDAO(tarefa).update(tarefa.getId(), tarefa);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error editing task: " + e.getMessage(), e);
        }
    }

    public void editarTarefaHabito(int id, String novoNome) throws ServiceOperationException {
        try {
            TarefaHabito habito = buscarHabitoPorId(id);
            if (habito == null) {
                throw new ServiceOperationException("Habit task not found with ID: " + id);
            }
            habito.setNome(novoNome);
            habitoDAO.update(id, habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error editing habit task: " + e.getMessage(), e);
        }
    }

    public void editarTarefaDiaria(int id, String novoNome, Prioridade novaPrioridade) throws ServiceOperationException {
        try {
            TarefaDiaria diaria = buscarDiariaPorId(id);
            if (diaria == null) {
                throw new ServiceOperationException("Daily task not found with ID: " + id);
            }
            diaria.setNome(novoNome);
            diaria.setPrioridade(novaPrioridade);
            diariaDAO.update(id, diaria);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error editing daily task: " + e.getMessage(), e);
        }
    }

    public void editarTarefaAfazer(int id, String novoNome, LocalDate novaDataConclusao) throws ServiceOperationException {
        try {
            TarefaAfazer afazer = buscarAfazerPorId(id);
            if (afazer == null) {
                throw new ServiceOperationException("Todo task not found with ID: " + id);
            }
            afazer.setNome(novoNome);
            afazer.setDataConclusao(novaDataConclusao);
            afazerDAO.update(id, afazer);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error editing todo task: " + e.getMessage(), e);
        }
    }

    public void exibir(Tarefa tarefa) {
        System.out.println(tarefa);
    }

    public TarefaHabito buscarHabitoPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaHabito> habito = habitoDAO.findById(id);
            return habito.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error finding habit task by ID: " + e.getMessage(), e);
        }
    }

    public TarefaDiaria buscarDiariaPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaDiaria> diaria = diariaDAO.findById(id);
            return diaria.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error finding daily task by ID: " + e.getMessage(), e);
        }
    }

    public TarefaAfazer buscarAfazerPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaAfazer> afazer = afazerDAO.findById(id);
            return afazer.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error finding todo task by ID: " + e.getMessage(), e);
        }
    }

    public void remover(Tarefa tarefa) throws ServiceOperationException {
        try {
            getDAO(tarefa).delete(tarefa.getId());
        } catch (DAOException e) {
            throw new ServiceOperationException("Error removing task: " + e.getMessage(), e);
        }
    }

    private DAO<? extends Tarefa> getDAO(Tarefa tarefa) throws ServiceOperationException {
        if (tarefa instanceof TarefaHabito) {
            return habitoDAO;
        } else if (tarefa instanceof TarefaDiaria) {
            return diariaDAO;
        } else if (tarefa instanceof TarefaAfazer) {
            return afazerDAO;
        } else {
            throw new ServiceOperationException("Unsupported task type: " + tarefa.getClass().getSimpleName());
        }
    }

    public Tarefa buscarPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaHabito> habito = habitoDAO.findById(id);
            if (habito.isPresent()) {
                return habito.get();
            }
            Optional<TarefaDiaria> diaria = diariaDAO.findById(id);
            if (diaria.isPresent()) {
                return diaria.get();
            }
            Optional<TarefaAfazer> afazer = afazerDAO.findById(id);
            if (afazer.isPresent()) {
                return afazer.get();
            }
            throw new ServiceOperationException("Task not found with ID: " + id);
        } catch (DAOException e) {
            throw new ServiceOperationException("Error finding task by ID: " + e.getMessage(), e);
        }
    }

    public List<TarefaHabito> listarHabitos() throws ServiceOperationException {
        try {
            return habitoDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Error listing habits: " + e.getMessage(), e);
        }
    }

    public List<TarefaDiaria> listarDiarias() throws ServiceOperationException {
        try {
            return diariaDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Error listing dailies: " + e.getMessage(), e);
        }
    }

    public List<TarefaAfazer> listarAfazeres() throws ServiceOperationException {
        try {
            return afazerDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Error listing todos: " + e.getMessage(), e);
        }
    }

}
