public class EntityFactory {
    public static Entity createEntity(String entityType, int id, String... attributes) {
        switch (entityType) {
            case "Cliente":
                return new Cliente(id, attributes[0], attributes[1]); // name, phoneNumber
            case "Livro":
                return new Livro(id, attributes[0], attributes[1]);   // name, category
            case "LivroEmprestado":
                return new LivroEmprestado(id, attributes[0], attributes[1], attributes[2]); // clienteName, livroName, returnDate
            default:
                throw new IllegalArgumentException("Invalid entity type");
        }
    }
}