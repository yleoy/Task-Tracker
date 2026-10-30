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
                        System.out.println("Uso: update <id> <nova-descricao>");
                        return;
                    }
                    manager.update(Integer.parseInt(args[1]), args[2]);
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("Uso: delete <id>");
                        return;
                    }
                    manager.delete(Integer.parseInt(args[1]));
                    break;

                case "list":
                    manager.listAll();
                    break;

                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Uso: mark-in-progress <id>");
                        return;
                    }
                    manager.markInProgress(Integer.parseInt(args[1]));
                    break;
                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Uso: mark-done <id>");
                        return;
                    }
                    manager.markDone(Integer.parseInt(args[1]));
                    break;

            }
        } catch (
                NumberFormatException e) {
            System.out.println("Erro: O ID informado deve ser um número válido.");
        }

    }
}