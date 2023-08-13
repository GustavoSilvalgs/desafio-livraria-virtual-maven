import javax.persistence.*;
import java.util.*;

public class LivrariaVirtual {

    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 10;
    private final int MAX_VENDAS = 50;
    private int numImpressos;
    private int numEletronicos;
    private int numVendas;
    private Scanner sc = new Scanner(System.in);

    @OneToMany
    private List<Impresso> impressos = new ArrayList<>();

    @OneToMany
    private List<Eletronico> eletronicos = new ArrayList<>();

    @OneToMany
    private List<Venda> vendas = new ArrayList<>();

    public LivrariaVirtual() {
        numImpressos = em.createQuery("SELECT li FROM Impresso li", Impresso.class).getResultList().size();
        numEletronicos = em.createQuery("SELECT li FROM Eletronico li", Eletronico.class).getResultList().size();
        numVendas = em.createQuery("SELECT li FROM vendas li", Venda.class).getResultList().size();
    }

    public int getNumImpressos() {
        return numImpressos;
    }

    public int getMAX_IMPRESSOS() {
        return MAX_IMPRESSOS;
    }

    public int getMAX_ELETRONICOS() {
        return MAX_ELETRONICOS;
    }

    public int getMAX_VENDAS() {
        return MAX_VENDAS;
    }

    public void setNumImpressos(int numImpressos) {
        this.numImpressos = numImpressos;
    }

    public int getNumEletronicos() {
        return numEletronicos;
    }

