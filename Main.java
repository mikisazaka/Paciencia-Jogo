
import cartas.*;
import controle.Jogo;
import excecoes.IndiceInvalidoException;
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
                    
                    if(baralho.getCartas() == null) {
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
                        System.out.println("1 - Movimentar uma carta da fila (monteDeCompras) para pilha (4 Naipes)");
                        System.out.println("2 - Movimentar da fila (monteDeCompra) para a fila (monteDeCompra)");
                        System.out.println("3 - Movimentar uma carta da fila (monteDeCompra) para uma das listas ligadas (7 montes)");
                        System.out.println("4 - Movimentar uma carta de uma das listas ligadas (7 montes) para uma das pilhas (4 Naipes)");
                        System.out.println("5 - Movimentar uma carta de uma lista ligada (7 montes) para outra lista ligada (7 montes)");
                        System.out.println("6 - Reiniciar o jogo");
                        System.out.println("7 - Ver estado atual do jogo");
                        System.out.println("8 - Sair do jogo");
                        System.out.println();

                        int opcaoJogo = Integer.parseInt(sc.nextLine());

                        switch (opcaoJogo) {

                            case 1: {

                                String resultado = jogo.getMovimentacoes().moverFilaPilha();
                                System.out.println(resultado);

                                if (jogo.verificarVitoria()) {
                                    System.out.println("\n>>> Parabéns! Você venceu o jogo! <<<\n");
                                    jogoFinalizado = true;
                                }
                                System.out.println(jogo.visualizarJogo());
                                break;

                            }

                            case 2: {

                                Carta cartaFila = jogo.getMovimentacoes().movimentacaoFila();

                                if(cartaFila == null) {
                                    System.out.println("Não há cartas na fila para movimentar.");
                                } else {
                                    System.out.println("Nova carta do monte: " + cartaFila.getNome());
                                }
                                break;
                            }

                            case 3: {

                                System.out.println(jogo.visualizarJogo());
                                System.out.println("Digite o número da lista ligada (0 a 6) para onde deseja mover a carta:");
                                System.out.println("Considere 0 a carta da esquerda e 6, a carta da direita.");
                                int listaLigada = Integer.parseInt(sc.nextLine());

                                try {
                                    System.out.println(jogo.getMovimentacoes().movimentacaoFilaLista(listaLigada));
                                } catch (IndiceInvalidoException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }

                            case 4: {

                                System.out.println(jogo.visualizarJogo());
                                System.out.println("Digite o número da lista (0 a 6) de onde deseja mover a carta para a pilha:");
                                int lista = Integer.parseInt(sc.nextLine());

                                try {
                                    String resultadoMov = jogo.getMovimentacoes().moverListaParaPilha(lista);
                                    System.out.println(resultadoMov);

                                    if (jogo.verificarVitoria()) {
                                        System.out.println("\n>>> Parabéns! Você venceu o jogo! <<<\n");
                                        jogoFinalizado = true;
                                    }

                                } catch (IndiceInvalidoException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            }

                            case 5: {
                                System.out.println("Informe o índice da lista de início: ");
                                int listaInicial = Integer.parseInt(sc.nextLine());

                                System.out.println("Informe o índice da lista de destino: ");
                                int listaDestino = Integer.parseInt(sc.nextLine());

                                boolean sucesso = jogo.getMovimentacoes().moverListaParaLista(jogo.getMesa()[listaInicial], jogo.getMesa()[listaDestino]);

                                if (!sucesso) {
                                    System.out.println("Movimentação inválida.");
                                }

                                System.out.println(jogo.visualizarJogo());
                                break;
                            }

                            case 6: {
                                System.out.println("Reiniciando jogo...");
                                jogo.reiniciarJogo();
                                System.out.println(jogo.visualizarJogo());
                                break;
                            }

                            case 7: {
                                System.out.println(jogo.visualizarJogo());
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
                    System.out.println("Jogo finalizado!");
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