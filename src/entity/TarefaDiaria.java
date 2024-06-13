package entity;

import java.time.LocalDate;

public class TarefaDiaria extends Tarefa {
    private LocalDate prioridade;

    public TarefaDiaria(int id, String nome, LocalDate prioridade) {
        super(id, nome);
        this.prioridade = prioridade;
    }

    public LocalDate getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(LocalDate prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "TarefaDiaria{id=" + getId() + ", nome='" + getNome() + "', prioridade=" + prioridade + "}";
    }
}
