package controle;

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

    // Estruturas auxiliares para reiniciar jogo
    private Pilha[] basesIniciais = new Pilha[4];
    private Fila monteCompraInicial;
    private Lista[] mesaInicial = new Lista[7];

    private Movimentacoes movimentacoes;

    public Jogo() {
        monteCompra = new Fila();
        monteCompraInicial = new Fila();

        for (int i = 0; i < bases.length; i++) {
            bases[i] = new Pilha();
            basesIniciais[i] = new Pilha();
        }

        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new Lista();
            mesaInicial[i] = new Lista();
        }

        movimentacoes = new Movimentacoes(this);
    }

    public void estruturarJogo(Baralho baralho) {
        Lista cartas = baralho.getCartas();

        No<Carta> aux = cartas.getCabeca();
        for (int i = 0; i < mesa.length; i++) {
            for (int j = 0; j <= i; j++) {
                No<Carta> carta = aux;
                Carta c = carta.getValor();
                if (j == i) {
                    c.setVisibilidade(true);
                }
                mesa[i].add(c);
                mesaInicial[i].add(new Carta(c));
                aux = aux.getProx();
            }
        }

        while (aux != null) {
            No<Carta> carta = aux;
            Carta c = carta.getValor();
            monteCompra.enqueue(c);
            monteCompraInicial.enqueue(new Carta(c));
            aux = aux.getProx();
        }
    }

    public String visualizarJogo() {

        StringBuilder sb = new StringBuilder();
        if (monteCompra.getSize() == 0) {
            sb.append("[ ]  ");
        } else {
            sb.append("[X]  ");
        }

        if (monteCompra.getSize() == 0 || !monteCompra.getCabeca().getValor().isVisivel()) {
            sb.append("[ ]");
        } else {
            Carta topo = monteCompra.getCabeca().getValor();
            sb.append(colorirCarta(topo));
        }

        sb.append("     ");
        for (Pilha base : bases) {
            if (base.getSize() == 0) {
                sb.append("[ ]  ");
            } else {
                Carta topo = base.getCabeca().getValor();
                String carta = colorirCarta(topo);
                sb.append(padRight(carta, 5 + (carta.length() - topo.getNome().length())));
            }
        }

        sb.append("\n");

        int maxLen = 0;
        for (Lista coluna : mesa) {
            if (coluna.getSize() > maxLen) {
                maxLen = coluna.getSize();
            }
        }

        for (int linha = 0; linha < maxLen; linha++) {
            for (Lista coluna : mesa) {
                int index = coluna.getSize() - 1 - linha;

                if (index >= 0) {
                    Carta carta = coluna.get(index);

                    if (carta.isVisivel()) {
                        String cor = colorirCarta(carta);
                        sb.append(padRight(cor, 5 + (cor.length() - carta.getNome().length())));
                    } else {
                        sb.append("X    ");
                    }

                } else {
                    sb.append("     ");
                }
            }
            sb.append("\n");
        } return sb.toString();
    }

    public void reiniciarJogo() {
        limparEstruturas();
        reestruturarMesaInicial();
    }

    private void limparEstruturas() {
        for (Pilha base : bases) {
            int tamanho =  base.getSize();
            for (int i = 0; i < tamanho; i++) {
                base.pop();
            }
        }

        if (monteCompra.getSize() != 0) {
            int tamanho =  monteCompra.getSize();
            for (int i = 0; i < tamanho; i++) {
                monteCompra.dequeue();
            }
        }

        for (Lista coluna : mesa) {
            while (coluna.getCabeca() != null) {
                int index = coluna.getSize();
                coluna.removerElemento(index - 1);
            }
        }
    }

    private void reestruturarMesaInicial() {
        for (int i = 0; i < mesaInicial.length; i++) {
            for (int j = i; j >= 0; j--) {
                Carta aux =  mesaInicial[i].get(j);
                Carta c = new Carta(aux);
                mesa[i].add(c);
            }
        }

        No<Carta> atualizar = monteCompraInicial.getCabeca();
        while (atualizar != null) {
            Carta c = new Carta(atualizar.getValor());
            monteCompra.enqueue(c);
            atualizar = atualizar.getProx();
        }
    }
    
    public boolean verificarVitoria() {
        if(monteCompra.getSize() == 0) {
            for(Lista lista : mesa) {
                if (lista.getSize() != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private String colorirCarta(Carta carta) {
        String nome = carta.getNome();
        char naipe = nome.charAt(0);

        switch (naipe) {
            case '♥':
            case '♦':
                return "\u001B[31m" + nome + "\u001B[0m"; // Vermelho
            case '♠':
            case '♣':
                return "\u001B[37m" + nome + "\u001B[0m"; // Preto (cinza claro)
            default:
                return nome;
        }
    }

    private String padRight(String texto, int totalLength) {
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < totalLength) {
            sb.append(" ");
        }
        return sb.toString();
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

    public Movimentacoes getMovimentacoes() {
        return movimentacoes;
    }

}
