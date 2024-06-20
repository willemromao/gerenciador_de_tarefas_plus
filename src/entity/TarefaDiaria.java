package entity;

public class TarefaDiaria extends Tarefa {
    private Prioridade prioridade;

    public TarefaDiaria(String nome, Prioridade prioridade) {
        super(nome);
        this.prioridade = prioridade;
    }

    // getters and setters

    @Override
    public String toString() {
        return "TarefaDiaria{id=" + getId() + ", nome='" + getNome() + "', prioridade=" + prioridade + "}";
    }
}
