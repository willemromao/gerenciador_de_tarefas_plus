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
            throw new ServiceOperationException("Erro ao incrementar a contagem positiva para tarefa de hábito: " + e.getMessage(), e);
        }
    }

    public void incrementarNegativo(TarefaHabito habito) throws ServiceOperationException {
        try {
            habito.incrementarNegativo();
            habitoDAO.update(habito.getId(), habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao incrementar a contagem negativa para tarefa de hábito: " + e.getMessage(), e);
        }
    }

    public void concluir(Tarefa tarefa) throws ServiceOperationException {
        try {
            tarefa.concluir();
            getDAO(tarefa).update(tarefa.getId(), tarefa);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao concluir a tarefa: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaHabito habito) throws ServiceOperationException {
        try {
            habitoDAO.save(habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao adicionar tarefa de hábito: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaDiaria diaria) throws ServiceOperationException {
        try {
            diariaDAO.save(diaria);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao adicionar tarefa Diária: " + e.getMessage(), e);
        }
    }

    public void adicionar(TarefaAfazer afazer) throws ServiceOperationException {
        try {
            afazerDAO.save(afazer);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao adicionar tarefa Afazer: " + e.getMessage(), e);
        }
    }

    public void editarTarefaHabito(int id, String novoNome) throws ServiceOperationException {
        try {
            TarefaHabito habito = buscarHabitoPorId(id);
            if (habito == null) {
                throw new ServiceOperationException("Tarefa Hábito não encontrada com ID: " + id);
            }
            habito.setNome(novoNome);
            habitoDAO.update(id, habito);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao editar Tarefa Hábito: " + e.getMessage(), e);
        }
    }

    public void editarTarefaDiaria(int id, String novoNome, Prioridade novaPrioridade) throws ServiceOperationException {
        try {
            TarefaDiaria diaria = buscarDiariaPorId(id);
            if (diaria == null) {
                throw new ServiceOperationException("Tarefa Diária não encontrada com ID: " + id);
            }
            diaria.setNome(novoNome);
            diaria.setPrioridade(novaPrioridade);
            diariaDAO.update(id, diaria);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao editar a Tarefa Diária: " + e.getMessage(), e);
        }
    }

    public void editarTarefaAfazer(int id, String novoNome, LocalDate novaDataConclusao) throws ServiceOperationException {
        try {
            TarefaAfazer afazer = buscarAfazerPorId(id);
            if (afazer == null) {
                throw new ServiceOperationException("Tarefa Afazer não encontrada com ID: " + id);
            }
            afazer.setNome(novoNome);
            afazer.setDataConclusao(novaDataConclusao);
            afazerDAO.update(id, afazer);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao editar Tarefa Afazer: " + e.getMessage(), e);
        }
    }

    public TarefaHabito buscarHabitoPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaHabito> habito = habitoDAO.findById(id);
            return habito.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao encontrar Tarefa Hábito por ID: " + e.getMessage(), e);
        }
    }

    public TarefaDiaria buscarDiariaPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaDiaria> diaria = diariaDAO.findById(id);
            return diaria.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao encontrar Tarefa Diária por ID: " + e.getMessage(), e);
        }
    }

    public TarefaAfazer buscarAfazerPorId(int id) throws ServiceOperationException {
        try {
            Optional<TarefaAfazer> afazer = afazerDAO.findById(id);
            return afazer.orElse(null);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao localizar Tarefa Afazer por ID: " + e.getMessage(), e);
        }
    }

    public void remover(Tarefa tarefa) throws ServiceOperationException {
        try {
            getDAO(tarefa).delete(tarefa.getId());
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao remover tarefa: " + e.getMessage(), e);
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
            throw new ServiceOperationException("Tipo de tarefa não suportada: " + tarefa.getClass().getSimpleName());
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
            throw new ServiceOperationException("Tarefa não encontrada com ID: " + id);
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao localizar tarefa por ID: " + e.getMessage(), e);
        }
    }

    public List<TarefaHabito> listarHabitos() throws ServiceOperationException {
        try {
            return habitoDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao listar Hábitos: " + e.getMessage(), e);
        }
    }

    public List<TarefaDiaria> listarDiarias() throws ServiceOperationException {
        try {
            return diariaDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao listar Diárias: " + e.getMessage(), e);
        }
    }

    public List<TarefaAfazer> listarAfazeres() throws ServiceOperationException {
        try {
            return afazerDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceOperationException("Erro ao lista Afazeres: " + e.getMessage(), e);
        }
    }

}
