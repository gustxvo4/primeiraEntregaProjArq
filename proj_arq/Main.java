import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        LibraryController controller = new LibraryController(scanner);

        while (true) {
            System.out.println("Escolha uma entidade:");
            System.out.println("1. Cliente");
            System.out.println("2. Livro");
            System.out.println("3. Livro Emprestado");
            System.out.println("4. Sair");

            int escolhaEntidade = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (escolhaEntidade == 4) {
                System.out.println("Saindo...");
                break;
            }

            String tipoEntidade;
            if (escolhaEntidade == 1) {
                tipoEntidade = "Cliente";
            } else if (escolhaEntidade == 2) {
                tipoEntidade = "Livro";
            } else if (escolhaEntidade == 3) {
                tipoEntidade = "LivroEmprestado";
            } else {
                System.out.println("Escolha inválida!");
                continue;
            }

            controller.gerenciarEntidade(tipoEntidade);
        }
        scanner.close();
    }
}