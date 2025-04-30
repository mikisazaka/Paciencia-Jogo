package cartas;

import estruturas_lineares.Lista;
import java.util.Random;

public class Baralho {

    private Lista cartas;

    public Baralho() {
        cartas = new Lista();
        gerarBaralho();
    }

    private void gerarBaralho() {

        String[] naipes = {"Copas", "Ouros", "Paus", "Espadas"};
        String[] cores = {"Vermelha", "Vermelha", "Preta", "Preta"};

        for (int i = 0; i < naipes.length; i++) {
            for (int numero = 1; numero <= 13; numero++) {
                String nome = naipes[i] + numero;
                Carta c = new Carta(numero, naipes[i], cores[i], false, nome);
                cartas.add(c);
            }
        }
    }

    public void embaralhar() {
        Lista embaralhadas = new Lista();
        Random random = new Random();

        while (!cartas.estaVazio()) {
            int indice = random.nextInt(cartas.getSize());
            Carta c = cartas.get(indice);
            cartas.removerElemento(indice);
            embaralhadas.addFinal(c);
        }

        cartas = embaralhadas;
    }

    public Lista getCartas() {
        return cartas;
    }    

}
