package view;

import service.TarefaService;
import dao.TarefaDAO;
import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;

import java.util.Scanner;

public class MainView {
    private final TarefaService tarefaService;

    public MainView() {
        TarefaDAO<TarefaHabito> tarefaHabitoDAO = new TarefaDAO<>(TarefaHabito.class);
        TarefaDAO<TarefaDiaria> tarefaDiariaDAO = new TarefaDAO<>(TarefaDiaria.class);
        TarefaDAO<TarefaAfazer> tarefaAfazerDAO = new TarefaDAO<>(TarefaAfazer.class);
        this.tarefaService = new TarefaService(tarefaHabitoDAO, tarefaDiariaDAO, tarefaAfazerDAO);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = -1;
            do {
                System.out.println("===== GERENCIADOR DE TAREFAS PLUS ======");
                System.out.println("1. Exibir");
                System.out.println("2. Adicionar");
                System.out.println("3. Editar");
                System.out.println("4. Remover");
                System.out.println("5. Contador de Hábitos");
                System.out.println("6. Sair do sistema \n");
                System.out.print("Escolha uma opção: ");

                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Entrada inválida. Tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                TarefaView view;
                switch (opcao) {
                    case 1:
                        view = new ExibirTarefaView(tarefaService);
                        break;
                    case 2:
                        view = new AdicionarTarefaView(tarefaService);
                        break;
                    case 3:
                        view = new EditarTarefaView(tarefaService);
                        break;
                    case 4:
                        view = new RemoverTarefaView(tarefaService);
                        break;
                    case 5:
                        view = new ContadorHabitosView(tarefaService);
                        break;
                    case 6:
                        System.out.println("Saindo do sistema...");
                        continue;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        continue;
                }
                view.startView();
            } while (opcao != 6);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
