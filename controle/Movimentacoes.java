package controle;

import cartas.Carta;

public class Movimentacoes {

    private Jogo jogo;

    public Movimentacoes(Jogo jogo) {
        this.jogo = jogo;
    }

    public Carta movimentacaoFila() {

        if (jogo.getMonteCompra().getSize() == 0) {
            return null;
        }

        Carta aux = jogo.getMonteCompra().getCabeca().getValor();
        if (aux.isVisivel()) {
            aux.setVisibilidade(false);
            jogo.getMonteCompra().dequeue();
            jogo.getMonteCompra().enqueue(aux);
        }

        Carta carta = jogo.getMonteCompra().getCabeca().getValor();
        carta.setVisibilidade(true);
        return carta;
    }

}
