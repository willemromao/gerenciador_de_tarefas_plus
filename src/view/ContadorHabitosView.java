package view;

import entity.Tarefa;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

public class ContadorHabitosView extends TarefaView {

    public ContadorHabitosView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.print("Número da Tarefa a ser incrementada/decrementada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Tarefa tarefa = tarefaService.buscarPorId(id);
            if (tarefa == null) {
                System.out.println("Tarefa não encontrada.");
                return;
            }

            if (tarefa instanceof TarefaHabito habito) {
                System.out.println("Escolha a operação:");
                System.out.println("1. Incrementar positivo");
                System.out.println("2. Incrementar negativo");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        tarefaService.incrementarPositivo(habito);
                        System.out.println("Contagem positiva incrementada para o hábito.");
                        break;
                    case 2:
                        tarefaService.incrementarNegativo(habito);
                        System.out.println("Contagem negativa incrementada para o hábito.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } else {
                System.out.println("Essa tarefa não é um hábito e não pode ter sua contagem incrementada/decrementada.");
            }

        } catch (ServiceOperationException e) {
            System.out.println("Erro ao realizar operação com tarefa de hábito: " + e.getMessage());
        }
    }
}
