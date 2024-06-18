package entity;

public class TarefaDiaria extends Tarefa {
    private Prioridade prioridade;

    public TarefaDiaria(int id, String nome, Prioridade prioridade) {
        super(id, nome);
        this.prioridade = prioridade;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "TarefaDiaria{id=" + getId() + ", nome='" + getNome() + "', prioridade=" + prioridade + "}";
    }
}
