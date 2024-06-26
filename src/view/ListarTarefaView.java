package view;

import java.util.List;
import entity.TarefaAfazer;
import entity.TarefaDiaria;
import entity.TarefaHabito;
import exception.ServiceOperationException;
import service.TarefaService;

public class ListarTarefaView extends TarefaView {

    public ListarTarefaView(TarefaService tarefaService) {
        super(tarefaService);
    }

    @Override
    public void execute() {
        try {
            List<TarefaHabito> habitos = tarefaService.listarHabitos();
            List<TarefaDiaria> diarias = tarefaService.listarDiarias();
            List<TarefaAfazer> afazeres = tarefaService.listarAfazeres();

            System.out.println("===== HÁBITOS =====");
            habitos.forEach(System.out::println);
            System.out.println();

            System.out.println("===== DIÁRIAS =====");
            diarias.forEach(System.out::println);
            System.out.println();

            System.out.println("===== AFAZERES =====");
            afazeres.forEach(System.out::println);
            System.out.println();
        } catch (ServiceOperationException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }
}
