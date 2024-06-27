package view;

import database.Database;
import service.TarefaService;
import dao.TarefaDAO;
import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;

import java.util.Scanner;

public class MainView {
    private final TarefaService tarefaService;

    public MainView() {
        Database database = Database.getInstance();
        TarefaDAO<TarefaHabito> tarefaHabitoDAO = new TarefaDAO<>(database.getTable(TarefaHabito.class));
        TarefaDAO<TarefaDiaria> tarefaDiariaDAO = new TarefaDAO<>(database.getTable(TarefaDiaria.class));
        TarefaDAO<TarefaAfazer> tarefaAfazerDAO = new TarefaDAO<>(database.getTable(TarefaAfazer.class));
        this.tarefaService = new TarefaService(tarefaHabitoDAO, tarefaDiariaDAO, tarefaAfazerDAO);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("===== GERENCIADOR DE TAREFAS PLUS ======");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("5. Concluir uma Tarefa");
            System.out.println("6. Sair do sistema \n");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            TarefaView view;
            switch (opcao) {
                case 1:
                    view = new ListarTarefaView(tarefaService);
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
                    view = new ConcluirTarefaView(tarefaService);
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
        scanner.close();
    }
}
