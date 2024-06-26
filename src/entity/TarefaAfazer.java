package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TarefaAfazer extends Tarefa {
    private LocalDate dataConclusao;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TarefaAfazer(String nome, LocalDate dataConclusao) {
        super(nome);
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
        return "Tarefa Afazer (" + getId() +
                "),  " + getNome() +
                ", Data de conclusão: " + dataConclusao.format(formatter);
    }
}
