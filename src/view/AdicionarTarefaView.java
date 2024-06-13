package view;

import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

import java.time.LocalDate;

public class AdicionarTarefaView extends TarefaView {

    public AdicionarTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.println("### Adicionar Tarefa ###");
        System.out.println("1. Habito");
        System.out.println("2. Diaria");
        System.out.println("3. Afazer");
        System.out.print("Escolha o tipo de tarefa: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.print("Nome da Tarefa: ");
        String nome = scanner.nextLine();

        try {
            switch (tipo) {
                case 1:
                    TarefaHabito habito = new TarefaHabito(0, nome, 0, 0);
                    tarefaService.adicionar(habito);
                    break;
                case 2:
                    System.out.print("Data (YYYY-MM-DD): ");
                    String data = scanner.nextLine();
                    TarefaDiaria diaria = new TarefaDiaria(0, nome, LocalDate.parse(data));
                    tarefaService.adicionar(diaria);
                    break;
                case 3:
                    System.out.print("Data de conclusão esperada (YYYY-MM-DD): ");
                    String dataConclusao = scanner.nextLine();
                    TarefaAfazer afazer = new TarefaAfazer(0, nome, LocalDate.parse(dataConclusao));
                    tarefaService.adicionar(afazer);
                    break;
                default:
                    System.out.println("Tipo de tarefa inválido.");
            }
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }
}
