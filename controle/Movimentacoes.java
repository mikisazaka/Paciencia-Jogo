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

        if (lista < 0 || lista > 6) {
            throw new IndiceInvalidoException("Índice inválido! Escolha um número entre 0 e 6.");
        }

        if (jogo.getMonteCompra().getSize() == 0) {
            return "Monte de compra vazio!";
        }

        if (jogo.getMonteCompra().getCabeca().getValor().isVisivel()) {
            Carta cartaFila = jogo.getMonteCompra().getCabeca().getValor();
            Carta cartaLista = null;
            if(jogo.getMesa()[lista].getSize() != 0)
                cartaLista = jogo.getMesa()[lista].getCabeca().getValor();

            if (cartaLista != null) {
                if (!cartaLista.getCor().equals(cartaFila.getCor()) && cartaFila.getNumero() == cartaLista.getNumero() - 1) {
                    jogo.getMesa()[lista].add(cartaFila);
                    jogo.getMonteCompra().dequeue();
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

                    // ✅ Verifica vitória após a movimentação
                    if (jogo.verificarVitoria()) {
                        return "Carta " + cartaFila.getNome() + " movida para base vazia. Parabéns! Você venceu o jogo!";
                    }

                    return "Carta " + cartaFila.getNome() + " movida para base vazia.";
                }
            } else {
                Carta topoBase = base.getCabeca().getValor();
                if (topoBase.getNaipe() == cartaFila.getNaipe() &&
                        cartaFila.getNumero() == topoBase.getNumero() + 1) {
                    base.push(cartaFila);
                    jogo.getMonteCompra().dequeue();

                    if (jogo.verificarVitoria()) {
                        return "Carta " + cartaFila.getNome() + " empilhada com sucesso. Parabéns! Você venceu o jogo!";
                    }

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

        Carta cartaTopo = lista.getCabeca().getValor();

        if (!cartaTopo.isVisivel()) {
            return "A carta do topo da lista não está visível.";
        }

        for (Pilha base : jogo.getBases()) {
            if (base.getSize() == 0) {
                if (cartaTopo.getNumero() == 1) { // Ás
                    base.push(cartaTopo);
                    lista.removerCabeca();
                    if(lista.getCabeca() != null)
                        lista.getCabeca().getValor().setVisibilidade(true);

                    if (jogo.verificarVitoria()) {
                        return "Carta " + cartaTopo.getNome() + " movida para base vazia. Parabéns! Você venceu o jogo!";
                    }

                    return "Carta " + cartaTopo.getNome() + " movida para base vazia.";
                }
            } else {
                Carta topoBase = base.getCabeca().getValor();
                if (topoBase.getNaipe() == cartaTopo.getNaipe() &&
                        cartaTopo.getNumero() == topoBase.getNumero() + 1) {
                    base.push(cartaTopo);
                    lista.removerCabeca();
                    if(lista.getCabeca() != null)
                        lista.getCabeca().getValor().setVisibilidade(true);

                    if (jogo.verificarVitoria()) {
                        return "Carta " + cartaTopo.getNome() + " empilhada com sucesso. Parabéns! Você venceu o jogo!";
                    }

                    return "Carta " + cartaTopo.getNome() + " empilhada com sucesso.";
                }
            }
        }

        return "Não é possível mover a carta " + cartaTopo.getNome() + " para nenhuma pilha.";
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