import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numero;
    private String cliente;
    private float valor;

    @OneToMany
    private List<Livro> livros = new ArrayList<>();

    public Venda() {
    }

    public Venda(String cliente, List<Livro> livros) {
        this.cliente = cliente;
        this.livros = livros;

        var soma = 0.0f;
        for (int i = 0; i < livros.size(); i++) {
            soma += livros.get(i).getPreco();
            if (livros.get(i) instanceof Impresso){
                soma += ((Impresso) livros.get(i)).getFrete();
            }
        }
        this.valor = soma;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void addLivro (Livro livro, int index){
        this.livros.add(index, livro);
        this.valor += livro.getPreco();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void listarLivros() {
        System.out.println("Livros da venda " + id + ":");
        for (var livro : this.livros) { System.out.println(livro.toString()); }
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id: " + id +
                ", Cliente: '" + cliente + '\'' +
                ", Valor: " + valor +
                ", Livros: " + livros +
                '}';
    }
}
