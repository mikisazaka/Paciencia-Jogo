
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Menu principal:");
            System.out.println("1 - Embaralhar cartas");
            System.out.println("2 - Iniciar Jogo");
            System.out.println("3 - Sair");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                
                case 1: {

                    break;

                }

                case 2: {

                    while () {
                        System.out.println("Menu do jogo:");
                        System.out.println("1 - Movimentar uma carta da fila para pilha");
                        System.out.println("2 - Movimentar da fila para a fila");
                        System.out.println("3 - Movimentar uma carta da fila para uma das listas ligadas");
                        System.out.println("4 - Movimentar uma carta de uma das listas ligadas para uma das pilhas");
                        System.out.println("5 - Movimentar uma carta de uma lista ligada para outra lista ligada");
                        System.out.println("6 - Reiniciar o jogo");
                        System.out.println("7 - Ver estado atual do jogo");
                    }

                    break;

                }

                case 3: {
                    sc.close();
                    return;
                }

                default: {
                    System.out.println("Entrada inválida!");
                }

            }

        }

    }

}
