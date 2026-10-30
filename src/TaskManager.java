import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> lista = new ArrayList<>();
    private final String FILE_JSON = "file/tasks.json";

    public TaskManager() {
        loadTasks();
    }

    public void add(String description) {
        int biggerId = 0;
        for (Task task : lista) {
            if (task.id < biggerId) {
                biggerId = task.id;
            }
        }
        int newId = biggerId + 1;

        Task newTask = new Task(newId, description);
        lista.add(newTask);
        saveTasks();
        System.out.println("Tarefa adicionada com sucesso! ID: " + newTask.id);
    }

    public void listAll() {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
            return;
        }
        for (Task task : lista) {
            System.out.println(task);
        }
    }


    private void loadTasks() {
        File file = new File(FILE_JSON);
        if (!file.exists()) {
            return;
        }

        try {

            String content = Files.readString(file.toPath()).trim();

            if (content.length() <= 2) {
                return;
            }

            String[] taskList = content.replace("[", "").replace("]", "").split("},");

            for (String taskJson : taskList) {
                taskJson = taskJson.trim();
                if (taskJson.isEmpty()) {
                    continue;
                }

                if (!taskJson.endsWith("}")) {
                    taskJson = taskJson + "}";
                }

                lista.add(Task.fromJson(taskJson));


            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar Tarefas" + e.getMessage());
        }
    }

    private void saveTasks() {
        File file = new File(FILE_JSON);

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (FileWriter editor = new FileWriter(file)) {
            editor.write("[\n");
            for (int i = 0; i < lista.size(); i++) {
                editor.write(lista.get(i).toJson());
                if (i != lista.size() - 1) {
                    editor.write(",\n");
                } else {
                    editor.write("]\n");
                }
            }
            editor.write("]\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }


    public static void ViewHelp() {
        System.out.println("\n--- Task Tracker CLI ---");
        System.out.println("Comandos disponíveis:");
        System.out.println("  add <descricao>                  - Adiciona uma nova tarefa");
        System.out.println("  update <id> <nova-descricao>     - Atualiza a descrição de uma tarefa");
        System.out.println("  delete <id>                      - Remove uma tarefa");
        System.out.println("  mark-in-progress <id>            - Marca a tarefa como em andamento");
        System.out.println("  mark-done <id>                   - Marca a tarefa como concluída");
        System.out.println("  list                             - Lista todas as tarefas");
        System.out.println("  list [todo|in-progress|done]     - Lista filtrando por status\n");
    }

}
