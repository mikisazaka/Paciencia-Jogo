
import cartas.*;
import controle.Jogo;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Jogo jogo = new Jogo();
        Baralho baralho = new Baralho();

        while (true) {

            System.out.println("======Menu principal======");
            System.out.println("1 - Embaralhar cartas");
            System.out.println("2 - Iniciar Jogo");
            System.out.println("3 - Sair");
            System.out.println("");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1: {
                    System.out.println("Embaralhando...");

                    if (baralho.getCartas() == null) {
                        System.out.println("Falha ao embaralhar.");
                    } else {
                        baralho.embaralhar();
                        System.out.println("Embaralhamento concluído! \nForam embaralhadas " + baralho.getCartas().getSize() + " cartas!");
                    }
                    break;

                }

                case 2: {

                    jogo.estruturarJogo(baralho);
                    boolean jogoFinalizado = false;
                    while (!jogoFinalizado) {
                        System.out.println("\n======Menu do jogo======");
                        System.out.println("1 - Movimentar uma carta da fila para pilha");
                        System.out.println("2 - Movimentar da fila para a fila");
                        System.out.println("3 - Movimentar uma carta da fila para uma das listas ligadas");
                        System.out.println("4 - Movimentar uma carta de uma das listas ligadas para uma das pilhas");
                        System.out.println("5 - Movimentar uma carta de uma lista ligada para outra lista ligada");
                        System.out.println("6 - Reiniciar o jogo");
                        System.out.println("7 - Ver estado atual do jogo");
                        System.out.println("8 - Sair do jogo");
                        System.out.println();

                        int opcaoJogo = Integer.parseInt(sc.nextLine());

                        switch (opcaoJogo) {

                            case 1:

                                break;

                            case 2: {

                                Carta cartaFila = jogo.getMovimentacoes().movimentacaoFila();

                                if(cartaFila == null) {
                                    System.out.println("Não há cartas na fila para movimentar.");
                                } else {
                                    System.out.println("Nova carta do monte: " + cartaFila.getNome());
                                }
                                break;
                            }

                            case 3:

                                break;

                            case 4:

                                break;

                            case 5:

                                break;

                            case 6:

                                break;

                            case 7: {

                                jogo.visualizarJogo();
                                break;
                            }

                            case 8: {

                                System.out.println("Saindo do jogo...");
                                jogoFinalizado = true;
                                break;
                            }

                            default: {

                                System.out.println("Opção inválida!");
                                break;
                            }
                        }
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
