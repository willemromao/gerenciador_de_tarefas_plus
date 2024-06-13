package view;

import service.TarefaService;
import entity.Tarefa;
import exception.ServiceOperationException;

public class EditarTarefaView extends TarefaView {

    public EditarTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.print("ID da Tarefa a ser editada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            if (tarefa == null) {
                System.out.println("Tarefa não encontrada.");
                return;
            }

            System.out.print("Novo Nome da Tarefa: ");
            String novoNome = scanner.nextLine();
            tarefa.setNome(novoNome);

            tarefaService.editar(tarefa);
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao editar tarefa: " + e.getMessage());
        }
    }
}
