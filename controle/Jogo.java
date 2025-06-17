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

    private Movimentacoes movimentacoes;

    public Jogo() {
        monteCompra = new Fila();

        for (int i = 0; i < bases.length; i++) {
            bases[i] = new Pilha();
        }

        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new Lista();
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

    public void visualizarJogo(Baralho baralho) {
        if (monteCompra.getSize() == 0) {
            System.out.print("[ ]  ");
        } else {
            System.out.print("[X]  ");
        }

        if (monteCompra.getSize() == 0 || !monteCompra.getCabeca().getValor().isVisivel()) {
            System.out.print("[ ]");
        } else {
            Carta topo = monteCompra.getCabeca().getValor();
            System.out.print(colorirCarta(topo));
        }

        System.out.print("     ");
        for (Pilha base : bases) {
            if (base.getSize() == 0) {
                System.out.print("[ ]  ");
            } else {
                Carta topo = base.getCabeca().getValor();
                String carta = colorirCarta(topo);
                System.out.print(padRight(carta, 5 + (carta.length() - topo.getNome().length())));
            }
        }
        System.out.println("\n");

        int maxAltura = 0;
        for (Lista lista : mesa) {
            int altura = 0;
            No<Carta> aux = lista.getCabeca();
            while (aux != null) {
                altura++;
                aux = aux.getProx();
            }
            if (altura > maxAltura) {
                maxAltura = altura;
            }
        }

        for (int i = 0; i < maxAltura; i++) {
            for (Lista lista : mesa) {
                No<Carta> aux = lista.getCabeca();
                int altura = 0;

                while (aux != null && altura < i) {
                    aux = aux.getProx();
                    altura++;
                }

                if (aux != null) {
                    Carta carta = aux.getValor();
                    if (aux.getProx() == null) {
                        String cor = colorirCarta(carta);
                        System.out.print(padRight(cor, 5 + (cor.length() - carta.getNome().length())));
                    } else {
                        System.out.print("X    ");
                    }
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
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
