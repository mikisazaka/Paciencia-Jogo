package controle;

import cartas.Carta;
import estruturas_lineares.Lista;
import estruturas_lineares.No;

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

    public boolean moverListaParaLista(Lista origem, Lista destino) {
        if (origem == null || destino == null || origem.estaVazio()) return false;

        Lista temporaria = new Lista();

        // Monta sequência visível da origem
        int i = 0;
        while (origem.get(i) != null && origem.get(i).isVisivel()) {
            temporaria.add(origem.get(i)); // Mantém a ordem da sequência
            i++;
        }

        // Se a lista de destino estiver vazia, só pode mover se a primeira carta for um Rei
        if (destino.estaVazio()) {
            if (temporaria.get(0).getNumero() != 13) return false;
        } else {
            // Valida se a carta do topo da sequência pode ser colocada sobre a carta destino
            Carta topoDestino = destino.get(0);
            Carta primeiraSequencia = temporaria.get(0);

            if (topoDestino.getNumero() != primeiraSequencia.getNumero() + 1) return false;
            if (topoDestino.getCor().equals(primeiraSequencia.getCor())) return false;
        }

        while (origem.get(0) != null && origem.get(0).isVisivel()) {
            origem.removerCabeca();
        }

        // Move as cartas da sequência para o destino
        while (!temporaria.estaVazio()) {
            destino.add(temporaria.get(0));
            temporaria.removerCabeca();
        }

        // Torna visível a nova carta do topo da origem (se existir)
        if (origem.get(0) != null) {
            origem.get(0).setVisibilidade(true);
        }

        return true;
    }
}
