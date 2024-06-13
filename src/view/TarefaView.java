package view;

import java.util.Scanner;
import service.TarefaService;

public abstract class TarefaView {
    protected final Scanner scanner;
    protected final TarefaService tarefaService;

    public TarefaView(TarefaService tarefaService) {
        this.scanner = new Scanner(System.in);
        this.tarefaService = tarefaService;
    }

    public abstract void execute();
}
