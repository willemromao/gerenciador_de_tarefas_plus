package view;

import service.TarefaService;
import dao.TarefaDAO;
import database.DatabaseTable;
import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;
import java.util.Scanner;

public class MainView {
    private final TarefaService tarefaService;

    public MainView() {
        TarefaDAO<TarefaHabito> tarefaHabitoDAO = new TarefaDAO<>(new DatabaseTable<>());
        TarefaDAO<TarefaDiaria> tarefaDiariaDAO = new TarefaDAO<>(new DatabaseTable<>());
        TarefaDAO<TarefaAfazer> tarefaAfazerDAO = new TarefaDAO<>(new DatabaseTable<>());
        this.tarefaService = new TarefaService(tarefaHabitoDAO, tarefaDiariaDAO, tarefaAfazerDAO);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("### Gerenciar Tarefas ###");
            System.out.println("1. Listar Tarefas");
            System.out.println("2. Adicionar Tarefa");
            System.out.println("3. Editar Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

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
                    System.out.println("Voltando ao Menu Principal...");
                    continue;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }
            view.execute();
        } while (opcao != 5);
        scanner.close();
    }
}
