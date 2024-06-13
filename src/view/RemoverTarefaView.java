package view;

import service.TarefaService;
import java.util.Scanner;
import entity.Tarefa;
import exception.ServiceOperationException;

public class RemoverTarefaView extends TarefaView {

    public RemoverTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.print("ID da Tarefa a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            if (tarefa == null) {
                System.out.println("Tarefa não encontrada.");
                return;
            }

            tarefaService.remover(tarefa);
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao remover tarefa: " + e.getMessage());
        }
    }
}
