package cartas;

import estruturas_lineares.Pilha;

public class Baralho {

    private Pilha cartas;

    public Baralho() {
        cartas = new Pilha();
        gerarBaralho();
    }

    private void gerarBaralho() {

        String[] naipes = {"Copas", "Ouros", "Paus", "Espadas"};
        String[] cores = {"Vermelha", "Vermelha", "Preta", "Preta"};

        for (int i = 0; i < naipes.length; i++) {
            for (int numero = 1; numero <= 13; numero++) {
                String nome = naipes[i] + numero;
                Carta c = new Carta(numero, naipes[i], cores[i], false, nome);
                cartas.push(c);
            }
        }
    }

    public void embaralharCartas() {

    }
}
