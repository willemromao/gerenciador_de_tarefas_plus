package view;

import service.TarefaService;
import entity.*;
import exception.ServiceOperationException;

public class RemoverTarefaView extends TarefaView {

    public RemoverTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.println("===== REMOVER TAREFA =====");
        System.out.println("1. Hábito");
        System.out.println("2. Diária");
        System.out.println("3. Afazer");
        System.out.print("Escolha o tipo de tarefa: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Tarefa a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Tarefa tarefa;
            switch (tipo) {
                case 1:
                    tarefa = tarefaService.buscarHabitoPorId(id);
                    break;
                case 2:
                    tarefa = tarefaService.buscarDiariaPorId(id);
                    break;
                case 3:
                    tarefa = tarefaService.buscarAfazerPorId(id);
                    break;
                default:
                    System.out.println("Tipo de tarefa inválido.");
                    return;
            }

            if (tarefa == null) {
                System.out.println("Tarefa não encontrada.");
                return;
            }

            tarefaService.remover(tarefa);
            System.out.println("Tarefa removida com sucesso.");
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao remover tarefa: " + e.getMessage());
        }
    }
}
