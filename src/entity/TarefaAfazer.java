package entity;

import java.time.LocalDate;

public class TarefaAfazer extends Tarefa {
    private LocalDate dataConclusao;

    public TarefaAfazer(int id, String nome, LocalDate dataConclusao) {
        super(id, nome);
        this.dataConclusao = dataConclusao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

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
