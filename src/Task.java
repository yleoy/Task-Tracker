import java.time.LocalDateTime;

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

    public String toJson() {
        return "  {\n" +
                "    \"id\": " + id + ",\n" +
                "    \"description\": \"" + description + "\",\n" +
                "    \"status\": \"" + status.getValor() + "\",\n" +
                "    \"createdAt\": \"" + createdAt + "\",\n" +
                "    \"updatedAt\": \"" + updatedAt + "\"\n" +
                "  }";
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
