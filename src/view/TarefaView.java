package view;

import java.util.Scanner;
import service.TarefaService;

public abstract class TarefaView implements View{
    protected final Scanner scanner;
    protected final TarefaService tarefaService;

    public TarefaView(TarefaService tarefaService) {
        this.scanner = new Scanner(System.in);
        this.tarefaService = tarefaService;
    }

    @Override
    public void startView() {
        execute();
    }

    public abstract void execute();
}
