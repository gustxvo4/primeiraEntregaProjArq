import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Entity {
    private static final String FILE_NAME = "clientes.txt";
    private String nome;
    private String telefone;

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    // id auto-incrementado com verificação da existência do arquivo
    private static int getNextId() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // se o arquivo não existe, retorna o primeiro id (1)
            return 1;
        }

        int maxId = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            int idAtual = Integer.parseInt(dados[0]);
            if (idAtual > maxId) {
                maxId = idAtual;
            }
        }
        reader.close();
        return maxId + 1;
    }

    public static void registrarCliente(String nome, String telefone) throws IOException {
        int id = getNextId();  // id auto-incrementado
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(id + "," + nome + "," + telefone + "\n");
        writer.close();
    }

    public static void listarTodosClientes() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
        }
        reader.close();
    }

    public static void listarClientePorId(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (Integer.parseInt(dados[0]) == id) {
                System.out.println(linha);
                break;
            }
        }
        reader.close();
    }

    public static void editarCliente(int id, String novoNome, String novoTelefone) throws IOException {
        List<String> clientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (Integer.parseInt(dados[0]) == id) {
                clientes.add(id + "," + novoNome + "," + novoTelefone);
            } else {
                clientes.add(linha);
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String cliente : clientes) {
            writer.write(cliente + "\n");
        }
        writer.close();
    }
}
