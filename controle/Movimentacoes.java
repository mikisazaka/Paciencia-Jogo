package controle;

import cartas.Carta;
import excecoes.IndiceInvalidoException;

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

    public String movimentacaoFilaLista(int lista) throws IndiceInvalidoException {

        if(lista < 0 || lista > 6) {
            throw new IndiceInvalidoException("Índice inválido! Escolha um número entre 0 e 6.");
        } 

        if (jogo.getMonteCompra().getSize() == 0) {
            return "Monte de compra vazio!";
        }

        if (jogo.getMonteCompra().getCabeca().getValor().isVisivel()) {
            Carta cartaFila = jogo.getMonteCompra().getCabeca().getValor();
            Carta cartaLista = jogo.getMesa()[lista].getCauda().getValor();

            if (cartaLista != null) {
                if (!cartaLista.getCor().equals(cartaFila.getCor()) && cartaFila.getNumero() == cartaLista.getNumero() + 1) {
                    jogo.getMonteCompra().dequeue();
                    jogo.getMesa()[lista].addFinal(cartaFila);
                    return "Carta movida para a lista " + lista + " com sucesso!";
                }
                return "Não foi possível mover a carta para a lista " + lista + ". Verifique as regras do jogo.";
            } else {
                if (cartaFila.getNumero() == 13) {
                    jogo.getMonteCompra().dequeue();
                    jogo.getMesa()[lista].add(cartaFila);
                    return "Carta movida para a lista " + lista + " com sucesso!";
                }
            }
        }
        return "Carta da fila não visível! Não é possível mover a carta para a lista " + lista + ".";
    }
}
