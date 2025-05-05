
import cartas.Baralho;
import cartas.Carta;
import estruturas_lineares.Fila;
import estruturas_lineares.Lista;
import estruturas_lineares.No;
import estruturas_lineares.Pilha;

public class Jogo {

    private Pilha[] bases = new Pilha[4];
    private Fila monteCompra;
    private Lista[] mesa = new Lista[7];

    public Jogo() {
        monteCompra = new Fila();

        for (int i = 0; i < bases.length; i++) {
            bases[i] = new Pilha();
        }

        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new Lista();
        }
    }

    public void estruturarJogo(Baralho baralho) {
        Lista cartas = baralho.getCartas();

        No<Carta> aux = cartas.getCabeca();
        for (int i = 0; i < mesa.length; i++) {
            for (int j = 0; j <= i; j++) {
                No<Carta> carta = aux;
                Carta c = carta.getValor();
                mesa[i].add(c);
                if (j == i) {
                    c.setVisibilidade(true);
                }
                aux = aux.getProx();
            }
        }

        while (aux != null) {
            No<Carta> carta = aux;
            Carta c = carta.getValor();
            monteCompra.enqueue(c);
            aux = aux.getProx();
        }
    }

    public void visualizarJogo() {

    }

    public Pilha[] getBases() {
        return bases;
    }

    public void setBases(Pilha[] bases) {
        this.bases = bases;
    }

    public Fila getMonteCompra() {
        return monteCompra;
    }

    public void setMonteCompra(Fila monteCompra) {
        this.monteCompra = monteCompra;
    }

    public Lista[] getMesa() {
        return mesa;
    }

    public void setMesa(Lista[] mesa) {
        this.mesa = mesa;
    }

}
