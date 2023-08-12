import javax.persistence.Entity;

@Entity
public class Impresso extends Livro {

    private float frete;
    private int estoque;

    public Impresso() {
    }

    public Impresso(String titulo, String autores, String editora, float preco, float frete, int estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void atualizarEstoque() {
        estoque--;
    }

    @Override
    public String toString() {
        return "Livro Impresso {" +
                "Id: " + super.getId() +
                ", Titulo: '" + super.getTitulo() + '\'' +
                ", Autores: '" + super.getAutores() + '\'' +
                ", Editora: '" + super.getEditora() + '\'' +
                ", Preco: " + super.getPreco() + '\'' +
                ", Frete: " + this.frete + '\'' +
                ", Estoque: " + this.estoque+
                '}';
    }
}

