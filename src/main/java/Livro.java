import javax.persistence.*;

@Entity(name = "livros")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String autores;
    private String editora;
    private float preco;

    public Livro() {
    }

    public Livro(String titulo, String autores, String editora, float preco) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "\n#Livro#\n" +
                "\nid: " + id +
                "\ntitulo: " + titulo +
                "\nautores: " + autores +
                "\neditora: " + editora +
                "\npreco: " + preco;
    }
}