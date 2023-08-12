import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private static int numVendas;
    private int numero;
    private String cliente;
    private float valor;

    @OneToMany
    private List<Livro> livros = new ArrayList<>();

    public Venda() {
    }

    public Venda(int numero, String cliente, float valor, List<Livro> livros) {
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
        this.livros = livros;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public void addLivro (Livro l, int index){
        livros.add(index, l);
        valor += l.getPreco();
    }

    public void listarLivros() {
        System.out.println("Livros da venda " + numero + ":");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + ". " + livros.get(i).getTitulo());
        }
    }
}

