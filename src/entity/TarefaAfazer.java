package entity;

import java.time.LocalDate;

public class TarefaAfazer extends Tarefa {
    private LocalDate data;

    public TarefaAfazer(int id, String nome, LocalDate data) {
        super(id, nome);
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TarefaAfazer{id=" + getId() + ", nome='" + getNome() + "', data=" + data + "}";
    }
}
