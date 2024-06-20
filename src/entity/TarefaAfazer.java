package entity;

import java.time.LocalDate;

public class TarefaAfazer extends Tarefa {
    private LocalDate dataConclusao;

    public TarefaAfazer(String nome, LocalDate dataConclusao) {
        super(nome);
        this.dataConclusao = dataConclusao;
    }

    // getters and setters

    @Override
    public void concluir() {
        this.dataConclusao = LocalDate.now();
    }

    @Override
    public String toString() {
        return "TarefaAfazer{" +
                "dataConclusao=" + dataConclusao +
                ", id=" + getId() +
                ", nome='" + getNome() + '\'' +
                '}';
    }
}
