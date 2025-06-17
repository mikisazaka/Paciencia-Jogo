package controle;

import cartas.Carta;
import estruturas_lineares.Lista;
import estruturas_lineares.Pilha;
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

    public String moverFilaPilha() {
    if (jogo.getMonteCompra().getSize() == 0) {
        return "Monte de compra está vazio.";
    }

    Carta cartaFila = jogo.getMonteCompra().getCabeca().getValor();

    if (!cartaFila.isVisivel()) {
        return "Carta não está visível.";
    }

    for (Pilha base : jogo.getBases()) {
        if (base.getSize() == 0) {
            if (cartaFila.getNumero() == 1) { 
                base.push(cartaFila);
                jogo.getMonteCompra().dequeue(); 
                return "Carta " + cartaFila.getNome() + " movida para base vazia.";
            }
        } else {
            Carta topoBase = base.getCabeca().getValor();
            if (topoBase.getNaipe() == cartaFila.getNaipe() &&
                cartaFila.getNumero() == topoBase.getNumero() + 1) {
                base.push(cartaFila);
                jogo.getMonteCompra().dequeue(); 
                return "Carta " + cartaFila.getNome() + " empilhada com sucesso.";
            }
        }
    }

    return "Não é possível mover a carta " + cartaFila.getNome() + " para nenhuma base.";
    }

    public String moverListaParaPilha(int indiceLista) throws IndiceInvalidoException {
        if (indiceLista < 0 || indiceLista > 6) {
            throw new IndiceInvalidoException("Índice da lista inválido! Use de 0 a 6.");
        }

        Lista lista = jogo.getMesa()[indiceLista];
        if (lista.getSize() == 0) {
            return "A lista selecionada está vazia.";
        }

        Carta cartaTopo = lista.getCauda().getValor();

        if (!cartaTopo.isVisivel()) {
            return "A carta do topo da lista não está visível.";
        }

        for (Pilha base : jogo.getBases()) {
            if (base.getSize() == 0) {
                if (cartaTopo.getNumero() == 1) { // Ás
                    base.push(cartaTopo);
                    lista.removerElemento(lista.getSize() - 1);
                    return "Carta " + cartaTopo.getNome() + " movida para base vazia.";
                }
            } else {
                Carta topoBase = base.getCabeca().getValor();
                if (topoBase.getNaipe() == cartaTopo.getNaipe() &&
                    cartaTopo.getNumero() == topoBase.getNumero() + 1) {
                    base.push(cartaTopo);
                    lista.removerElemento(lista.getSize() - 1);
                    return "Carta " + cartaTopo.getNome() + " empilhada com sucesso.";
                }
            }
        }

        return "Não é possível mover a carta " + cartaTopo.getNome() + " para nenhuma pilha.";
    }
}
