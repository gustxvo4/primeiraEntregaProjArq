import java.io.IOException;
import java.util.Scanner;

public class LibraryController {
    private Scanner scanner;

    public LibraryController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void gerenciarEntidade(String tipoEntidade) throws IOException {
        System.out.println("Escolha uma operação:");
        System.out.println("1. Listar Todos");
        System.out.println("2. Listar por ID");
        System.out.println("3. Editar");
        System.out.println("4. Registrar");

        int escolhaOperacao = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (escolhaOperacao) {
            case 1:
                listarTodasEntidades(tipoEntidade);
                break;
            case 2:
                listarEntidadePorId(tipoEntidade);
                break;
            case 3:
                editarEntidade(tipoEntidade);
                break;
            case 4:
                registrarEntidade(tipoEntidade);
                break;
            default:
                System.out.println("Escolha inválida!");
        }
    }

    private void listarTodasEntidades(String tipoEntidade) throws IOException {
        if (tipoEntidade.equals("Cliente")) {
            Cliente.listarTodosClientes();
        } else if (tipoEntidade.equals("Livro")) {
            Livro.listarTodosLivros();
        } else if (tipoEntidade.equals("LivroEmprestado")) {
            LivroEmprestado.listarTodosLivrosEmprestados();
        }
    }

    private void listarEntidadePorId(String tipoEntidade) throws IOException {
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        if (tipoEntidade.equals("Cliente")) {
            Cliente.listarClientePorId(id);
        } else if (tipoEntidade.equals("Livro")) {
            Livro.listarLivroPorId(id);
        } else if (tipoEntidade.equals("LivroEmprestado")) {
            LivroEmprestado.listarLivroEmprestadoPorId(id);
        }
    }

    private void editarEntidade(String tipoEntidade) throws IOException {
        System.out.println("Digite o ID para editar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        if (tipoEntidade.equals("Cliente")) {
            System.out.println("Digite o novo nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite o novo telefone:");
            String telefone = scanner.nextLine();
            Cliente.editarCliente(id, nome, telefone);
        } else if (tipoEntidade.equals("Livro")) {
            System.out.println("Digite o novo nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite a nova categoria:");
            String categoria = scanner.nextLine();
            Livro.editarLivro(id, nome, categoria);
        } else if (tipoEntidade.equals("LivroEmprestado")) {
            System.out.println("Digite o novo nome do cliente:");
            String nomeCliente = scanner.nextLine();
            System.out.println("Digite o novo nome do livro:");
            String nomeLivro = scanner.nextLine();
            System.out.println("Digite a nova data de devolução:");
            String dataDevolucao = scanner.nextLine();
            LivroEmprestado.editarLivroEmprestado(id, nomeCliente, nomeLivro, dataDevolucao);
        }
    }

    private void registrarEntidade(String tipoEntidade) throws IOException {
        if (tipoEntidade.equals("Cliente")) {
            System.out.println("Digite o nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite o telefone:");
            String telefone = scanner.nextLine();
            Cliente.registrarCliente(nome, telefone);  // ID gerado automaticamente
        } else if (tipoEntidade.equals("Livro")) {
            System.out.println("Digite o nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite a categoria:");
            String categoria = scanner.nextLine();
            Livro.registrarLivro(nome, categoria);  // ID gerado automaticamente
        } else if (tipoEntidade.equals("LivroEmprestado")) {
            System.out.println("Digite o nome do cliente:");
            String nomeCliente = scanner.nextLine();
            System.out.println("Digite o nome do livro:");
            String nomeLivro = scanner.nextLine();
            System.out.println("Digite a data de devolução:");
            String dataDevolucao = scanner.nextLine();
            LivroEmprestado.registrarLivroEmprestado(nomeCliente, nomeLivro, dataDevolucao);  // ID gerado automaticamente
        }
    }
}
