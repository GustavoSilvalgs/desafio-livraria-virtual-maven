import javax.persistence.Entity;

@Entity
public class Eletronico extends Livro {

    private int tamanho;

    public Eletronico(){
    }

    public Eletronico(String titulo, String autores, String editora, float preco, int tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "\n #Livro Eletrônico#" +
                "\nId: " + super.getId() +
                "\nTitulo: " + super.getTitulo() +
                "\nAutores: " + super.getAutores() +
                "\nEditora: " + super.getEditora() +
                "\nPreco: " + super.getPreco() +
                "\nTamanho: " + this.tamanho + "KB";

    }
}

