package entity;

public class TarefaDiaria extends Tarefa {
    private Prioridade prioridade;

    public TarefaDiaria(String nome, Prioridade prioridade) {
        super(nome);
        this.prioridade = prioridade;
    }

    // getters and setters

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Tarefa Diária (" + getId() + "), " + getNome() + ", Prioridade: " + prioridade;
    }
}
