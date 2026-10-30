
public class Task {

    int id;
    String description;
    Status status;
    String createdAt;
    String updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.TODO;
    }

    public Task(int id, String description, Status status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task fromJson(String json) {

        json = json.replace("{", "").replace("}", "").replace("\"", "");

        String[] fields = json.split(",");

        int id = Integer.parseInt(fields[0].split(":")[1].trim());
        String description = fields[1].split(":")[1].trim();
        Status status = Status.fromString(fields[2].split(":")[1].trim());

        String createdAt = fields[3].substring(fields[3].indexOf(":") + 1).trim();
        String updateAt = fields[4].substring(fields[4].indexOf(":") + 1).trim();

        return new Task(id, description, status, createdAt, updateAt);

    }

    public String toJson() {
        return "{\"id\":\"" + id + "\", \"description\":\"" + description +
                "\", \"status\":\"" + status.getValor() +
                "\", \"createdAt\":\"" + createdAt +
                "\", \"updatedAt\":\"" + updatedAt + "\"}";
    }

    @Override
    public String toString() {
        return "--- Tarefa [ID: " + id + "] ---" +
                "\n Data de Inicialização: " + createdAt +
                "\n Descrição: " + description +
                "\n Status: " + status +
                "\n Última Atualização: " + updatedAt + "\n";
    }

}
