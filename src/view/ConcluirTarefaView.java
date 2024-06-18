package view;

import entity.Tarefa;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

public class ConcluirTarefaView extends TarefaView {

    public ConcluirTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.print("ID da Tarefa a ser concluída: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            if (tarefa == null) {
                System.out.println("Tarefa não encontrada.");
                return;
            }

            if (tarefa instanceof TarefaHabito) {
                TarefaHabito habito = (TarefaHabito) tarefa;
                tarefaService.incrementarPositivo(habito);
            } else {
                tarefaService.concluir(tarefa);
            }

            System.out.println("Tarefa concluída com sucesso.");
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao concluir tarefa: " + e.getMessage());
        }
    }
}
