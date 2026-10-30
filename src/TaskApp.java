public class TaskApp {
    public static void main(String[] args) {
        if (args.length == 0) {
            TaskManager.ViewHelp();
            return;
        }

        TaskManager manager = new TaskManager();
        String comand = args[0];

        try {
            switch (comand) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Uso: add <descricao>");
                        return;
                    }
                    manager.add(args[1]);
                    break;
                case "update":
                    if (args.length < 3) {
                        System.out.println("Funcionou o update");
                        break;
                    }
                case "delete":
                    if (args.length < 2) {
                        System.out.println("Funcionou o delete");
                        break;
                    }
                case "list":
                    manager.listAll();
                    break;

            }
        } catch (
                NumberFormatException e) {
            System.out.println("Erro: O ID informado deve ser um número válido.");
        }

    }
}