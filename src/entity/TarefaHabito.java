package entity;

public class TarefaHabito extends Tarefa {
    private int contadorPositivo;
    private int contadorNegativo;

    public TarefaHabito(String nome) {
        super(nome);
        this.contadorPositivo = 0;
        this.contadorNegativo = 0;
    }

    public void incrementarPositivo() {
        this.contadorPositivo++;
    }

    public void incrementarNegativo() {
        this.contadorNegativo++;
    }

    @Override
    public String toString() {
        return "Tarefa Hábito (" + getId() + "),  " + getNome() + ", Contador +" + contadorPositivo +
                " -" + contadorNegativo;
    }
}
