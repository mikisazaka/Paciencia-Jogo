package cartas;

public class Carta {
    private int numero;
    private String naipe;
    private String cor;
    private boolean visibilidade;
    private String nome;

    public Carta(int numero, String naipe, String cor, boolean visibilidade, String nome) {
        this.numero = numero;
        this.naipe = naipe;
        this.cor = cor;
        this.visibilidade = visibilidade;
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isVisivel() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
