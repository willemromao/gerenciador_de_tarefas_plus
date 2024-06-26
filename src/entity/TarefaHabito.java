package entity;

public class TarefaHabito extends Tarefa {
    private int contadorPositivo;
    private int contadorNegativo;

    public TarefaHabito(String nome) {
        super(nome);
        this.contadorPositivo = 0;
        this.contadorNegativo = 0;
    }

    public TarefaHabito(String nome, int contadorPositivo, int contadorNegativo) {
        super(nome);
        this.contadorPositivo = contadorPositivo;
        this.contadorNegativo = contadorNegativo;
    }

    public int getContadorPositivo() {
        return contadorPositivo;
    }

    public void setContadorPositivo(int contadorPositivo) {
        this.contadorPositivo = contadorPositivo;
    }

    public int getContadorNegativo() {
        return contadorNegativo;
    }

    public void setContadorNegativo(int contadorNegativo) {
        this.contadorNegativo = contadorNegativo;
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
