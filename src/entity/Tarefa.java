package entity;

public abstract class Tarefa extends Entity {
    private String nome;

    public Tarefa(int id, String nome) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tarefa{id=" + getId() + ", nome='" + nome + "'}";
    }
}
