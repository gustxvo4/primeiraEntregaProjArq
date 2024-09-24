import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroEmprestado extends Entity {
    private static final String FILE_NAME = "livros_emprestados.txt";
    private String nomeCliente;
    private String nomeLivro;
    private String dataDevolucao;

    public LivroEmprestado(int id, String nomeCliente, String nomeLivro, String dataDevolucao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeLivro = nomeLivro;
        this.dataDevolucao = dataDevolucao;
    }

    // id auto-incrementado com verificação da existência do arquivo
    private static int getNextId() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return 1; // se o arquivo não existe, começa o id em 1
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

    public static void registrarLivroEmprestado(String nomeCliente, String nomeLivro, String dataDevolucao) throws IOException {
        int id = getNextId();  // id auto-incrementado
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(id + "," + nomeCliente + "," + nomeLivro + "," + dataDevolucao + "\n");
        writer.close();
    }

    public static void listarTodosLivrosEmprestados() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
        }
        reader.close();
    }

    public static void listarLivroEmprestadoPorId(int id) throws IOException {
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

    public static void editarLivroEmprestado(int id, String novoNomeCliente, String novoNomeLivro, String novaDataDevolucao) throws IOException {
        List<String> emprestados = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (Integer.parseInt(dados[0]) == id) {
                emprestados.add(id + "," + novoNomeCliente + "," + novoNomeLivro + "," + novaDataDevolucao);
            } else {
                emprestados.add(linha);
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String emprestado : emprestados) {
            writer.write(emprestado + "\n");
        }
        writer.close();
    }
}