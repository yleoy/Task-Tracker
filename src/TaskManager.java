import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> lista = new ArrayList<>();
    private final String FILE_JSON = "file/tasks.json";

    public TaskManager() {
        loadTasks();
    }

    private Task searchForId(int id) {
        for (Task t : lista) {
            if (t.id == id) return t;
        }
        return null;
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

    public void add(String description) {
        int biggerId = 0;
        for (Task task : lista) {
            if (task.id > biggerId) {
                biggerId = task.id;
            }
        }
        int newId = biggerId + 1;

        Task newTask = new Task(newId, description);
        lista.add(newTask);
        saveTasks();
        System.out.println("Tarefa adicionada com sucesso! ID: " + newTask.id);
    }

    public void update(int id, String newDescription ) {
        Task task = searchForId(id);
        if (task != null) {
            task.description = newDescription;
            task.updatedAt = LocalDateTime.now().toString();
            saveTasks();
            System.out.println("Tarefa atualizada com sucesso! (ID: " + task.id + ")");
        } else {
            System.out.println("Erro ao atualizar Tarefa: ");
        }
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

    public void markInProgress(int id) {
        Task task = searchForId(id);
        if (task != null) {
            task.status = Status.IN_PROGRES;
            task.updatedAt = LocalDateTime.now().toString();
            saveTasks();
            System.out.println("Tarefa marcada como *Em progresso* (ID: " + task.id + ")");
        } else {
            System.out.println("Nenhuma tarefa encontrada.");
        }
    }

    public void markDone(int id) {
        Task task = searchForId(id);
        if (task != null) {
            task.status = Status.DONE;
            task.updatedAt = LocalDateTime.now().toString();
            saveTasks();
            System.out.println("Tarefa marcado como Concluida (ID: " + task.id + ")");
        } else  {
            System.out.println("Nenhuma tarefa encontrada.");
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
