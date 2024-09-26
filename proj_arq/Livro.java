import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Livro extends Entity {
    private static final String FILE_NAME = "livros.txt";
    private String nome;
    private String categoria;

    public Livro(int id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
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

    public static void registrarLivro(String nome, String categoria) throws IOException {
        int id = getNextId();  // id auto-incrementado
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(id + "," + nome + "," + categoria + "\n");
        writer.close();
    }

    public static void listarTodosLivros() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
        }
        reader.close();
    }

    public static void listarLivroPorId(int id) throws IOException {
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

    public static void editarLivro(int id, String novoNome, String novaCategoria) throws IOException {
        List<String> livros = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            if (Integer.parseInt(dados[0]) == id) {
                livros.add(id + "," + novoNome + "," + novaCategoria);
            } else {
                livros.add(linha);
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String livro : livros) {
            writer.write(livro + "\n");
        }
        writer.close();
    }
}