package view;

import entity.Prioridade;
import entity.Tarefa;
import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

import java.time.LocalDate;
import java.util.Scanner;

public class AdicionarTarefaView extends TarefaView {

    public AdicionarTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        System.out.println("===== Adicionar Tarefa =====");
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
                    TarefaHabito habito = new TarefaHabito(nome); // Criando com o construtor de nome
                    tarefaService.adicionar(habito);
                    break;
                case 2:
                    System.out.print("Escolha a prioridade (BAIXA, MEDIA, ALTA): ");
                    String prioridadeInput = scanner.nextLine().toUpperCase();

                    Prioridade prioridade;
                    try {
                        prioridade = Prioridade.valueOf(prioridadeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Prioridade inválida. Usando prioridade padrão: BAIXA.");
                        prioridade = Prioridade.BAIXA;
                    }

                    TarefaDiaria diaria = new TarefaDiaria(nome, prioridade); // Criando com o construtor de nome e prioridade
                    tarefaService.adicionar(diaria);
                    break;
                case 3:
                    System.out.print("Data de conclusão esperada (YYYY-MM-DD): ");
                    String dataConclusao = scanner.nextLine();
                    TarefaAfazer afazer = new TarefaAfazer(nome, LocalDate.parse(dataConclusao)); // Criando com o construtor de nome e data
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
