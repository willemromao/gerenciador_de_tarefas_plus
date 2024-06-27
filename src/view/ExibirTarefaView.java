package view;

import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

import java.util.List;
import java.util.Scanner;

public class ExibirTarefaView extends TarefaView {

    public ExibirTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("===== EXIBIR TAREFA =====");
            System.out.println("1. Exibir todas as tarefas");
            System.out.println("2. Exibir tarefas por tipo");
            System.out.println("0. Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    exibirTodasTarefas();
                    break;
                case 2:
                    exibirTarefasPorTipo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private void exibirTodasTarefas() {
        try {
            List<TarefaHabito> habitos = tarefaService.listarHabitos();
            List<TarefaDiaria> diarias = tarefaService.listarDiarias();
            List<TarefaAfazer> afazeres = tarefaService.listarAfazeres();

            exibirPorTipo(habitos, "HÁBITOS");
            exibirPorTipo(diarias, "DIÁRIAS");
            exibirPorTipo(afazeres, "AFAZERES");
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao exibir tarefas: " + e.getMessage());
        }
    }

    private void exibirTarefasPorTipo() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("===== EXIBIR TAREFA =====");
            System.out.println("1. Hábitos");
            System.out.println("2. Diárias");
            System.out.println("3. Afazeres");
            System.out.println("0. Voltar ao menu anterior");

            System.out.print("Escolha o tipo de tarefa: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        exibirPorTipo(tarefaService.listarHabitos(), "HÁBITOS");
                        break;
                    case 2:
                        exibirPorTipo(tarefaService.listarDiarias(), "DIÁRIAS");
                        break;
                    case 3:
                        exibirPorTipo(tarefaService.listarAfazeres(), "AFAZERES");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (ServiceOperationException e) {
                System.out.println("Erro ao exibir tarefas por tipo: " + e.getMessage());
            }
        }
    }

    private void exibirPorTipo(List<?> tarefas, String titulo) {
        System.out.println("===== " + titulo + " =====");
        tarefas.forEach(System.out::println);
        System.out.println();
    }
}
