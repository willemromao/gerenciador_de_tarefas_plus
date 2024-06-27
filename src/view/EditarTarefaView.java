package view;

import service.TarefaService;
import entity.*;
import exception.ServiceOperationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditarTarefaView extends TarefaView {

    public EditarTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.println("===== EDITAR TAREFA =====");
        System.out.println("1. Hábito");
        System.out.println("2. Diária");
        System.out.println("3. Afazer");
        System.out.print("Escolha o tipo de tarefa a ser editada: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Tarefa a ser editada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            String novoNome;
            switch (tipo) {
                case 1:
                    System.out.print("Novo Nome da Tarefa: ");
                    novoNome = scanner.nextLine();
                    tarefaService.editarTarefaHabito(id, novoNome);
                    break;
                case 2:
                    System.out.print("Novo Nome da Tarefa: ");
                    novoNome = scanner.nextLine();
                    System.out.print("Nova Prioridade (BAIXA, MEDIA, ALTA): ");
                    String novaPrioridadeInput = scanner.nextLine().toUpperCase();
                    Prioridade novaPrioridade;
                    try {
                        novaPrioridade = Prioridade.valueOf(novaPrioridadeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Prioridade inválida. Usando prioridade atual.");
                        novaPrioridade = Prioridade.MEDIA;
                    }
                    tarefaService.editarTarefaDiaria(id, novoNome, novaPrioridade);
                    break;
                case 3:
                    System.out.print("Novo Nome da Tarefa: ");
                    novoNome = scanner.nextLine();
                    LocalDate novaDataConclusao = null;
                    boolean dataValida = false;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    while (!dataValida) {
                        System.out.print("Nova Data de conclusão esperada (DD-MM-YYYY): ");
                        String novaDataInput = scanner.nextLine();
                        try {
                            novaDataConclusao = LocalDate.parse(novaDataInput, formatter);
                            dataValida = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida. Por favor, insira uma data no formato DD-MM-YYYY.");
                        }
                    }
                    tarefaService.editarTarefaAfazer(id, novoNome, novaDataConclusao);
                    break;
                default:
                    System.out.println("Tipo de tarefa inválido.");
                    return;
            }
            System.out.println("Tarefa editada com sucesso!");
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao editar tarefa: " + e.getMessage());
        }
    }
}
