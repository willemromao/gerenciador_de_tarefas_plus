package entity;

public class TarefaHabito extends Tarefa {
    private int contadorPositivo;
    private int contadorNegativo;

    public TarefaHabito(int id, String nome, int contadorPositivo, int contadorNegativo) {
        super(id, nome);
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

    @Override
    public String toString() {
        return "TarefaHabito{id=" + getId() + ", nome='" + getNome() + "', contadorPositivo=" + contadorPositivo + ", contadorNegativo=" + contadorNegativo + "}";
    }
}