    public void setNumEletronicos(int numEletronicos) {
        this.numEletronicos = numEletronicos;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public List<Impresso> getImpressos() {
        return impressos;
    }

    public void setImpressos(List<Impresso> impressos) {
        this.impressos = impressos;
    }

    public List<Eletronico> getEletronicos() {
        return eletronicos;
    }

    public void setEletronicos(List<Eletronico> eletronicos) {
        this.eletronicos = eletronicos;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("livrariavirtual");
    EntityManager em = emf.createEntityManager();


    public void cadastrarLivro() {
        Locale.setDefault(Locale.US);

        System.out.println("Informe o tipo de livro que deseja cadastrar: ");
        System.out.println("1 - Impresso");
        System.out.println("2 - Eletrônico");
        System.out.println("3 - Ambos");
        int op = sc.nextInt();
        sc.nextLine();

        if (op < 1 && op > 3) {
            System.out.println("Escolha inválida.\n");
        }

        if (numImpressos + numEletronicos >= MAX_IMPRESSOS + MAX_ELETRONICOS) {
            System.out.println("Limite de livros atingido.\n");
            return;
        }

        if (op == 1 || op == 3) {
            if (numImpressos < MAX_IMPRESSOS) {
                System.out.print("Digite o título do livro: ");
                String titulo = sc.nextLine();
                System.out.print("Digite o autor(a) do livro: ");
                String autor = sc.nextLine();
                System.out.print("Digite o editor(a) do livro: ");
                String editor = sc.nextLine();
                System.out.print("Digite o preco do livro: ");
                float preco = Float.parseFloat(sc.next());
                System.out.print("Digite o valor do frete: ");
                float frete = Float.parseFloat(sc.next());
                System.out.print("Digite a quantidade em estoque: ");
                int estoque = sc.nextInt();
                sc.nextLine();

                Impresso impresso = new Impresso(titulo, autor, editor, preco, frete, estoque);
                em.getTransaction().begin();
                em.persist(impresso);
                em.getTransaction().commit();
                impressos.add(impresso);
                numImpressos++;
                System.out.println("Livro impresso (" + titulo + ") cadastrado com sucesso!\n");
            }
            else {
                System.out.println("Limite de livros impressos atingidos.\n");
            }
        }

        if (op == 2 || op == 3) {
            if (numEletronicos < MAX_ELETRONICOS) {
                System.out.print("Digite o título do livro: ");
                String titulo = sc.nextLine();
                System.out.print("Digite o autor(a) do livro: ");
                String autor = sc.nextLine();
                System.out.print("Digite o editor(a) do livro: ");
                String editor = sc.nextLine();
                System.out.print("Digite o preco do livro: ");
                float preco = Float.parseFloat(sc.next());
                System.out.print("Digite o tamanho(KB): ");
                int kb = sc.nextInt();
                sc.nextLine();

                Eletronico eletronico = new Eletronico(titulo, autor, editor, preco, kb);
                em.getTransaction().begin();
                em.persist(eletronico);
                em.getTransaction().commit();
                eletronicos.add(eletronico);
                numEletronicos++;

                System.out.println("Livro eletrônico (" + titulo + ") cadastrado com sucesso!\n");
            }
            else {
                System.out.println("Limite de livros eletrônicos atingidos.\n");
            }
        }
    }

    public void realizarVenda() {
        System.out.print("Digite o nome do cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Digite a quantidade de livros que deseja comprar: ");
        int quantidadeLivros = sc.nextInt();

        var livroSelecionados= new ArrayList<Livro>();
        int idLivroSelecionado;
        for (int i = 0; i < quantidadeLivros; i++) {
            System.out.println((i + 1) + "º Livro");
            System.out.println("Digite o tipo de livro: \n1 - Impresso\n2 - Eletrônico");
            int tipoLivro = sc.nextInt();
            if (tipoLivro == 1) {
                listarLivrosImpressos();
            }
            else if (tipoLivro == 2) {
                listarLivrosEletronicos();
            }
            System.out.print("Informe o id do livro escolhido: ");
            idLivroSelecionado = sc.nextInt();
            livroSelecionados.add(retornaLivroPorId(idLivroSelecionado));
        }
        var venda = new Venda(cliente,livroSelecionados);

        System.out.println(venda);
        System.out.println("Confirmar venda? s/n");
        var confirmarVenda = sc.next().toLowerCase();
        if (confirmarVenda.equals("s")){
            for (int i = 0; i < venda.getLivros().size(); i++) {
                if (venda.getLivros().get(i) instanceof Impresso){
                    ((Impresso) venda.getLivros().get(i)).setEstoque(
                            ((Impresso) venda.getLivros().get(i)).getEstoque() - 1
                    );
                }
            }
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        }
        else {
            System.out.println("Venda Cancelada");
        }
    }

    public void listarLivrosImpressos() {


        String hql  = "SELECT li FROM Impresso li";
        Query query = em.createQuery(hql, Impresso.class);
        impressos = query.getResultList();

        if (impressos.isEmpty()){
            System.out.println("Não há livros impressos ");
        }
        else {
            for (var impresso : impressos){
                System.out.println(impresso.toString());
            }
        }

    }

    public void listarLivrosEletronicos() {

        String hql  = "SELECT li FROM Eletronico li";
        Query query = em.createQuery(hql, Eletronico.class);
        eletronicos = query.getResultList();

        if (eletronicos.isEmpty()) {
            System.out.println("Não há livros eletrônicos");
        }
        else {
            for (var eletronico : eletronicos) {
                System.out.println(eletronico.toString());
            }
        }
    }

    public void listarLivros() {
        var livros = new ArrayList<Livro>();

        livros.addAll(em.createQuery("SELECT li FROM livros li", Livro.class).getResultList());
        if(!livros.isEmpty()){
            livros.sort(Comparator.comparing(Livro::getId));
            for (var livro: livros) { System.out.println(livro.toString()); }
        }else {
            System.out.println("Não há livros cadastrados!");
        }
    }

    public void listarVendas() {
        var vendasRetornadas = new ArrayList<Venda>(em.createQuery("SELECT li FROM vendas li", Venda.class).getResultList());
        if (vendasRetornadas.isEmpty()){
            System.out.println("Não há vendas realizadas.");
        }else {
            for (var venda : vendasRetornadas) { System.out.println(venda.toString()); }
        }
    }

    public Livro retornaLivroPorId(int id){
        try{
            return em.find(Livro.class, (long) id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LivrariaVirtual livraria = new LivrariaVirtual();


        int op;

        do {
            System.out.println("\n******** MENU ********\n");
            System.out.println("Escolha as opções abaixo: ");
            System.out.println("1 - Para cadastrar livro");
            System.out.println("2 - Para Realizar uma venda");
            System.out.println("3 - Para Listar livros");
            System.out.println("4 - Para Listar vendas");
            System.out.println("5 - Sair do programa");

            op = sc.nextInt();

            switch (op) {
                case 1:
                    livraria.cadastrarLivro();
                    break;
                case 2:
                    livraria.realizarVenda();
                    break;
                case 3:
                    System.out.println(
                            "Selecione dentre as opções abaixo:\n " +
                                    "1. Listar Livros Impressos\n" +
                                    "2. Listar Livros Eletronicos\n " +
                                    "3. Listar todos os Livros");
                    var opListar = sc.nextInt();
                    if(opListar == 1){
                        livraria.listarLivrosImpressos();
                    } else if (opListar == 2) {
                        livraria.listarLivrosEletronicos();
                    } else if (opListar == 3) {
                        livraria.listarLivros();
                    }else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 4:
                    livraria.listarVendas();
                    break;
                case 5:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção Inválida. Digite Uma opção válida!");
                    break;
            }
        }
        while (op != 5);
    }
}
